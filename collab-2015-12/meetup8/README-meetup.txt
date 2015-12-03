README.md is intended to be a useful resource to aid you in
understanding the application enough to dig in and start poking it to
see what happens.

The following are some suggested collaboration activities that should be interesting:

1. Right now, dead peers are only detected when someone tries to contact them. Is there a better, more aggressive way to detect dead peers?
2. Add a TTL integer to received data, decrementing it every time you
   gossip it to a peer. This will cut down on the amount of redundant
   (known) data that is gossipped.
3. Implement 'output-stream-handler in order to reply to gossip with gossip, rather than just receiving gossip silently.
4. Use inotify lib to watch input-file, rather than slurping it repeatedly.
5. Implement a second listening socket to receive local :data updates, replacing --input-file.
6. Add error-checking to 'split-hostport.
7. Write some generative tests of this library/application.
7. Write any kind of test of this library/application. :-)
8. Use transit (https://github.com/cognitect/transit-clj) for transport.
9. Gossip over core.async instead of network sockets.
