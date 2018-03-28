// Compiled by ClojureScript 1.9.293 {:static-fns true, :optimize-constants true, :elide-asserts true}
goog.provide('traveling_game.core');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('cljs.pprint');
goog.require('traveling_game.graph');
goog.require('clojure.math.combinatorics');
goog.require('clojure.string');
cljs.core.enable_console_print_BANG_();
/**
 * We'll handle almost anything the user cares to throw at us, and we
 *   eat spaces and commas just like Clojure does!
 */
traveling_game.core.parse_path = (function traveling_game$core$parse_path(path){
return clojure.string.split.cljs$core$IFn$_invoke$arity$2(path,/[ ,]+/);
});
/**
 * This was a really quick hack to munge a graph into a format like
 *   the one used in a D3 force-directed layout example I found.
 */
traveling_game.core.graph__GT_d3_format = (function traveling_game$core$graph__GT_d3_format(graph){
var nodes = traveling_game.graph.graph__GT_nodes(graph);
return new cljs.core.PersistentArrayMap(null, 2, ["nodes",cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(((function (nodes){
return (function (p1__16185_SHARP_){
return cljs.core.PersistentHashMap.fromArrays(["id","group"],[p1__16185_SHARP_,cljs.core.rand_int((20))]);
});})(nodes))
,nodes),"links",cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(((function (nodes){
return (function (p__16197){
var vec__16198 = p__16197;
var ab = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16198,(0),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16198,(1),null);
var vec__16201 = cljs.core.vec(ab);
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16201,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16201,(1),null);
return cljs.core.PersistentHashMap.fromArrays(["source","target","value"],[a,b,cost]);
});})(nodes))
,cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (nodes){
return (function (r,p__16204){
var vec__16205 = p__16204;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16205,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16205,(1),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16205,(2),null);
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(r,cljs.core.PersistentHashSet.fromArray([a,b], true),cost);
});})(nodes))
,cljs.core.PersistentArrayMap.EMPTY,graph))], null);
});
traveling_game.core.default_node_count = (6);
traveling_game.core.max_node_count = (8);
traveling_game.core.edge_cost_min = (1);
traveling_game.core.edge_cost_max = (100);
if(typeof traveling_game.core.app_state !== 'undefined'){
} else {
traveling_game.core.app_state = reagent.core.atom.cljs$core$IFn$_invoke$arity$1(new cljs.core.PersistentArrayMap(null, 6, [cljs.core.cst$kw$graph,null,cljs.core.cst$kw$proposed_DASH_path,null,cljs.core.cst$kw$node_DASH_count,traveling_game.core.default_node_count,cljs.core.cst$kw$shortest_DASH_path,null,cljs.core.cst$kw$show_DASH_shortest_DASH_path_QMARK_,false,cljs.core.cst$kw$show_DASH_shortest_DASH_score_QMARK_,false], null));
}
/**
 * I borrowed this code from the Internet. It's handy for checkboxes.
 */
traveling_game.core.toggle = (function traveling_game$core$toggle(id){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(traveling_game.core.app_state,cljs.core.update,id,cljs.core.not);
});
/**
 * Display a text input box, in which the user can type the names of
 *   visited nodes to attempt a solution.
 */
traveling_game.core.proposed_solution = (function traveling_game$core$proposed_solution(){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"container"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$input,new cljs.core.PersistentArrayMap(null, 4, [cljs.core.cst$kw$type,"text",cljs.core.cst$kw$size,(86),cljs.core.cst$kw$placeholder,"Solution as: Name Name Name",cljs.core.cst$kw$on_DASH_change,(function (p1__16208_SHARP_){
var input = p1__16208_SHARP_.target.value;
cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(traveling_game.core.app_state,cljs.core.assoc,cljs.core.cst$kw$proposed_DASH_path,traveling_game.core.parse_path(input));

return p1__16208_SHARP_.target.value = input;
})], null)], null)], null);
});
/**
 * Display an individual graph node as a textual list item.
 */
traveling_game.core.graph_node = (function traveling_game$core$graph_node(p__16209){
var vec__16213 = p__16209;
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16213,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16213,(1),null);
var cost = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16213,(2),null);
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$li,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$key,[cljs.core.str(a),cljs.core.str(b),cljs.core.str(cost)].join('')], null),[cljs.core.str(a),cljs.core.str(" -> "),cljs.core.str(b),cljs.core.str(" ("),cljs.core.str(cost),cljs.core.str(")")].join('')], null);
});
/**
 * Display a given graph textually.
 */
traveling_game.core.graph_display_text = (function traveling_game$core$graph_display_text(graph){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$ul,(function (){var iter__7326__auto__ = (function traveling_game$core$graph_display_text_$_iter__16222(s__16223){
return (new cljs.core.LazySeq(null,(function (){
var s__16223__$1 = s__16223;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__16223__$1);
if(temp__4657__auto__){
var s__16223__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__16223__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__16223__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__16225 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__16224 = (0);
while(true){
if((i__16224 < size__7325__auto__)){
var node = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__16224);
cljs.core.chunk_append(b__16225,traveling_game.core.graph_node(node));

var G__16228 = (i__16224 + (1));
i__16224 = G__16228;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__16225),traveling_game$core$graph_display_text_$_iter__16222(cljs.core.chunk_rest(s__16223__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__16225),null);
}
} else {
var node = cljs.core.first(s__16223__$2);
return cljs.core.cons(traveling_game.core.graph_node(node),traveling_game$core$graph_display_text_$_iter__16222(cljs.core.rest(s__16223__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__7326__auto__(graph);
})()], null);
});
/**
 * Display an SVG. This was an experiment that I didn't get to finish to my satisfaction..
 */
traveling_game.core.graph_display_fdg_element = (function traveling_game$core$graph_display_fdg_element(){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div$svg_DASH_container,new cljs.core.PersistentArrayMap(null, 2, [cljs.core.cst$kw$class,"container",cljs.core.cst$kw$id,"graph-container"], null)], null);
});
/**
 * Display some controls to reset the graph, toggle some display elements, etc.
 */
traveling_game.core.ui_controls = (function traveling_game$core$ui_controls(){
var num_cities_val = reagent.core.atom.cljs$core$IFn$_invoke$arity$1(null);
return ((function (num_cities_val){
return (function (){
var map__16233 = (cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(traveling_game.core.app_state) : cljs.core.deref.call(null,traveling_game.core.app_state));
var map__16233__$1 = ((((!((map__16233 == null)))?((((map__16233.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__16233.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__16233):map__16233);
var node_count = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16233__$1,cljs.core.cst$kw$node_DASH_count);
return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"container"], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"input-group mb-3"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$dev,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"input-group-prepend"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$button,new cljs.core.PersistentArrayMap(null, 3, [cljs.core.cst$kw$type,"button",cljs.core.cst$kw$class,"btn btn-secondary",cljs.core.cst$kw$on_DASH_click,((function (map__16233,map__16233__$1,node_count,num_cities_val){
return (function (){
var g = traveling_game.graph.generate_connected_graph((10),true,0.6,traveling_game.core.edge_cost_min,traveling_game.core.edge_cost_max,node_count);
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$variadic(traveling_game.core.app_state,cljs.core.assoc,cljs.core.cst$kw$graph,g,cljs.core.array_seq([cljs.core.cst$kw$shortest_DASH_path,traveling_game.graph.graph__GT_shortest_path(g)], 0));
});})(map__16233,map__16233__$1,node_count,num_cities_val))
], null),"Reset"], null)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$input,new cljs.core.PersistentArrayMap(null, 6, [cljs.core.cst$kw$type,"text",cljs.core.cst$kw$id,"number-of-cities",cljs.core.cst$kw$class,"form-control",cljs.core.cst$kw$placeholder,[cljs.core.str("Number of cities (max "),cljs.core.str(traveling_game.core.max_node_count),cljs.core.str(", default "),cljs.core.str(traveling_game.core.default_node_count),cljs.core.str(")")].join(''),cljs.core.cst$kw$value,(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(num_cities_val) : cljs.core.deref.call(null,num_cities_val)),cljs.core.cst$kw$on_DASH_change,((function (map__16233,map__16233__$1,node_count,num_cities_val){
return (function (p1__16229_SHARP_){
var input = (function (){var G__16235 = p1__16229_SHARP_.target.value;
return parseInt(G__16235);
})();
var newval = (((cljs.core.int_QMARK_(input)) && (((0) < input)))?(function (){var x__6881__auto__ = input;
var y__6882__auto__ = traveling_game.core.max_node_count;
return ((x__6881__auto__ < y__6882__auto__) ? x__6881__auto__ : y__6882__auto__);
})():"");
(cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(num_cities_val,newval) : cljs.core.reset_BANG_.call(null,num_cities_val,newval));

return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(traveling_game.core.app_state,cljs.core.assoc,cljs.core.cst$kw$node_DASH_count,(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(num_cities_val) : cljs.core.deref.call(null,num_cities_val)));
});})(map__16233,map__16233__$1,node_count,num_cities_val))
], null)], null)], null),new cljs.core.PersistentVector(null, 6, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"form-check form-check-inline"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$input,new cljs.core.PersistentArrayMap(null, 4, [cljs.core.cst$kw$class,"form-check-input",cljs.core.cst$kw$type,"checkbox",cljs.core.cst$kw$id,"show-shortest-score",cljs.core.cst$kw$on_DASH_change,((function (map__16233,map__16233__$1,node_count,num_cities_val){
return (function (){
return traveling_game.core.toggle(cljs.core.cst$kw$show_DASH_shortest_DASH_score_QMARK_);
});})(map__16233,map__16233__$1,node_count,num_cities_val))
], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$label,new cljs.core.PersistentArrayMap(null, 2, [cljs.core.cst$kw$class,"form-check-label",cljs.core.cst$kw$for,"show-shortest-score"], null),"Show best possible score"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$input,new cljs.core.PersistentArrayMap(null, 4, [cljs.core.cst$kw$class,"form-check-input",cljs.core.cst$kw$type,"checkbox",cljs.core.cst$kw$id,"show-shortest-path",cljs.core.cst$kw$on_DASH_change,((function (map__16233,map__16233__$1,node_count,num_cities_val){
return (function (){
return traveling_game.core.toggle(cljs.core.cst$kw$show_DASH_shortest_DASH_path_QMARK_);
});})(map__16233,map__16233__$1,node_count,num_cities_val))
], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$label,new cljs.core.PersistentArrayMap(null, 2, [cljs.core.cst$kw$class,"form-check-label",cljs.core.cst$kw$for,"show-shortest-path"], null),"Show best possible path"], null)], null)], null);
});
;})(num_cities_val))
});
/**
 * Display the proposed path as it has been parsed, the nodes
 *   remaining to be visited, and maybe the best-possible score and the
 *   best-possible path.
 */
traveling_game.core.proposed_path_score = (function traveling_game$core$proposed_path_score(){
var map__16241 = cljs.core.select_keys((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(traveling_game.core.app_state) : cljs.core.deref.call(null,traveling_game.core.app_state)),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$proposed_DASH_path,cljs.core.cst$kw$graph,cljs.core.cst$kw$shortest_DASH_path,cljs.core.cst$kw$show_DASH_shortest_DASH_path_QMARK_,cljs.core.cst$kw$show_DASH_shortest_DASH_score_QMARK_], null));
var map__16241__$1 = ((((!((map__16241 == null)))?((((map__16241.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__16241.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__16241):map__16241);
var proposed_path = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16241__$1,cljs.core.cst$kw$proposed_DASH_path);
var graph = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16241__$1,cljs.core.cst$kw$graph);
var shortest_path = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16241__$1,cljs.core.cst$kw$shortest_DASH_path);
var show_shortest_path_QMARK_ = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16241__$1,cljs.core.cst$kw$show_DASH_shortest_DASH_path_QMARK_);
var show_shortest_score_QMARK_ = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__16241__$1,cljs.core.cst$kw$show_DASH_shortest_DASH_score_QMARK_);
var nodes = traveling_game.graph.graph__GT_nodes(graph);
var vec__16242 = shortest_path;
var shortest_path__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16242,(0),null);
var shortest_score = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__16242,(1),null);
var score = (cljs.core.truth_(proposed_path)?traveling_game.graph.score(traveling_game.graph.graph__GT_map(graph),proposed_path):null);
var remaining_cities = (cljs.core.truth_(proposed_path)?(function (){var visited = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentHashSet.EMPTY,proposed_path);
return cljs.core.filter.cljs$core$IFn$_invoke$arity$2(cljs.core.complement(visited),nodes);
})():nodes);
return new cljs.core.PersistentVector(null, 6, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$p,"Parsed proposed path:",clojure.string.join.cljs$core$IFn$_invoke$arity$2(" ",proposed_path)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$p,"Cities remaining: ",clojure.string.join.cljs$core$IFn$_invoke$arity$2(" ",remaining_cities)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$p,[cljs.core.str("Score: "),cljs.core.str(score)].join('')], null),(cljs.core.truth_((function (){var and__6531__auto__ = show_shortest_score_QMARK_;
if(cljs.core.truth_(and__6531__auto__)){
return shortest_score;
} else {
return and__6531__auto__;
}
})())?new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$p,[cljs.core.str("Best possible score: "),cljs.core.str(shortest_score)].join('')], null):null),(cljs.core.truth_((function (){var and__6531__auto__ = show_shortest_path_QMARK_;
if(cljs.core.truth_(and__6531__auto__)){
return shortest_path__$1;
} else {
return and__6531__auto__;
}
})())?new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$p,[cljs.core.str("Best possible path: "),cljs.core.str(shortest_path__$1)].join('')], null):null)], null);
});
traveling_game.core.page = (function traveling_game$core$page(ratom){

var graph_render_hack = reagent.core.atom.cljs$core$IFn$_invoke$arity$1(null);
return ((function (graph_render_hack){
return (function (){
var current_graph = cljs.core.cst$kw$graph.cljs$core$IFn$_invoke$arity$1((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(ratom) : cljs.core.deref.call(null,ratom)));
return new cljs.core.PersistentVector(null, 8, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$div,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.cst$kw$class,"container"], null),"Welcome to reagent-pigwheel.",new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [traveling_game.core.ui_controls], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [traveling_game.core.graph_display_text,current_graph], null),(cljs.core.truth_((function (){var and__6531__auto__ = false;
if(and__6531__auto__){
var and__6531__auto____$1 = current_graph;
if(cljs.core.truth_(and__6531__auto____$1)){
return cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2(current_graph,(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(graph_render_hack) : cljs.core.deref.call(null,graph_render_hack)));
} else {
return and__6531__auto____$1;
}
} else {
return and__6531__auto__;
}
})())?(function (){
(cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(graph_render_hack,current_graph) : cljs.core.reset_BANG_.call(null,graph_render_hack,current_graph));

var G__16247 = cljs.core.clj__GT_js(traveling_game.core.graph__GT_d3_format(current_graph));
return fdg_go(G__16247);
})()
:null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [traveling_game.core.proposed_solution], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [traveling_game.core.proposed_path_score], null)], null);
});
;})(graph_render_hack))
});
traveling_game.core.dev_setup = (function traveling_game$core$dev_setup(){
if(goog.DEBUG){
cljs.core.enable_console_print_BANG_();

return cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["dev mode"], 0));
} else {
return null;
}
});
traveling_game.core.reload = (function traveling_game$core$reload(){
return reagent.core.render.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [traveling_game.core.page,traveling_game.core.app_state], null),document.getElementById("app"));
});
traveling_game.core.main = (function traveling_game$core$main(){
traveling_game.core.dev_setup();

return traveling_game.core.reload();
});
goog.exportSymbol('traveling_game.core.main', traveling_game.core.main);
