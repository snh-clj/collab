// Compiled by ClojureScript 1.9.293 {:static-fns true, :optimize-constants true, :elide-asserts true}
goog.provide('reagent.debug');
goog.require('cljs.core');
reagent.debug.has_console = typeof console !== 'undefined';
reagent.debug.tracking = false;
if(typeof reagent.debug.warnings !== 'undefined'){
} else {
reagent.debug.warnings = (cljs.core.atom.cljs$core$IFn$_invoke$arity$1 ? cljs.core.atom.cljs$core$IFn$_invoke$arity$1(null) : cljs.core.atom.call(null,null));
}
if(typeof reagent.debug.track_console !== 'undefined'){
} else {
reagent.debug.track_console = (function (){var o = ({});
o.warn = ((function (o){
return (function() { 
var G__13600__delegate = function (args){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$variadic(reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$warn], null),cljs.core.conj,cljs.core.array_seq([cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.str,args)], 0));
};
var G__13600 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__13601__i = 0, G__13601__a = new Array(arguments.length -  0);
while (G__13601__i < G__13601__a.length) {G__13601__a[G__13601__i] = arguments[G__13601__i + 0]; ++G__13601__i;}
  args = new cljs.core.IndexedSeq(G__13601__a,0);
} 
return G__13600__delegate.call(this,args);};
G__13600.cljs$lang$maxFixedArity = 0;
G__13600.cljs$lang$applyTo = (function (arglist__13602){
var args = cljs.core.seq(arglist__13602);
return G__13600__delegate(args);
});
G__13600.cljs$core$IFn$_invoke$arity$variadic = G__13600__delegate;
return G__13600;
})()
;})(o))
;

o.error = ((function (o){
return (function() { 
var G__13603__delegate = function (args){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$variadic(reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.cst$kw$error], null),cljs.core.conj,cljs.core.array_seq([cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.str,args)], 0));
};
var G__13603 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__13604__i = 0, G__13604__a = new Array(arguments.length -  0);
while (G__13604__i < G__13604__a.length) {G__13604__a[G__13604__i] = arguments[G__13604__i + 0]; ++G__13604__i;}
  args = new cljs.core.IndexedSeq(G__13604__a,0);
} 
return G__13603__delegate.call(this,args);};
G__13603.cljs$lang$maxFixedArity = 0;
G__13603.cljs$lang$applyTo = (function (arglist__13605){
var args = cljs.core.seq(arglist__13605);
return G__13603__delegate(args);
});
G__13603.cljs$core$IFn$_invoke$arity$variadic = G__13603__delegate;
return G__13603;
})()
;})(o))
;

return o;
})();
}
reagent.debug.track_warnings = (function reagent$debug$track_warnings(f){
reagent.debug.tracking = true;

(cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(reagent.debug.warnings,null) : cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null));

(f.cljs$core$IFn$_invoke$arity$0 ? f.cljs$core$IFn$_invoke$arity$0() : f.call(null));

var warns = (cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(reagent.debug.warnings) : cljs.core.deref.call(null,reagent.debug.warnings));
(cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(reagent.debug.warnings,null) : cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null));

reagent.debug.tracking = false;

return warns;
});
