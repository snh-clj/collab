# rottentomatoes-query

## Overview

The mies-node leiningen template provides a nice default setup to run CLJS
applications from the command line, using node.js. In this collaboration, we’ll
enhance a simple starting application to do increasingly interesting queries
using the Rotten Tomatoes API.

## Resources

* API docs: http://developer.rottentomatoes.com/
* API base URL: http://api.rottentomatoes.com/api/public/v1.0

## Goals

The following are suggested features you might add to your query application.
They are loosely ordered by increasing perceived difficulty. Feel free to be
creative!

1. run provided simple API call
1. add second successful API call
1. use CLI params
  1. Positional params are suggested for --simplicity.
1. Try any of the following (listed in suggested order of difficulty)
  1. list cast of movie named on CLI
  1. list intersection of casts of movies named on CLI
  1. list movies in common between two actors
  1. support other set operations: union, difference (asym/sym)?
  1. list filmography of actor named on CLI
  1. list set ops (intersection, union, difference) on filmographies of actors named
on CLI
  1. 6 degrees of Kevin Bacon — http://en.wikipedia.org/wiki/Six_Degrees_of_Kevin_Bacon
  1. 451 degrees of Kevin Bacon — actually, 6 degrees from Kevin Bacon will likely
cover most of the database of actors. It’s probably worth starting with 1 degree
of Kevin Bacon and build incrementally. I’d suggest a depth parameter that you
can slowly ramp up.

## Usage

Your collaboration group already has a rottentomatoes-query project directory
with a working example API call. This working example uses an API key that is
provided in the environment.

If a member of your group has created a Rotten Tomatoes API key, you can use it
by running this command in your “run” window (see below):

```
export RT_API_KEY=”<YOUR_KEY_HERE>”
```

Working with ClojureScript and command line (vs repl) necessitates multiple tmux
windows. To create a new tmux window type `CTRL-A c`. To switch to the next and
previous tmux windows use `CTRL-A n` and `CTRL-A p`. To switch to a particular tmux
window by number type `CTRL-A` followed by the number of the window you want.
(e.g. to switch to window 2 type `CTRL-A 2`. To bounce from your current window
to the last window you viewed type `CTRL-A l`  (that’s a lowercase L). You can
name windows with `CTRL-A  ,` (comma) and then enter the new name (“compile”,
“run” and “edit” may make sense for the below).

### tmux window 1: Compilation

```
$ lein cljsbuild auto
```

Make sure one build completes before you proceed in tmux window 2.

### tmux window 2: Execution
```
,$ nodejs ./run.js
```

### tmux window 3: Editing
```
$ emacs src/rottentomatoes_query/core.cljs
```

After every saved change, your cljsbuild process will automatically re-compile
in tmux window 1 so you should be able to go to tmux window 2 and run your
application almost immediately.
