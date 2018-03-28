// Compiled by ClojureScript 1.9.293 {:static-fns true, :optimize-constants true, :elide-asserts true}
goog.provide('traveling_game.graph');
goog.require('cljs.core');
goog.require('clojure.string');
goog.require('clojure.math.combinatorics');
/**
 * Nodes that may be selected when creating a randomized graph
 *   Map from node-name to metadata about the node. The metadata is currently ignored.
 */
traveling_game.graph.nodes = cljs.core.PersistentHashMap.fromArrays(["Stromsburg","Robin's_Nest","Medaryville","Seattle","Manchester","London","Boston","Hudson","Osceola","Paris","Portland","Omaha","Nashua"],[new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(9)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(80)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(10)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(43)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(11)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(17)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(19)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(1)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(8)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(14)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(123)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(71)], null),new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$points,(3)], null)]);
/**
 * Randomly select 'n nodes, presumeably to be used when constructing a graph.
 */
traveling_game.graph.select_nodes = (function traveling_game$graph$select_nodes(n){
var n__$1 = (function (){var x__6881__auto__ = n;
var y__6882__auto__ = cljs.core.count(traveling_game.graph.nodes);
return ((x__6881__auto__ < y__6882__auto__) ? x__6881__auto__ : y__6882__auto__);
})();
return cljs.core.take.cljs$core$IFn$_invoke$arity$2(n__$1,cljs.core.shuffle(cljs.core.keys(traveling_game.graph.nodes)));
});
/**
 * 'rand is inclusive of 0 and exclusive of 1, so our test should not have '=.
 */
traveling_game.graph.do_probability = (function traveling_game$graph$do_probability(cutoff){
return (cljs.core.rand.cljs$core$IFn$_invoke$arity$0() < cutoff);
});
/**
 * Given
 * * the 'prob[ability] that any two nodes will be connected by an edge,
 * * the 'cost-min and 'cost-max for the randomized cost of any given edge, and
 * * a seq of the nodes to be included in the graph,
 *   ...construct a graph represented as a vector of 3-tuple vectors.
 * 
 *   E.g.:
 *   (nodes->graph 0.5 1 10 (select-nodes 3))
 *   [["Robin's_Nest" "Stromsburg" 1]
 * ["Osceola" "Robin's_Nest" 2]
 * ["Osceola" "Stromsburg" 3]]
 *   
 */
traveling_game.graph.nodes__GT_graph = (function traveling_game$graph$nodes__GT_graph(prob,cost_min,cost_max,nodes){
var prob__$1 = (function (){var x__6874__auto__ = prob;
var y__6875__auto__ = 0.3;
return ((x__6874__auto__ > y__6875__auto__) ? x__6874__auto__ : y__6875__auto__);
})();
return cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(((function (prob__$1){
return (function (p__13446){
var vec__13447 = p__13446;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13447,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13447,(1),null);
return (new cljs.core.PersistentVector(null,3,(5),cljs.core.PersistentVector.EMPTY_NODE,[a,b,(cost_min + cljs.core.rand_int((cost_max - cost_min)))],null));
});})(prob__$1))
,cljs.core.filter.cljs$core$IFn$_invoke$arity$2(((function (prob__$1){
return (function (p__13450){
var vec__13451 = p__13450;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13451,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13451,(1),null);
var and__6531__auto__ = cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2(a,b);
if(and__6531__auto__){
return traveling_game.graph.do_probability(prob__$1);
} else {
return and__6531__auto__;
}
});})(prob__$1))
,cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic(((function (prob__$1){
return (function (x){
return cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(((function (prob__$1){
return (function (p1__13437_SHARP_){
return (new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[x,p1__13437_SHARP_],null));
});})(prob__$1))
,nodes);
});})(prob__$1))
,cljs.core.array_seq([nodes], 0))));
});
/**
 * Take a directed graph and make it undirected, selecting whichever edge cost comes last.
 */
traveling_game.graph.graph__GT_undirected = (function traveling_game$graph$graph__GT_undirected(graph){
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (r,p__13465){
var vec__13466 = p__13465;
var ab = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13466,(0),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13466,(1),null);
var vec__13469 = cljs.core.vec(ab);
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13469,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13469,(1),null);
return cljs.core.conj.cljs$core$IFn$_invoke$arity$variadic(r,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [a,b,cost], null),cljs.core.array_seq([new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [b,a,cost], null)], 0));
}),cljs.core.PersistentVector.EMPTY,cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (r,p__13472){
var vec__13473 = p__13472;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13473,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13473,(1),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13473,(2),null);
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(r,cljs.core.PersistentHashSet.fromArray([a,b], true),cost);
}),cljs.core.PersistentArrayMap.EMPTY,graph));
});
/**
 * Given some nodes that have been visited, 'travel simultaneously
 *   from all of them to whatever can now be reached. Return the seq of
 *   distinct nodes reachable from everywhere you've been.
 */
traveling_game.graph.travel = (function traveling_game$graph$travel(visited_nodes,graph){
return cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.second,cljs.core.filter.cljs$core$IFn$_invoke$arity$2((function (p1__13476_SHARP_){
var G__13478 = cljs.core.first(p1__13476_SHARP_);
return (visited_nodes.cljs$core$IFn$_invoke$arity$1 ? visited_nodes.cljs$core$IFn$_invoke$arity$1(G__13478) : visited_nodes.call(null,G__13478));
}),graph)));
});
/**
 * Extract the node names from the given 'graph, returning a 'set.
 */
traveling_game.graph.graph__GT_nodes = (function traveling_game$graph$graph__GT_nodes(graph){
return cljs.core.set(cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic((function (p1__13479_SHARP_){
return cljs.core.take.cljs$core$IFn$_invoke$arity$2((2),p1__13479_SHARP_);
}),cljs.core.array_seq([graph], 0)));
});
/**
 * Determine if the given 'graph is connected (i.e., every node is
 *   reachable) when starting from 'starting-node.
 */
traveling_game.graph.connected_from_QMARK_ = (function traveling_game$graph$connected_from_QMARK_(graph,starting_node){
var all_nodes = traveling_game.graph.graph__GT_nodes(graph);
var graph__$1 = graph;
var visited_nodes = cljs.core.PersistentHashSet.fromArray([starting_node],true);
while(true){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(visited_nodes,all_nodes)){
return true;
} else {
var reachable_nodes = traveling_game.graph.travel(visited_nodes,graph__$1);
var now_visited_nodes = cljs.core.into.cljs$core$IFn$_invoke$arity$2(visited_nodes,reachable_nodes);
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2(now_visited_nodes,visited_nodes)){
var G__13481 = cljs.core.filter.cljs$core$IFn$_invoke$arity$2(((function (graph__$1,visited_nodes,reachable_nodes,now_visited_nodes,all_nodes){
return (function (p1__13480_SHARP_){
return cljs.core.complement(now_visited_nodes).call(null,cljs.core.first(p1__13480_SHARP_));
});})(graph__$1,visited_nodes,reachable_nodes,now_visited_nodes,all_nodes))
,graph__$1);
var G__13482 = now_visited_nodes;
graph__$1 = G__13481;
visited_nodes = G__13482;
continue;
} else {
return false;
}
}
break;
}
});
/**
 * Testing every node that has an outgoing edge, return true if the
 *   graph is connected from all points, and false if it is not connected
 *   from any point. (We don't want to bother handling disconnected graphs.)
 */
traveling_game.graph.connected_QMARK_ = (function traveling_game$graph$connected_QMARK_(graph){
var all_starting_nodes = cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.first,graph));
var starting_node = cljs.core.first(all_starting_nodes);
var rest_start_nodes = cljs.core.rest(all_starting_nodes);
while(true){
if(cljs.core.truth_(traveling_game.graph.connected_from_QMARK_(graph,starting_node))){
return true;
} else {
if(cljs.core.empty_QMARK_(cljs.core.rest(rest_start_nodes))){
return false;
} else {
var G__13483 = cljs.core.first(rest_start_nodes);
var G__13484 = cljs.core.rest(rest_start_nodes);
starting_node = G__13483;
rest_start_nodes = G__13484;
continue;

}
}
break;
}
});
/**
 * Generate random graphs up to 'max-tries times, returning only when
 *   we've given up, or when we've generated a connected graph.
 */
traveling_game.graph.generate_connected_graph = (function traveling_game$graph$generate_connected_graph(max_tries,directed_QMARK_,connectivity_prob,cost_min,cost_max,node_count){
var attempt_counter = (0);
while(true){
if((attempt_counter < max_tries)){
var g = traveling_game.graph.nodes__GT_graph(connectivity_prob,cost_min,cost_max,traveling_game.graph.select_nodes(node_count));
var g__$1 = (cljs.core.truth_(directed_QMARK_)?g:traveling_game.graph.graph__GT_undirected(g));
if(cljs.core.truth_(traveling_game.graph.connected_QMARK_(g__$1))){
return g__$1;
} else {
var G__13485 = (attempt_counter + (1));
attempt_counter = G__13485;
continue;
}
} else {
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["ERROR: Failed to generate connected graph in",max_tries,"tries"], 0));

return null;
}
break;
}
});
/**
 * Turn the given tuples graph into the same graph represented by a
 *   map from 2-tuples representing the direction of the edge to the cost
 *   of that edge: {[city-a, city-b] 43
 *               [city-b, city-c] 86}
 * 
 *   E.g.:
 *   (graph->map [["London" "Omaha" 4]
 *             ["London" "Medaryville" 4]
 *             ["Omaha" "Medaryville" 2]
 *             ["Medaryville" "Omaha" 4]])
 *   {["London" "Omaha"] 4,
 * ["London" "Medaryville"] 4,
 * ["Omaha" "Medaryville"] 2,
 * ["Medaryville" "Omaha"] 4}
 *   
 */
traveling_game.graph.graph__GT_map = (function traveling_game$graph$graph__GT_map(graph){
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (r,p__13490){
var vec__13491 = p__13490;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13491,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13491,(1),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13491,(2),null);
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(r,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [a,b], null),cost);
}),cljs.core.PersistentArrayMap.EMPTY,graph);
});
/**
 * Add up the lengths of the edges specified in the provided 'path
 *   through the provided graph, which must be in the form returned by
 *   'graph->map.
 */
traveling_game.graph.score = (function traveling_game$graph$score(graph_map,path){
var path__$1 = cljs.core.partition.cljs$core$IFn$_invoke$arity$3((2),(1),path);
var s = cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (path__$1){
return (function (r,hop){
return (r + (function (){var or__6543__auto__ = (function (){var G__13497 = cljs.core.vec(hop);
return (graph_map.cljs$core$IFn$_invoke$arity$1 ? graph_map.cljs$core$IFn$_invoke$arity$1(G__13497) : graph_map.call(null,G__13497));
})();
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (0);
}
})());
});})(path__$1))
,(0),path__$1);
return s;
});
/**
 * Return true if evey hop in the provided 'path is valid within the
 *   provided graph, which must be in the form returned by 'graph->map.
 */
traveling_game.graph.hops_valid_QMARK_ = (function traveling_game$graph$hops_valid_QMARK_(graph_map,path){
return cljs.core.every_QMARK_(graph_map,cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.vec,cljs.core.partition.cljs$core$IFn$_invoke$arity$3((2),(1),path)));
});
/**
 * Find all valid paths through the given 'graph, and score each
 *   one. How else will you know which one is best? Go, traveling
 *   salesperson, go!
 */
traveling_game.graph.score_all_paths = (function traveling_game$graph$score_all_paths(graph){
var all_nodes = traveling_game.graph.graph__GT_nodes(graph);
var all_paths = clojure.math.combinatorics.permutations(all_nodes);
var all_scores = cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (all_nodes,all_paths){
return (function (r,p){
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(r,p,traveling_game.graph.score(traveling_game.graph.graph__GT_map(graph),p));
});})(all_nodes,all_paths))
,cljs.core.PersistentArrayMap.EMPTY,cljs.core.filter.cljs$core$IFn$_invoke$arity$2(((function (all_nodes,all_paths){
return (function (p1__13498_SHARP_){
return traveling_game.graph.hops_valid_QMARK_(traveling_game.graph.graph__GT_map(graph),p1__13498_SHARP_);
});})(all_nodes,all_paths))
,all_paths));
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.cst$kw$scored_DASH_paths_DASH_count,cljs.core.count(all_scores)], 0));

return all_scores;
});
/**
 * Find the shortest path through the given 'graph, by adding up all
 *   the available paths in the graph. This is what real traveling
 *   salespeople do.
 */
traveling_game.graph.graph__GT_shortest_path = (function traveling_game$graph$graph__GT_shortest_path(graph){
return cljs.core.first(cljs.core.sort_by.cljs$core$IFn$_invoke$arity$2(cljs.core.second,traveling_game.graph.score_all_paths(graph)));
});
