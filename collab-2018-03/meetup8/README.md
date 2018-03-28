# traveling-game

This is a toy graph-traversal-game-thing. It runs in your browser, and you can do figwheelies with your REPL!

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

When you're ready to deploy this in production, be sure to run `lein clean` first! I've been bitten more times than I can count by forgetting that step...

```
lein clean
lein cljsbuild once min
```

## TODO

1. Example of a nice graphical representation possibility: http://jsfiddle.net/bc4um7pc/ -- get a nice graph drawn, with labeled edges, arrows, etc.
1. Improve the UI in other ways (I've left several possibilities open...).
1. Use clojure.spec to specify the shape of the graph structures in the traveling-game.graph ns.
1. Add more UI settings to change the connectedness probability of the graph, the cost ranges for the edges, etc.
1. Implement more efficient algorithms for 'traveling-game.graph/connected-from?, 'traveling-game.graph/connected?, or 'traveling-game.graph/graph->shortest-path. Or something else.
