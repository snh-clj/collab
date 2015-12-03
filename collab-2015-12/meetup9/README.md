# birdie-tell

<img align="right" alt="Gossip in action" src="http://blogs.unimelb.edu.au/sciencecommunication/files/2014/10/Screen-shot-2014-10-20-at-6.34.52-PM.png"/>

This is a small Clojure application/library that implements a simple
data-propagation Gossip protocol for fun and exploration. There is no
idea here of consensus, and only a very weak idea of "membership" in
the cluster. Each peer is authoritative over its own data, and shares
that versioned data with the rest of the gossip cluster.

As a simple use case, consider a router named Zelda that can route to
networks A, B, and D. The gossip protocol implemented by this library
would allow Zelda to inform her peer routers Yolanda, Xerces, and
Wenderson that she can route to networks A, B, and D. Yolanda, Xerces,
and Wenderson can then send Zelda traffic for those networks.

## TL;DR

Use `lein run -- --help` to see the options.

Use `./run.sh 0`, and just watch the output for a bit to get a sense
of what a single peer's local state looks like.

Then `./run.sh 1`, `./run.sh 2`, and `./run.sh 3` in three separate
terminals (or tmux windows) and watch their local states take in
gossip data.

Edit `data00.edn`, `data01.edn`, etc. and watch state changes
propagate.

## Gossip Protocols

<a href="https://camo.githubusercontent.com/9c623792929f54b6ec656ee718fec7ccc1a42892/68747470733a2f2f7261772e6769746875622e636f6d2f737562737461636b2f6e6f6465666573742d323031322f6d61737465722f696d616765732f676f737369705f70726f746f636f6c2e706e67"><img align="right" src="https://camo.githubusercontent.com/9c623792929f54b6ec656ee718fec7ccc1a42892/68747470733a2f2f7261772e6769746875622e636f6d2f737562737461636b2f6e6f6465666573742d323031322f6d61737465722f696d616765732f676f737369705f70726f746f636f6c2e706e67" width="400"/></a>

[Gossip protocols](https://en.wikipedia.org/wiki/Gossip_protocol) are
named for a metaphor; imagine people gathering around a water cooler
and exchanging the latest news with random people who happen to visit
the cooler at the same moment.

Gossip protocols can be used for reliable distribution of data and
computation, and I'm interested in incorporating [vector
clocks](https://en.wikipedia.org/wiki/Vector_clock) to achieve
consensus on shared state, but I haven't gotten that far, yet!

### Lessons learned:
1. Distributed systems are harder than they seem, and
1. #1 is true even after you've accepted #1.

### Overview of birdie-tell Gossip

1. Every peer always sends its entire local state whenever it gossips. This includes its own UUID and its complete list of peers (including itself) and their attributes.
1. Peers receive gossip silently; they do not gossip in response.
1. Peers wait at least `--minimum-gossip-wait` milliseconds and at most `----maximum-gossip-wait` milliseconds between attempts to gossip to a randomly-chosen peer.
1. Peers attempt to gossip to a believed-alive peer `--live-percentage` % of the time. The rest of the time, or when there are no believed-alive peers, peers randomly select a believed-dead peer or a yet-unreached potential peer (specified by `--peer` on the command line).
1. Peers never gossip to themselves.
1. Peers ignore data about themselves that are received from peers.
1. Dead peers are only identified when an attempt to gossip to them fails. A single failure can propagate the deadness of a peer throughout the cluster. Undead peers are identified by a single success. This means that in the case of a partial partition in the cluster, where some nodes can see node A and other nodes cannot, A will repeatedly switch back and forth between alive and dead.

### CAP Properties of birdie-tell Gossip

* Consistency: Weak: eventually, word will get around. Since each peer is fully authoritative over its own data, and all data is versioned by a monotonically increasing integer, data loss is not possible.
* Availability: High: each peer is its own authority, so if a peer is up its data can be updated.
* Partition tolerance: High: birdie-tell continues to operate despite arbitrary partitioning due to network failures.

### Data Velocity in birdie-tell Gossip

How quickly does data move through a cluster of peers running
birdie-tell? Ignoring `--minimum-gossip-wait` and
`--maximum-gossip-wait` options, consider how many hops it should take
for a change in one peer's data to reach every other active peer.

To keep it simple, we'll only consider the ideal scenario in which all
peers are alive and accessible, or in which `--live-percentage` is set
to 100.

#### Propagation Rounds for *n* nodes

N.B.: This is a first draft of these calculations.

TODO: re-do this with quantization.

1. Peer A gossips its own New Data to peer B. Now two peers have New Data.
1. A & B randomly select two new peers to receive gossip. If peers ever gossipped to themselves, the probability that each would choose a peer that doesn't have New Data would be `(/ (- n 2) n)`. But since they never gossip to themselves, the probability is `(/ (- (dec n) 1) (dec n))`. Multiplying by 2, `(* 2 (/ (- (dec n) 1) (dec n)))` more peers probably have New Data, for a total of `(+ 2 (* 2 (/ (- (dec n) 1) (dec n))))`; we'll say *x* is the number of peers that now have New Data.
1. *x* peers randomly select *x* other peers to receive gossip. The probability that each will choose a peer that doesn't have New Data is `(/ (- (dec n) (dec x)) (dec n))`, or `(* x (/ (- (dec n) (dec x)) (dec n)))`, for a new total of `(+ x (* x (/ (- (dec n) (dec x)) (dec n))))`.

This calculation is generalized in `birdie-tell.core/calculate-propagation-steps`.

```
birdie-tell.core=> (time (calculate-propagation-steps 100))
"Elapsed time: 3.770804 msecs"
10
birdie-tell.core=> (time (calculate-propagation-steps 1000))
"Elapsed time: 935.393359 msecs"
14
birdie-tell.core=> (time (calculate-propagation-steps 10000))
"Elapsed time: 106829.810707 msecs"
17
```

17 randomized gossip rounds to propagate through a 10000-node cluster!

## Usage

The following example starts a peer that:

1. watches `data00.edn` as the backing data store,
1. has UUID `uuid:0`,
1. is named `yelnats`,
1. listens on `127.0.0.1:2400`, and
1. will try occasionally to talk to `127.0.0.1:2401`.

```bash
lein run -- --input-file data00.edn --uuid uuid:0 --name yelnats --host-port 127.0.0.1:2400 --peer 127.0.0.1:2401
```

While the peer is running, edit the file `data01.edn` and save your changes. Watch the peer's output to see the `:data` update, and the `:version` be incremented.

## Example

Open four terminals so you can see them all on one screen. [Divvy](http://mizage.com/divvy/) is great for arranging these. In these four terminals, run the following commands:

### Terminal A
`./run.sh 0`

### Terminal B
`./run.sh 1`

### Terminal C
`./run.sh 2`

### Terminal D
`./run.sh 3`

After they all start showing that they're all :alive, `CTRL-c` one or two (or three!) of them, then restart them, and watch the group recover.

## Additional Resources

1. https://github.com/michaelklishin/vclock
1. http://courses.csail.mit.edu/6.895/fall02/papers/Ladin/acmtocs.pdf
1. https://code.google.com/p/cassandra-shawn/wiki/GossipProtocol
1. http://doc.akka.io/docs/akka/snapshot/common/cluster.html
1. https://github.com/edwardcapriolo/gossip
1. http://courses.washington.edu/css434/students/Gossip.pdf

## TODO

1. handle threads properly

## License

Copyright © 2015 Matt Oquist

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.