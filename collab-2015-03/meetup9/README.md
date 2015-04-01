# meet-async

# N.B.: Collaboration branch -- requires LEIN_FIGWHEEL_* environment variables.

## Usage

1. ```export LEIN_FIGWHEEL_HOST=<hostname>```
1. ```export LEIN_FIGWHEEL_PORT=<port#>```
1. Install [Leiningen](http://leiningen.org). Then run ```lein figwheel``` from the project directory.
  1. If you can, run ```rlwrap lein figwheel``` to get readline support.
  1. After your code compiles for the first time, you'll see a Clojurescript REPL waiting for a connection from your browser.
1. Browse to http://localhost:3449 and your Clojurescript REPL will become live. In your REPL, do ```(in-ns 'meet-async.core)``` to get into the right namespace.
1. Edit ```src/meet_async/core.cljs``` in your preferred text editor—your changes will be live-loaded by ```lein figwheel```. You'll want to look at the code as you go through the examples in your browser.
1. You might also want to take a look at ```resources/public/index.html```. 
