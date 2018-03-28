// Compiled by ClojureScript 1.9.293 {:static-fns true, :optimize-constants true, :elide-asserts true}
goog.provide('clojure.math.combinatorics');
goog.require('cljs.core');
clojure.math.combinatorics._STAR__SINGLEQUOTE_ = cljs.core._STAR_;
clojure.math.combinatorics._PLUS__SINGLEQUOTE_ = cljs.core._PLUS_;
/**
 * Annoyingly, the built-in distinct? doesn't handle 0 args, so we need
 * to write our own version that considers the empty-list to be distinct
 */
clojure.math.combinatorics.all_different_QMARK_ = (function clojure$math$combinatorics$all_different_QMARK_(s){
if(cljs.core.seq(s)){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.distinct_QMARK_,s);
} else {
return true;
}
});
clojure.math.combinatorics.index_combinations = (function clojure$math$combinatorics$index_combinations(n,cnt){
return (new cljs.core.LazySeq(null,(function (){
var c = cljs.core.vec(cljs.core.cons(null,(function (){var iter__7326__auto__ = (function clojure$math$combinatorics$index_combinations_$_iter__12542(s__12543){
return (new cljs.core.LazySeq(null,(function (){
var s__12543__$1 = s__12543;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12543__$1);
if(temp__4657__auto__){
var s__12543__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12543__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12543__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12545 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12544 = (0);
while(true){
if((i__12544 < size__7325__auto__)){
var j = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12544);
cljs.core.chunk_append(b__12545,((j + cnt) + (- (n + (1)))));

var G__12550 = (i__12544 + (1));
i__12544 = G__12550;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12545),clojure$math$combinatorics$index_combinations_$_iter__12542(cljs.core.chunk_rest(s__12543__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12545),null);
}
} else {
var j = cljs.core.first(s__12543__$2);
return cljs.core.cons(((j + cnt) + (- (n + (1)))),clojure$math$combinatorics$index_combinations_$_iter__12542(cljs.core.rest(s__12543__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2((1),(n + (1))));
})()));
var iter_comb = ((function (c){
return (function clojure$math$combinatorics$index_combinations_$_iter_comb(c__$1,j){
if((j > n)){
return null;
} else {
var c__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(c__$1,j,((c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(j) : c__$1.call(null,j)) - (1)));
if(((c__$2.cljs$core$IFn$_invoke$arity$1 ? c__$2.cljs$core$IFn$_invoke$arity$1(j) : c__$2.call(null,j)) < j)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [c__$2,(j + (1))], null);
} else {
var c__$3 = c__$2;
var j__$1 = j;
while(true){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(j__$1,(1))){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [c__$3,j__$1], null);
} else {
var G__12551 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(c__$3,(j__$1 - (1)),((c__$3.cljs$core$IFn$_invoke$arity$1 ? c__$3.cljs$core$IFn$_invoke$arity$1(j__$1) : c__$3.call(null,j__$1)) - (1)));
var G__12552 = (j__$1 - (1));
c__$3 = G__12551;
j__$1 = G__12552;
continue;
}
break;
}
}
}
});})(c))
;
var step = ((function (c,iter_comb){
return (function clojure$math$combinatorics$index_combinations_$_step(c__$1,j){
return cljs.core.cons(cljs.core.rseq(cljs.core.subvec.cljs$core$IFn$_invoke$arity$3(c__$1,(1),(n + (1)))),(new cljs.core.LazySeq(null,((function (c,iter_comb){
return (function (){
var next_step = iter_comb(c__$1,j);
if(cljs.core.truth_(next_step)){
return clojure$math$combinatorics$index_combinations_$_step((next_step.cljs$core$IFn$_invoke$arity$1 ? next_step.cljs$core$IFn$_invoke$arity$1((0)) : next_step.call(null,(0))),(next_step.cljs$core$IFn$_invoke$arity$1 ? next_step.cljs$core$IFn$_invoke$arity$1((1)) : next_step.call(null,(1))));
} else {
return null;
}
});})(c,iter_comb))
,null,null)));
});})(c,iter_comb))
;
return step(c,(1));
}),null,null));
});
clojure.math.combinatorics.distribute = (function clojure$math$combinatorics$distribute(m,index,total,distribution,already_distributed){
var distribution__$1 = distribution;
var index__$1 = index;
var already_distributed__$1 = already_distributed;
while(true){
if((index__$1 >= cljs.core.count(m))){
return null;
} else {
var quantity_to_distribute = (total - already_distributed__$1);
var mi = (m.cljs$core$IFn$_invoke$arity$1 ? m.cljs$core$IFn$_invoke$arity$1(index__$1) : m.call(null,index__$1));
if((quantity_to_distribute <= mi)){
return cljs.core.conj.cljs$core$IFn$_invoke$arity$2(distribution__$1,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [index__$1,quantity_to_distribute,total], null));
} else {
var G__12553 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(distribution__$1,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [index__$1,mi,(already_distributed__$1 + mi)], null));
var G__12554 = (index__$1 + (1));
var G__12555 = (already_distributed__$1 + mi);
distribution__$1 = G__12553;
index__$1 = G__12554;
already_distributed__$1 = G__12555;
continue;
}
}
break;
}
});
clojure.math.combinatorics.next_distribution = (function clojure$math$combinatorics$next_distribution(m,total,distribution){
var vec__12562 = cljs.core.peek(distribution);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12562,(0),null);
var this_bucket = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12562,(1),null);
var this_and_to_the_left = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12562,(2),null);
if((index < (cljs.core.count(m) - (1)))){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this_bucket,(1))){
return cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.pop(distribution),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(index + (1)),(1),this_and_to_the_left], null));
} else {
return cljs.core.conj.cljs$core$IFn$_invoke$arity$variadic(cljs.core.pop(distribution),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [index,(this_bucket - (1)),(this_and_to_the_left - (1))], null),cljs.core.array_seq([new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(index + (1)),(1),this_and_to_the_left], null)], 0));
}
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this_bucket,total)){
return null;
} else {
var distribution__$1 = cljs.core.pop(distribution);
while(true){
var vec__12565 = cljs.core.peek(distribution__$1);
var index__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12565,(0),null);
var this_bucket__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12565,(1),null);
var this_and_to_the_left__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12565,(2),null);
var distribution__$2 = ((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this_bucket__$1,(1)))?cljs.core.pop(distribution__$1):cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.pop(distribution__$1),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [index__$1,(this_bucket__$1 - (1)),(this_and_to_the_left__$1 - (1))], null)));
if(((total - (this_and_to_the_left__$1 - (1))) <= cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,cljs.core.subvec.cljs$core$IFn$_invoke$arity$2(m,(index__$1 + (1)))))){
return clojure.math.combinatorics.distribute(m,(index__$1 + (1)),total,distribution__$2,(this_and_to_the_left__$1 - (1)));
} else {
if(cljs.core.seq(distribution__$2)){
var G__12568 = distribution__$2;
distribution__$1 = G__12568;
continue;
} else {
return null;

}
}
break;
}

}
}
});
clojure.math.combinatorics.bounded_distributions = (function clojure$math$combinatorics$bounded_distributions(m,t){
var step = (function clojure$math$combinatorics$bounded_distributions_$_step(distribution){
return cljs.core.cons(distribution,(new cljs.core.LazySeq(null,(function (){
var temp__4657__auto__ = clojure.math.combinatorics.next_distribution(m,t,distribution);
if(cljs.core.truth_(temp__4657__auto__)){
var next_step = temp__4657__auto__;
return clojure$math$combinatorics$bounded_distributions_$_step(next_step);
} else {
return null;
}
}),null,null)));
});
return step(clojure.math.combinatorics.distribute(m,(0),t,cljs.core.PersistentVector.EMPTY,(0)));
});
/**
 * Handles the case when you want the combinations of a list with duplicate items.
 */
clojure.math.combinatorics.multi_comb = (function clojure$math$combinatorics$multi_comb(l,t){
var f = cljs.core.frequencies(l);
var v = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(l));
var domain = cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v));
var m = cljs.core.vec((function (){var iter__7326__auto__ = ((function (f,v,domain){
return (function clojure$math$combinatorics$multi_comb_$_iter__12657(s__12658){
return (new cljs.core.LazySeq(null,((function (f,v,domain){
return (function (){
var s__12658__$1 = s__12658;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12658__$1);
if(temp__4657__auto__){
var s__12658__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12658__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12658__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12660 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12659 = (0);
while(true){
if((i__12659 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12659);
cljs.core.chunk_append(b__12660,(function (){var G__12665 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12665) : f.call(null,G__12665));
})());

var G__12745 = (i__12659 + (1));
i__12659 = G__12745;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12660),clojure$math$combinatorics$multi_comb_$_iter__12657(cljs.core.chunk_rest(s__12658__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12660),null);
}
} else {
var i = cljs.core.first(s__12658__$2);
return cljs.core.cons((function (){var G__12666 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12666) : f.call(null,G__12666));
})(),clojure$math$combinatorics$multi_comb_$_iter__12657(cljs.core.rest(s__12658__$2)));
}
} else {
return null;
}
break;
}
});})(f,v,domain))
,null,null));
});})(f,v,domain))
;
return iter__7326__auto__(domain);
})());
var qs = clojure.math.combinatorics.bounded_distributions(m,t);
var iter__7326__auto__ = ((function (f,v,domain,m,qs){
return (function clojure$math$combinatorics$multi_comb_$_iter__12667(s__12668){
return (new cljs.core.LazySeq(null,((function (f,v,domain,m,qs){
return (function (){
var s__12668__$1 = s__12668;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12668__$1);
if(temp__4657__auto__){
var s__12668__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12668__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12668__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12670 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12669 = (0);
while(true){
if((i__12669 < size__7325__auto__)){
var q = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12669);
cljs.core.chunk_append(b__12670,cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (i__12669,q,c__7324__auto__,size__7325__auto__,b__12670,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs){
return (function clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12709(s__12710){
return (new cljs.core.LazySeq(null,((function (i__12669,q,c__7324__auto__,size__7325__auto__,b__12670,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs){
return (function (){
var s__12710__$1 = s__12710;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__12710__$1);
if(temp__4657__auto____$1){
var s__12710__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__12710__$2)){
var c__7324__auto____$1 = cljs.core.chunk_first(s__12710__$2);
var size__7325__auto____$1 = cljs.core.count(c__7324__auto____$1);
var b__12712 = cljs.core.chunk_buffer(size__7325__auto____$1);
if((function (){var i__12711 = (0);
while(true){
if((i__12711 < size__7325__auto____$1)){
var vec__12721 = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto____$1,i__12711);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12721,(0),null);
var this_bucket = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12721,(1),null);
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12721,(2),null);
cljs.core.chunk_append(b__12712,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(this_bucket,(v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(index) : v.call(null,index))));

var G__12746 = (i__12711 + (1));
i__12711 = G__12746;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12712),clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12709(cljs.core.chunk_rest(s__12710__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12712),null);
}
} else {
var vec__12724 = cljs.core.first(s__12710__$2);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12724,(0),null);
var this_bucket = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12724,(1),null);
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12724,(2),null);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(this_bucket,(v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(index) : v.call(null,index))),clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12709(cljs.core.rest(s__12710__$2)));
}
} else {
return null;
}
break;
}
});})(i__12669,q,c__7324__auto__,size__7325__auto__,b__12670,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs))
,null,null));
});})(i__12669,q,c__7324__auto__,size__7325__auto__,b__12670,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs))
;
return iter__7326__auto__(q);
})()));

var G__12747 = (i__12669 + (1));
i__12669 = G__12747;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12670),clojure$math$combinatorics$multi_comb_$_iter__12667(cljs.core.chunk_rest(s__12668__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12670),null);
}
} else {
var q = cljs.core.first(s__12668__$2);
return cljs.core.cons(cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (q,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs){
return (function clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12727(s__12728){
return (new cljs.core.LazySeq(null,((function (q,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs){
return (function (){
var s__12728__$1 = s__12728;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__12728__$1);
if(temp__4657__auto____$1){
var s__12728__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__12728__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12728__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12730 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12729 = (0);
while(true){
if((i__12729 < size__7325__auto__)){
var vec__12739 = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12729);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12739,(0),null);
var this_bucket = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12739,(1),null);
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12739,(2),null);
cljs.core.chunk_append(b__12730,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(this_bucket,(v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(index) : v.call(null,index))));

var G__12748 = (i__12729 + (1));
i__12729 = G__12748;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12730),clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12727(cljs.core.chunk_rest(s__12728__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12730),null);
}
} else {
var vec__12742 = cljs.core.first(s__12728__$2);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12742,(0),null);
var this_bucket = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12742,(1),null);
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12742,(2),null);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(this_bucket,(v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(index) : v.call(null,index))),clojure$math$combinatorics$multi_comb_$_iter__12667_$_iter__12727(cljs.core.rest(s__12728__$2)));
}
} else {
return null;
}
break;
}
});})(q,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs))
,null,null));
});})(q,s__12668__$2,temp__4657__auto__,f,v,domain,m,qs))
;
return iter__7326__auto__(q);
})()),clojure$math$combinatorics$multi_comb_$_iter__12667(cljs.core.rest(s__12668__$2)));
}
} else {
return null;
}
break;
}
});})(f,v,domain,m,qs))
,null,null));
});})(f,v,domain,m,qs))
;
return iter__7326__auto__(qs);
});
/**
 * All the unique ways of taking t different elements from items
 */
clojure.math.combinatorics.combinations = (function clojure$math$combinatorics$combinations(items,t){
var v_items = cljs.core.vec(cljs.core.reverse(items));
if((t === (0))){
var x__7380__auto__ = cljs.core.List.EMPTY;
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
} else {
var cnt = cljs.core.count(items);
if((t > cnt)){
return null;
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(t,(1))){
var iter__7326__auto__ = ((function (cnt,v_items){
return (function clojure$math$combinatorics$combinations_$_iter__12756(s__12757){
return (new cljs.core.LazySeq(null,((function (cnt,v_items){
return (function (){
var s__12757__$1 = s__12757;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12757__$1);
if(temp__4657__auto__){
var s__12757__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12757__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12757__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12759 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12758 = (0);
while(true){
if((i__12758 < size__7325__auto__)){
var item = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12758);
cljs.core.chunk_append(b__12759,(function (){var x__7380__auto__ = item;
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})());

var G__12762 = (i__12758 + (1));
i__12758 = G__12762;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12759),clojure$math$combinatorics$combinations_$_iter__12756(cljs.core.chunk_rest(s__12757__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12759),null);
}
} else {
var item = cljs.core.first(s__12757__$2);
return cljs.core.cons((function (){var x__7380__auto__ = item;
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})(),clojure$math$combinatorics$combinations_$_iter__12756(cljs.core.rest(s__12757__$2)));
}
} else {
return null;
}
break;
}
});})(cnt,v_items))
,null,null));
});})(cnt,v_items))
;
return iter__7326__auto__(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(items));
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(t,cnt)){
var x__7380__auto__ = cljs.core.seq(items);
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
} else {
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(((function (cnt,v_items){
return (function (p1__12749_SHARP_){
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(v_items,p1__12749_SHARP_);
});})(cnt,v_items))
,clojure.math.combinatorics.index_combinations(t,cnt));
}
} else {
return clojure.math.combinatorics.multi_comb(items,t);

}
}
}
}
});
/**
 * Given a sequence that may have chunks, return a sequence that is 1-at-a-time
 * lazy with no chunks. Chunks are good for efficiency when the data items are
 * small, but when being processed via map, for example, a reference is kept to
 * every function result in the chunk until the entire chunk has been processed,
 * which increases the amount of memory in use that cannot be garbage
 * collected.
 */
clojure.math.combinatorics.unchunk = (function clojure$math$combinatorics$unchunk(s){
return (new cljs.core.LazySeq(null,(function (){
if(cljs.core.seq(s)){
return cljs.core.cons(cljs.core.first(s),(function (){var G__12764 = cljs.core.rest(s);
return (clojure.math.combinatorics.unchunk.cljs$core$IFn$_invoke$arity$1 ? clojure.math.combinatorics.unchunk.cljs$core$IFn$_invoke$arity$1(G__12764) : clojure.math.combinatorics.unchunk.call(null,G__12764));
})());
} else {
return null;
}
}),null,null));
});
/**
 * All the subsets of items
 */
clojure.math.combinatorics.subsets = (function clojure$math$combinatorics$subsets(items){
return cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic((function (n){
return clojure.math.combinatorics.combinations(items,n);
}),cljs.core.array_seq([clojure.math.combinatorics.unchunk(cljs.core.range.cljs$core$IFn$_invoke$arity$1((cljs.core.count(items) + (1))))], 0));
});
/**
 * All the ways to take one item from each sequence
 */
clojure.math.combinatorics.cartesian_product = (function clojure$math$combinatorics$cartesian_product(var_args){
var args__7654__auto__ = [];
var len__7647__auto___12767 = arguments.length;
var i__7648__auto___12768 = (0);
while(true){
if((i__7648__auto___12768 < len__7647__auto___12767)){
args__7654__auto__.push((arguments[i__7648__auto___12768]));

var G__12769 = (i__7648__auto___12768 + (1));
i__7648__auto___12768 = G__12769;
continue;
} else {
}
break;
}

var argseq__7655__auto__ = ((((0) < args__7654__auto__.length))?(new cljs.core.IndexedSeq(args__7654__auto__.slice((0)),(0),null)):null);
return clojure.math.combinatorics.cartesian_product.cljs$core$IFn$_invoke$arity$variadic(argseq__7655__auto__);
});

clojure.math.combinatorics.cartesian_product.cljs$core$IFn$_invoke$arity$variadic = (function (seqs){
var v_original_seqs = cljs.core.vec(seqs);
var step = ((function (v_original_seqs){
return (function clojure$math$combinatorics$step(v_seqs){
var increment = ((function (v_original_seqs){
return (function (v_seqs__$1){
var i = (cljs.core.count(v_seqs__$1) - (1));
var v_seqs__$2 = v_seqs__$1;
while(true){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(i,(-1))){
return null;
} else {
var temp__4655__auto__ = cljs.core.next((v_seqs__$2.cljs$core$IFn$_invoke$arity$1 ? v_seqs__$2.cljs$core$IFn$_invoke$arity$1(i) : v_seqs__$2.call(null,i)));
if(temp__4655__auto__){
var rst = temp__4655__auto__;
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v_seqs__$2,i,rst);
} else {
var G__12770 = (i - (1));
var G__12771 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v_seqs__$2,i,(v_original_seqs.cljs$core$IFn$_invoke$arity$1 ? v_original_seqs.cljs$core$IFn$_invoke$arity$1(i) : v_original_seqs.call(null,i)));
i = G__12770;
v_seqs__$2 = G__12771;
continue;
}
}
break;
}
});})(v_original_seqs))
;
if(cljs.core.truth_(v_seqs)){
return cljs.core.cons(cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.first,v_seqs),(new cljs.core.LazySeq(null,((function (increment,v_original_seqs){
return (function (){
return clojure$math$combinatorics$step(increment(v_seqs));
});})(increment,v_original_seqs))
,null,null)));
} else {
return null;
}
});})(v_original_seqs))
;
if(cljs.core.every_QMARK_(cljs.core.seq,seqs)){
return (new cljs.core.LazySeq(null,((function (v_original_seqs,step){
return (function (){
return step(v_original_seqs);
});})(v_original_seqs,step))
,null,null));
} else {
return null;
}
});

clojure.math.combinatorics.cartesian_product.cljs$lang$maxFixedArity = (0);

clojure.math.combinatorics.cartesian_product.cljs$lang$applyTo = (function (seq12765){
return clojure.math.combinatorics.cartesian_product.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq(seq12765));
});

/**
 * All the ways of taking n (possibly the same) elements from the sequence of items
 */
clojure.math.combinatorics.selections = (function clojure$math$combinatorics$selections(items,n){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(clojure.math.combinatorics.cartesian_product,cljs.core.take.cljs$core$IFn$_invoke$arity$2(n,cljs.core.repeat.cljs$core$IFn$_invoke$arity$1(items)));
});
clojure.math.combinatorics.iter_perm = (function clojure$math$combinatorics$iter_perm(v){
var len = cljs.core.count(v);
var j = (function (){var i = (len - (2));
while(true){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(i,(-1))){
return null;
} else {
if(((v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i)) < (function (){var G__12773 = (i + (1));
return (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(G__12773) : v.call(null,G__12773));
})())){
return i;
} else {
var G__12774 = (i - (1));
i = G__12774;
continue;

}
}
break;
}
})();
if(cljs.core.truth_(j)){
var vj = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(j) : v.call(null,j));
var l = (function (){var i = (len - (1));
while(true){
if((vj < (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i)))){
return i;
} else {
var G__12775 = (i - (1));
i = G__12775;
continue;
}
break;
}
})();
var v__$1 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$variadic(v,j,(v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(l) : v.call(null,l)),cljs.core.array_seq([l,vj], 0));
var k = (j + (1));
var l__$1 = (len - (1));
while(true){
if((k < l__$1)){
var G__12776 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$variadic(v__$1,k,(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(l__$1) : v__$1.call(null,l__$1)),cljs.core.array_seq([l__$1,(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(k) : v__$1.call(null,k))], 0));
var G__12777 = (k + (1));
var G__12778 = (l__$1 - (1));
v__$1 = G__12776;
k = G__12777;
l__$1 = G__12778;
continue;
} else {
return v__$1;
}
break;
}
} else {
return null;
}
});
clojure.math.combinatorics.vec_lex_permutations = (function clojure$math$combinatorics$vec_lex_permutations(v){
if(cljs.core.truth_(v)){
return cljs.core.cons(v,(new cljs.core.LazySeq(null,(function (){
var G__12780 = clojure.math.combinatorics.iter_perm(v);
return (clojure.math.combinatorics.vec_lex_permutations.cljs$core$IFn$_invoke$arity$1 ? clojure.math.combinatorics.vec_lex_permutations.cljs$core$IFn$_invoke$arity$1(G__12780) : clojure.math.combinatorics.vec_lex_permutations.call(null,G__12780));
}),null,null)));
} else {
return null;
}
});
/**
 * DEPRECATED as a public function.
 * 
 * In prior versions of the combinatorics library, there were two similar functions: permutations and lex-permutations.  It was a source of confusion to know which to call.  Now, you can always call permutations.  When appropriate (i.e., when you pass in a sorted sequence of numbers), permutations will automatically call lex-permutations as a speed optimization.
 */
clojure.math.combinatorics.lex_permutations = (function clojure$math$combinatorics$lex_permutations(c){
return (new cljs.core.LazySeq(null,(function (){
var vec_sorted = cljs.core.vec(cljs.core.sort.cljs$core$IFn$_invoke$arity$1(c));
if((cljs.core.count(vec_sorted) === (0))){
var x__7380__auto__ = cljs.core.PersistentVector.EMPTY;
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
} else {
return clojure.math.combinatorics.vec_lex_permutations(vec_sorted);
}
}),null,null));
});
/**
 * Returns true iff s is a sequence of numbers in non-decreasing order
 */
clojure.math.combinatorics.sorted_numbers_QMARK_ = (function clojure$math$combinatorics$sorted_numbers_QMARK_(s){
var and__6531__auto__ = cljs.core.every_QMARK_(cljs.core.number_QMARK_,s);
if(and__6531__auto__){
var or__6543__auto__ = cljs.core.empty_QMARK_(s);
if(or__6543__auto__){
return or__6543__auto__;
} else {
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._LT__EQ_,s);
}
} else {
return and__6531__auto__;
}
});
/**
 * Handles the case when you want the permutations of a list with duplicate items.
 */
clojure.math.combinatorics.multi_perm = (function clojure$math$combinatorics$multi_perm(l){
var f = cljs.core.frequencies(l);
var v = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(l));
var indices = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (f,v){
return (function clojure$math$combinatorics$multi_perm_$_iter__12791(s__12792){
return (new cljs.core.LazySeq(null,((function (f,v){
return (function (){
var s__12792__$1 = s__12792;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12792__$1);
if(temp__4657__auto__){
var s__12792__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12792__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12792__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12794 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12793 = (0);
while(true){
if((i__12793 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12793);
cljs.core.chunk_append(b__12794,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12799 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12799) : f.call(null,G__12799));
})(),i));

var G__12801 = (i__12793 + (1));
i__12793 = G__12801;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12794),clojure$math$combinatorics$multi_perm_$_iter__12791(cljs.core.chunk_rest(s__12792__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12794),null);
}
} else {
var i = cljs.core.first(s__12792__$2);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12800 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12800) : f.call(null,G__12800));
})(),i),clojure$math$combinatorics$multi_perm_$_iter__12791(cljs.core.rest(s__12792__$2)));
}
} else {
return null;
}
break;
}
});})(f,v))
,null,null));
});})(f,v))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v)));
})());
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.partial.cljs$core$IFn$_invoke$arity$2(cljs.core.map,v),clojure.math.combinatorics.lex_permutations(indices));
});
/**
 * All the distinct permutations of items, lexicographic by index 
 * (special handling for duplicate items).
 */
clojure.math.combinatorics.permutations = (function clojure$math$combinatorics$permutations(items){
if(cljs.core.truth_(clojure.math.combinatorics.sorted_numbers_QMARK_(items))){
return clojure.math.combinatorics.lex_permutations(items);
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
var v = cljs.core.vec(items);
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(((function (v){
return (function (p1__12802_SHARP_){
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(v,p1__12802_SHARP_);
});})(v))
,clojure.math.combinatorics.lex_permutations(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v))));
} else {
return clojure.math.combinatorics.multi_perm(items);

}
}
});
clojure.math.combinatorics.factorial = (function clojure$math$combinatorics$factorial(n){


var acc = (1);
var n__$1 = n;
while(true){
if((n__$1 === (0))){
return acc;
} else {
var G__12803 = (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(acc,n__$1) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,acc,n__$1));
var G__12804 = (n__$1 - (1));
acc = G__12803;
n__$1 = G__12804;
continue;
}
break;
}
});
/**
 * Input is a non-negative base 10 integer, output is the number in the
 * factorial number system (http://en.wikipedia.org/wiki/Factorial_number_system)
 * expressed as a list of 'digits'
 */
clojure.math.combinatorics.factorial_numbers = (function clojure$math$combinatorics$factorial_numbers(n){


var n__$1 = n;
var digits = cljs.core.List.EMPTY;
var divisor = (1);
while(true){
if((n__$1 === (0))){
return digits;
} else {
var q = cljs.core.quot(n__$1,divisor);
var r = cljs.core.rem(n__$1,divisor);
var G__12805 = q;
var G__12806 = cljs.core.cons(r,digits);
var G__12807 = (divisor + (1));
n__$1 = G__12805;
digits = G__12806;
divisor = G__12807;
continue;
}
break;
}
});
clojure.math.combinatorics.remove_nth = (function clojure$math$combinatorics$remove_nth(l,n){
var acc = cljs.core.PersistentVector.EMPTY;
var l__$1 = l;
var n__$1 = n;
while(true){
if((n__$1 === (0))){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(acc,cljs.core.rest(l__$1));
} else {
var G__12808 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(acc,cljs.core.first(l__$1));
var G__12809 = cljs.core.rest(l__$1);
var G__12810 = (n__$1 - (1));
acc = G__12808;
l__$1 = G__12809;
n__$1 = G__12810;
continue;
}
break;
}
});
/**
 * Input should be a sorted sequential collection l of distinct items, 
 * output is nth-permutation (0-based)
 */
clojure.math.combinatorics.nth_permutation_distinct = (function clojure$math$combinatorics$nth_permutation_distinct(l,n){

var length = cljs.core.count(l);
var fact_nums = clojure.math.combinatorics.factorial_numbers(n);
var indices = cljs.core.concat.cljs$core$IFn$_invoke$arity$2(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((length - cljs.core.count(fact_nums)),(0)),fact_nums);
var l__$1 = l;
var perm = cljs.core.PersistentVector.EMPTY;
while(true){
if(cljs.core.empty_QMARK_(indices)){
return perm;
} else {
var i = cljs.core.first(indices);
var item = cljs.core.nth.cljs$core$IFn$_invoke$arity$2(l__$1,i);
var G__12811 = cljs.core.rest(indices);
var G__12812 = clojure.math.combinatorics.remove_nth(l__$1,i);
var G__12813 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(perm,item);
indices = G__12811;
l__$1 = G__12812;
perm = G__12813;
continue;
}
break;
}
});
clojure.math.combinatorics.count_permutations_from_frequencies = (function clojure$math$combinatorics$count_permutations_from_frequencies(freqs){
var counts = cljs.core.vals(freqs);
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._SLASH_,clojure.math.combinatorics.factorial(cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,counts)),cljs.core.map.cljs$core$IFn$_invoke$arity$2(clojure.math.combinatorics.factorial,counts));
});
/**
 * Counts the number of distinct permutations of l
 */
clojure.math.combinatorics.count_permutations = (function clojure$math$combinatorics$count_permutations(l){
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(l))){
return clojure.math.combinatorics.factorial(cljs.core.count(l));
} else {
return clojure.math.combinatorics.count_permutations_from_frequencies(cljs.core.frequencies(l));
}
});
/**
 * Takes a sorted frequency map and returns how far into the sequence of
 * lexicographic permutations you get by varying the first item
 */
clojure.math.combinatorics.initial_perm_numbers = (function clojure$math$combinatorics$initial_perm_numbers(freqs){
return cljs.core.reductions.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics._PLUS__SINGLEQUOTE_,(0),(function (){var iter__7326__auto__ = (function clojure$math$combinatorics$initial_perm_numbers_$_iter__12832(s__12833){
return (new cljs.core.LazySeq(null,(function (){
var s__12833__$1 = s__12833;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12833__$1);
if(temp__4657__auto__){
var s__12833__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12833__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12833__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12835 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12834 = (0);
while(true){
if((i__12834 < size__7325__auto__)){
var vec__12844 = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12834);
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12844,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12844,(1),null);
cljs.core.chunk_append(b__12835,clojure.math.combinatorics.count_permutations_from_frequencies(cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(freqs,k,(v - (1)))));

var G__12850 = (i__12834 + (1));
i__12834 = G__12850;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12835),clojure$math$combinatorics$initial_perm_numbers_$_iter__12832(cljs.core.chunk_rest(s__12833__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12835),null);
}
} else {
var vec__12847 = cljs.core.first(s__12833__$2);
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12847,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12847,(1),null);
return cljs.core.cons(clojure.math.combinatorics.count_permutations_from_frequencies(cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(freqs,k,(v - (1)))),clojure$math$combinatorics$initial_perm_numbers_$_iter__12832(cljs.core.rest(s__12833__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__7326__auto__(freqs);
})());
});
/**
 * Finds the index and remainder from the initial-perm-numbers.
 */
clojure.math.combinatorics.index_remainder = (function clojure$math$combinatorics$index_remainder(perm_numbers,n){
var perm_numbers__$1 = perm_numbers;
var index = (0);
while(true){
if(((cljs.core.first(perm_numbers__$1) <= n)) && ((n < cljs.core.second(perm_numbers__$1)))){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [index,(n - cljs.core.first(perm_numbers__$1))], null);
} else {
var G__12851 = cljs.core.rest(perm_numbers__$1);
var G__12852 = (index + (1));
perm_numbers__$1 = G__12851;
index = G__12852;
continue;
}
break;
}
});
clojure.math.combinatorics.dec_key = (function clojure$math$combinatorics$dec_key(m,k){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((1),(m.cljs$core$IFn$_invoke$arity$1 ? m.cljs$core$IFn$_invoke$arity$1(k) : m.call(null,k)))){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(m,k);
} else {
return cljs.core.update_in.cljs$core$IFn$_invoke$arity$3(m,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [k], null),cljs.core.dec);
}
});
/**
 * Input is a non-negative base 10 integer n, and a sorted frequency map freqs.
 * Output is a list of 'digits' in this wacky duplicate factorial number system
 */
clojure.math.combinatorics.factorial_numbers_with_duplicates = (function clojure$math$combinatorics$factorial_numbers_with_duplicates(n,freqs){
var n__$1 = n;
var digits = cljs.core.PersistentVector.EMPTY;
var freqs__$1 = freqs;
while(true){
if((n__$1 === (0))){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(digits,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,cljs.core.vals(freqs__$1)),(0)));
} else {
var vec__12856 = clojure.math.combinatorics.index_remainder(clojure.math.combinatorics.initial_perm_numbers(freqs__$1),n__$1);
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12856,(0),null);
var remainder = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12856,(1),null);
var G__12859 = remainder;
var G__12860 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(digits,index);
var G__12861 = (function (){var nth_key = cljs.core.nth.cljs$core$IFn$_invoke$arity$2(cljs.core.keys(freqs__$1),index);
return clojure.math.combinatorics.dec_key(freqs__$1,nth_key);
})();
n__$1 = G__12859;
digits = G__12860;
freqs__$1 = G__12861;
continue;
}
break;
}
});
/**
 * Input should be a sorted sequential collection l of distinct items, 
 * output is nth-permutation (0-based)
 */
clojure.math.combinatorics.nth_permutation_duplicates = (function clojure$math$combinatorics$nth_permutation_duplicates(l,n){

var freqs = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.sorted_map(),cljs.core.frequencies(l));
var indices = clojure.math.combinatorics.factorial_numbers_with_duplicates(n,freqs);
var perm = cljs.core.PersistentVector.EMPTY;
while(true){
if(cljs.core.empty_QMARK_(indices)){
return perm;
} else {
var i = cljs.core.first(indices);
var item = cljs.core.nth.cljs$core$IFn$_invoke$arity$2(cljs.core.keys(freqs),i);
var G__12862 = clojure.math.combinatorics.dec_key(freqs,item);
var G__12863 = cljs.core.rest(indices);
var G__12864 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(perm,item);
freqs = G__12862;
indices = G__12863;
perm = G__12864;
continue;
}
break;
}
});
/**
 * (nth (permutations items)) but calculated more directly.
 */
clojure.math.combinatorics.nth_permutation = (function clojure$math$combinatorics$nth_permutation(items,n){
if(cljs.core.truth_(clojure.math.combinatorics.sorted_numbers_QMARK_(items))){
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.nth_permutation_distinct(items,n);
} else {
return clojure.math.combinatorics.nth_permutation_duplicates(items,n);
}
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
var v = cljs.core.vec(items);
var perm_indices = clojure.math.combinatorics.nth_permutation_distinct(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(items)),n);
return cljs.core.vec(cljs.core.map.cljs$core$IFn$_invoke$arity$2(v,perm_indices));
} else {
var v = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(items));
var f = cljs.core.frequencies(items);
var indices = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (v,f){
return (function clojure$math$combinatorics$nth_permutation_$_iter__12875(s__12876){
return (new cljs.core.LazySeq(null,((function (v,f){
return (function (){
var s__12876__$1 = s__12876;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12876__$1);
if(temp__4657__auto__){
var s__12876__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12876__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12876__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12878 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12877 = (0);
while(true){
if((i__12877 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12877);
cljs.core.chunk_append(b__12878,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12883 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12883) : f.call(null,G__12883));
})(),i));

var G__12885 = (i__12877 + (1));
i__12877 = G__12885;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12878),clojure$math$combinatorics$nth_permutation_$_iter__12875(cljs.core.chunk_rest(s__12876__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12878),null);
}
} else {
var i = cljs.core.first(s__12876__$2);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12884 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12884) : f.call(null,G__12884));
})(),i),clojure$math$combinatorics$nth_permutation_$_iter__12875(cljs.core.rest(s__12876__$2)));
}
} else {
return null;
}
break;
}
});})(v,f))
,null,null));
});})(v,f))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v)));
})());
return cljs.core.vec(cljs.core.map.cljs$core$IFn$_invoke$arity$2(v,clojure.math.combinatorics.nth_permutation_duplicates(indices,n)));
}
}
});
/**
 * (drop n (permutations items)) but calculated more directly.
 */
clojure.math.combinatorics.drop_permutations = (function clojure$math$combinatorics$drop_permutations(items,n){
if((n === (0))){
return clojure.math.combinatorics.permutations(items);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(n,clojure.math.combinatorics.count_permutations(items))){
return cljs.core.List.EMPTY;
} else {
if(cljs.core.truth_(clojure.math.combinatorics.sorted_numbers_QMARK_(items))){
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.vec_lex_permutations(clojure.math.combinatorics.nth_permutation_distinct(items,n));
} else {
return clojure.math.combinatorics.vec_lex_permutations(clojure.math.combinatorics.nth_permutation_duplicates(items,n));
}
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
var v = cljs.core.vec(items);
var perm_indices = clojure.math.combinatorics.nth_permutation_distinct(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(items)),n);
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.partial.cljs$core$IFn$_invoke$arity$2(cljs.core.map,v),clojure.math.combinatorics.vec_lex_permutations(perm_indices));
} else {
var v = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(items));
var f = cljs.core.frequencies(items);
var indices = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (v,f){
return (function clojure$math$combinatorics$drop_permutations_$_iter__12896(s__12897){
return (new cljs.core.LazySeq(null,((function (v,f){
return (function (){
var s__12897__$1 = s__12897;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12897__$1);
if(temp__4657__auto__){
var s__12897__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12897__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12897__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12899 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12898 = (0);
while(true){
if((i__12898 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12898);
cljs.core.chunk_append(b__12899,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12904 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12904) : f.call(null,G__12904));
})(),i));

var G__12906 = (i__12898 + (1));
i__12898 = G__12906;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12899),clojure$math$combinatorics$drop_permutations_$_iter__12896(cljs.core.chunk_rest(s__12897__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12899),null);
}
} else {
var i = cljs.core.first(s__12897__$2);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__12905 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__12905) : f.call(null,G__12905));
})(),i),clojure$math$combinatorics$drop_permutations_$_iter__12896(cljs.core.rest(s__12897__$2)));
}
} else {
return null;
}
break;
}
});})(v,f))
,null,null));
});})(v,f))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v)));
})());
return cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.partial.cljs$core$IFn$_invoke$arity$2(cljs.core.map,v),clojure.math.combinatorics.vec_lex_permutations(clojure.math.combinatorics.nth_permutation_duplicates(indices,n)));
}
}

}
}
});
clojure.math.combinatorics.n_take_k = (function clojure$math$combinatorics$n_take_k(n,k){
while(true){
if((k < (0))){
return (0);
} else {
if((k > n)){
return (0);
} else {
if((k === (0))){
return (1);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(k,(1))){
return n;
} else {
if((k > cljs.core.quot(n,(2)))){
var G__12907 = n;
var G__12908 = (n - k);
n = G__12907;
k = G__12908;
continue;
} else {
return (cljs.core.apply.cljs$core$IFn$_invoke$arity$2(clojure.math.combinatorics._STAR__SINGLEQUOTE_,cljs.core.range.cljs$core$IFn$_invoke$arity$2(((n - k) + (1)),(n + (1)))) / cljs.core.apply.cljs$core$IFn$_invoke$arity$2(clojure.math.combinatorics._STAR__SINGLEQUOTE_,cljs.core.range.cljs$core$IFn$_invoke$arity$2((1),(k + (1)))));

}
}
}
}
}
break;
}
});
clojure.math.combinatorics.count_combinations_from_frequencies = (function clojure$math$combinatorics$count_combinations_from_frequencies(freqs,t){
var counts = cljs.core.vals(freqs);
var sum = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,counts);
if((t === (0))){
return (1);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(t,(1))){
return cljs.core.count(freqs);
} else {
if(cljs.core.every_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 1, [(1),null], null), null),counts)){
return clojure.math.combinatorics.n_take_k(cljs.core.count(freqs),t);
} else {
if((t > sum)){
return (0);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(t,sum)){
return (1);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.count(freqs),(1))){
return (1);
} else {
var new_freqs = clojure.math.combinatorics.dec_key(freqs,cljs.core.first(cljs.core.keys(freqs)));
var G__12915 = (function (){var G__12917 = new_freqs;
var G__12918 = (t - (1));
return (clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2(G__12917,G__12918) : clojure.math.combinatorics.count_combinations_from_frequencies.call(null,G__12917,G__12918));
})();
var G__12916 = (function (){var G__12919 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(freqs,cljs.core.first(cljs.core.keys(freqs)));
var G__12920 = t;
return (clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2(G__12919,G__12920) : clojure.math.combinatorics.count_combinations_from_frequencies.call(null,G__12919,G__12920));
})();
return (clojure.math.combinatorics._PLUS__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._PLUS__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(G__12915,G__12916) : clojure.math.combinatorics._PLUS__SINGLEQUOTE_.call(null,G__12915,G__12916));

}
}
}
}
}
}
});
/**
 * We need an internal version that doesn't memoize each call to count-combinations-from-frequencies
 * so that we can memoize over a series of calls.
 */
clojure.math.combinatorics.count_combinations_unmemoized = (function clojure$math$combinatorics$count_combinations_unmemoized(items,t){
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.n_take_k(cljs.core.count(items),t);
} else {
return (clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2(cljs.core.frequencies(items),t) : clojure.math.combinatorics.count_combinations_from_frequencies.call(null,cljs.core.frequencies(items),t));
}
});
/**
 * (count (combinations items t)) but computed more directly
 */
clojure.math.combinatorics.count_combinations = (function clojure$math$combinatorics$count_combinations(items,t){
var count_combinations_from_frequencies12922 = clojure.math.combinatorics.count_combinations_from_frequencies;
clojure.math.combinatorics.count_combinations_from_frequencies = cljs.core.memoize(clojure.math.combinatorics.count_combinations_from_frequencies);

try{return clojure.math.combinatorics.count_combinations_unmemoized(items,t);
}finally {clojure.math.combinatorics.count_combinations_from_frequencies = count_combinations_from_frequencies12922;
}});
clojure.math.combinatorics.expt_int = (function clojure$math$combinatorics$expt_int(base,pow){
var n = pow;
var y = (1);
var z = base;
while(true){
var t = cljs.core.even_QMARK_(n);
var n__$1 = cljs.core.quot(n,(2));
if(t){
var G__12923 = n__$1;
var G__12924 = y;
var G__12925 = (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(z,z) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,z,z));
n = G__12923;
y = G__12924;
z = G__12925;
continue;
} else {
if((n__$1 === (0))){
return (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(z,y) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,z,y));
} else {
var G__12926 = n__$1;
var G__12927 = (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(z,y) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,z,y));
var G__12928 = (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(z,z) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,z,z));
n = G__12926;
y = G__12927;
z = G__12928;
continue;

}
}
break;
}
});
clojure.math.combinatorics.count_subsets_unmemoized = (function clojure$math$combinatorics$count_subsets_unmemoized(items){
if(cljs.core.empty_QMARK_(items)){
return (1);
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.expt_int((2),cljs.core.count(items));
} else {
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(clojure.math.combinatorics._PLUS__SINGLEQUOTE_,(function (){var iter__7326__auto__ = (function clojure$math$combinatorics$count_subsets_unmemoized_$_iter__12935(s__12936){
return (new cljs.core.LazySeq(null,(function (){
var s__12936__$1 = s__12936;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12936__$1);
if(temp__4657__auto__){
var s__12936__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12936__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12936__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12938 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12937 = (0);
while(true){
if((i__12937 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12937);
cljs.core.chunk_append(b__12938,clojure.math.combinatorics.count_combinations_unmemoized(items,i));

var G__12941 = (i__12937 + (1));
i__12937 = G__12941;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12938),clojure$math$combinatorics$count_subsets_unmemoized_$_iter__12935(cljs.core.chunk_rest(s__12936__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12938),null);
}
} else {
var i = cljs.core.first(s__12936__$2);
return cljs.core.cons(clojure.math.combinatorics.count_combinations_unmemoized(items,i),clojure$math$combinatorics$count_subsets_unmemoized_$_iter__12935(cljs.core.rest(s__12936__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2((0),(cljs.core.count(items) + (1))));
})());

}
}
});
/**
 * (count (subsets items)) but computed more directly
 */
clojure.math.combinatorics.count_subsets = (function clojure$math$combinatorics$count_subsets(items){
var count_combinations_from_frequencies12943 = clojure.math.combinatorics.count_combinations_from_frequencies;
clojure.math.combinatorics.count_combinations_from_frequencies = cljs.core.memoize(clojure.math.combinatorics.count_combinations_from_frequencies);

try{return clojure.math.combinatorics.count_subsets_unmemoized(items);
}finally {clojure.math.combinatorics.count_combinations_from_frequencies = count_combinations_from_frequencies12943;
}});
/**
 * The nth element of the sequence of t-combinations of items,
 * where items is a collection of distinct elements
 */
clojure.math.combinatorics.nth_combination_distinct = (function clojure$math$combinatorics$nth_combination_distinct(items,t,n){
var comb = cljs.core.PersistentVector.EMPTY;
var items__$1 = items;
var t__$1 = t;
var n__$1 = n;
while(true){
if(((n__$1 === (0))) || (cljs.core.empty_QMARK_(items__$1))){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(comb,cljs.core.take.cljs$core$IFn$_invoke$arity$2(t__$1,items__$1));
} else {
var dc_dt = clojure.math.combinatorics.n_take_k((cljs.core.count(items__$1) - (1)),(t__$1 - (1)));
if((n__$1 < dc_dt)){
var G__12944 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(comb,cljs.core.first(items__$1));
var G__12945 = cljs.core.rest(items__$1);
var G__12946 = (t__$1 - (1));
var G__12947 = n__$1;
comb = G__12944;
items__$1 = G__12945;
t__$1 = G__12946;
n__$1 = G__12947;
continue;
} else {
var G__12948 = comb;
var G__12949 = cljs.core.rest(items__$1);
var G__12950 = t__$1;
var G__12951 = (n__$1 - dc_dt);
comb = G__12948;
items__$1 = G__12949;
t__$1 = G__12950;
n__$1 = G__12951;
continue;
}
}
break;
}
});
/**
 * The nth element of the sequence of t-combinations of the multiset
 * represented by freqs
 */
clojure.math.combinatorics.nth_combination_freqs = (function clojure$math$combinatorics$nth_combination_freqs(freqs,t,n){
var comb = cljs.core.PersistentVector.EMPTY;
var freqs__$1 = freqs;
var t__$1 = t;
var n__$1 = n;
while(true){
if(((n__$1 === (0))) || (cljs.core.empty_QMARK_(freqs__$1))){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(comb,cljs.core.take.cljs$core$IFn$_invoke$arity$2(t__$1,cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (comb,freqs__$1,t__$1,n__$1){
return (function clojure$math$combinatorics$nth_combination_freqs_$_iter__12970(s__12971){
return (new cljs.core.LazySeq(null,((function (comb,freqs__$1,t__$1,n__$1){
return (function (){
var s__12971__$1 = s__12971;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__12971__$1);
if(temp__4657__auto__){
var s__12971__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__12971__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__12971__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__12973 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__12972 = (0);
while(true){
if((i__12972 < size__7325__auto__)){
var vec__12982 = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__12972);
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12982,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12982,(1),null);
cljs.core.chunk_append(b__12973,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(v,k));

var G__12988 = (i__12972 + (1));
i__12972 = G__12988;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__12973),clojure$math$combinatorics$nth_combination_freqs_$_iter__12970(cljs.core.chunk_rest(s__12971__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__12973),null);
}
} else {
var vec__12985 = cljs.core.first(s__12971__$2);
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12985,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__12985,(1),null);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(v,k),clojure$math$combinatorics$nth_combination_freqs_$_iter__12970(cljs.core.rest(s__12971__$2)));
}
} else {
return null;
}
break;
}
});})(comb,freqs__$1,t__$1,n__$1))
,null,null));
});})(comb,freqs__$1,t__$1,n__$1))
;
return iter__7326__auto__(freqs__$1);
})())));
} else {
var first_key = cljs.core.first(cljs.core.keys(freqs__$1));
var remove_one_key = clojure.math.combinatorics.dec_key(freqs__$1,first_key);
var dc_dt = (clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics.count_combinations_from_frequencies.cljs$core$IFn$_invoke$arity$2(remove_one_key,(t__$1 - (1))) : clojure.math.combinatorics.count_combinations_from_frequencies.call(null,remove_one_key,(t__$1 - (1))));
if((n__$1 < dc_dt)){
var G__12989 = cljs.core.conj.cljs$core$IFn$_invoke$arity$2(comb,first_key);
var G__12990 = remove_one_key;
var G__12991 = (t__$1 - (1));
var G__12992 = n__$1;
comb = G__12989;
freqs__$1 = G__12990;
t__$1 = G__12991;
n__$1 = G__12992;
continue;
} else {
var G__12993 = comb;
var G__12994 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(freqs__$1,first_key);
var G__12995 = t__$1;
var G__12996 = (n__$1 - dc_dt);
comb = G__12993;
freqs__$1 = G__12994;
t__$1 = G__12995;
n__$1 = G__12996;
continue;
}
}
break;
}
});
/**
 * The nth element of the sequence of t-combinations of items
 */
clojure.math.combinatorics.nth_combination = (function clojure$math$combinatorics$nth_combination(items,t,n){

if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.nth_combination_distinct(items,t,n);
} else {
var count_combinations_from_frequencies13008 = clojure.math.combinatorics.count_combinations_from_frequencies;
clojure.math.combinatorics.count_combinations_from_frequencies = cljs.core.memoize(clojure.math.combinatorics.count_combinations_from_frequencies);

try{var v = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(items));
var f = cljs.core.frequencies(items);
var indices = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.concat,(function (){var iter__7326__auto__ = ((function (v,f,count_combinations_from_frequencies13008){
return (function clojure$math$combinatorics$nth_combination_$_iter__13009(s__13010){
return (new cljs.core.LazySeq(null,((function (v,f,count_combinations_from_frequencies13008){
return (function (){
var s__13010__$1 = s__13010;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13010__$1);
if(temp__4657__auto__){
var s__13010__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13010__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13010__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13012 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13011 = (0);
while(true){
if((i__13011 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13011);
cljs.core.chunk_append(b__13012,cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__13017 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__13017) : f.call(null,G__13017));
})(),i));

var G__13019 = (i__13011 + (1));
i__13011 = G__13019;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13012),clojure$math$combinatorics$nth_combination_$_iter__13009(cljs.core.chunk_rest(s__13010__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13012),null);
}
} else {
var i = cljs.core.first(s__13010__$2);
return cljs.core.cons(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2((function (){var G__13018 = (v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(i) : v.call(null,i));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__13018) : f.call(null,G__13018));
})(),i),clojure$math$combinatorics$nth_combination_$_iter__13009(cljs.core.rest(s__13010__$2)));
}
} else {
return null;
}
break;
}
});})(v,f,count_combinations_from_frequencies13008))
,null,null));
});})(v,f,count_combinations_from_frequencies13008))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(v)));
})());
var indices_freqs = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.sorted_map(),cljs.core.frequencies(indices));
return cljs.core.vec(cljs.core.map.cljs$core$IFn$_invoke$arity$2(v,clojure.math.combinatorics.nth_combination_freqs(indices_freqs,t,n)));
}finally {clojure.math.combinatorics.count_combinations_from_frequencies = count_combinations_from_frequencies13008;
}}
});
clojure.math.combinatorics.nth_subset = (function clojure$math$combinatorics$nth_subset(items,n){

var size = (0);
var n__$1 = n;
while(true){
var num_combinations = clojure.math.combinatorics.count_combinations(items,size);
if((n__$1 < num_combinations)){
return clojure.math.combinatorics.nth_combination(items,size,n__$1);
} else {
var G__13020 = (size + (1));
var G__13021 = (n__$1 - num_combinations);
size = G__13020;
n__$1 = G__13021;
continue;
}
break;
}
});
/**
 * The opposite of nth, i.e., from an item in a list, find the n
 */
clojure.math.combinatorics.list_index = (function clojure$math$combinatorics$list_index(l,item){
var l__$1 = l;
var n = (0);
while(true){

if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(item,cljs.core.first(l__$1))){
return n;
} else {
var G__13022 = cljs.core.rest(l__$1);
var G__13023 = (n + (1));
l__$1 = G__13022;
n = G__13023;
continue;
}
break;
}
});
clojure.math.combinatorics.permutation_index_distinct = (function clojure$math$combinatorics$permutation_index_distinct(l){
var l__$1 = l;
var index = (0);
var n = (cljs.core.count(l__$1) - (1));
while(true){
if(cljs.core.empty_QMARK_(l__$1)){
return index;
} else {
var G__13032 = cljs.core.rest(l__$1);
var G__13033 = (function (){var G__13028 = index;
var G__13029 = (function (){var G__13030 = clojure.math.combinatorics.factorial(n);
var G__13031 = clojure.math.combinatorics.list_index(cljs.core.sort.cljs$core$IFn$_invoke$arity$1(l__$1),cljs.core.first(l__$1));
return (clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._STAR__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(G__13030,G__13031) : clojure.math.combinatorics._STAR__SINGLEQUOTE_.call(null,G__13030,G__13031));
})();
return (clojure.math.combinatorics._PLUS__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2 ? clojure.math.combinatorics._PLUS__SINGLEQUOTE_.cljs$core$IFn$_invoke$arity$2(G__13028,G__13029) : clojure.math.combinatorics._PLUS__SINGLEQUOTE_.call(null,G__13028,G__13029));
})();
var G__13034 = (n - (1));
l__$1 = G__13032;
index = G__13033;
n = G__13034;
continue;
}
break;
}
});
clojure.math.combinatorics.permutation_index_duplicates = (function clojure$math$combinatorics$permutation_index_duplicates(l){
var l__$1 = l;
var index = (0);
var freqs = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.sorted_map(),cljs.core.frequencies(l__$1));
while(true){
if(cljs.core.empty_QMARK_(l__$1)){
return index;
} else {
var G__13048 = cljs.core.rest(l__$1);
var G__13049 = cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics._PLUS__SINGLEQUOTE_,index,(function (){var iter__7326__auto__ = ((function (l__$1,index,freqs){
return (function clojure$math$combinatorics$permutation_index_duplicates_$_iter__13042(s__13043){
return (new cljs.core.LazySeq(null,((function (l__$1,index,freqs){
return (function (){
var s__13043__$1 = s__13043;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13043__$1);
if(temp__4657__auto__){
var s__13043__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13043__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13043__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13045 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13044 = (0);
while(true){
if((i__13044 < size__7325__auto__)){
var k = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13044);
cljs.core.chunk_append(b__13045,clojure.math.combinatorics.count_permutations_from_frequencies(clojure.math.combinatorics.dec_key(freqs,k)));

var G__13051 = (i__13044 + (1));
i__13044 = G__13051;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13045),clojure$math$combinatorics$permutation_index_duplicates_$_iter__13042(cljs.core.chunk_rest(s__13043__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13045),null);
}
} else {
var k = cljs.core.first(s__13043__$2);
return cljs.core.cons(clojure.math.combinatorics.count_permutations_from_frequencies(clojure.math.combinatorics.dec_key(freqs,k)),clojure$math$combinatorics$permutation_index_duplicates_$_iter__13042(cljs.core.rest(s__13043__$2)));
}
} else {
return null;
}
break;
}
});})(l__$1,index,freqs))
,null,null));
});})(l__$1,index,freqs))
;
return iter__7326__auto__(cljs.core.take_while.cljs$core$IFn$_invoke$arity$2(((function (l__$1,index,freqs,iter__7326__auto__){
return (function (p1__13035_SHARP_){
return (cljs.core.compare(p1__13035_SHARP_,cljs.core.first(l__$1)) < (0));
});})(l__$1,index,freqs,iter__7326__auto__))
,cljs.core.keys(freqs)));
})());
var G__13050 = clojure.math.combinatorics.dec_key(freqs,cljs.core.first(l__$1));
l__$1 = G__13048;
index = G__13049;
freqs = G__13050;
continue;
}
break;
}
});
/**
 * Input must be a sortable collection of items.  Returns the n such that
 *  (nth-permutation (sort items) n) is items.
 */
clojure.math.combinatorics.permutation_index = (function clojure$math$combinatorics$permutation_index(items){
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return clojure.math.combinatorics.permutation_index_distinct(items);
} else {
return clojure.math.combinatorics.permutation_index_duplicates(items);
}
});
clojure.math.combinatorics.update = (function clojure$math$combinatorics$update(vec,index,f){
var item = (vec.cljs$core$IFn$_invoke$arity$1 ? vec.cljs$core$IFn$_invoke$arity$1(index) : vec.call(null,index));
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(vec,index,(f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(item) : f.call(null,item)));
});
clojure.math.combinatorics.reify_bool = (function clojure$math$combinatorics$reify_bool(statement){
if(cljs.core.truth_(statement)){
return (1);
} else {
return (0);
}
});
clojure.math.combinatorics.init = (function clojure$math$combinatorics$init(n,s){
if(cljs.core.truth_(s)){
return cljs.core.vec((function (){var iter__7326__auto__ = (function clojure$math$combinatorics$init_$_iter__13058(s__13059){
return (new cljs.core.LazySeq(null,(function (){
var s__13059__$1 = s__13059;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13059__$1);
if(temp__4657__auto__){
var s__13059__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13059__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13059__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13061 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13060 = (0);
while(true){
if((i__13060 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13060);
cljs.core.chunk_append(b__13061,(function (){var x__6874__auto__ = (0);
var y__6875__auto__ = (i - ((n - s) - (-1)));
return ((x__6874__auto__ > y__6875__auto__) ? x__6874__auto__ : y__6875__auto__);
})());

var G__13064 = (i__13060 + (1));
i__13060 = G__13064;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13061),clojure$math$combinatorics$init_$_iter__13058(cljs.core.chunk_rest(s__13059__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13061),null);
}
} else {
var i = cljs.core.first(s__13059__$2);
return cljs.core.cons((function (){var x__6874__auto__ = (0);
var y__6875__auto__ = (i - ((n - s) - (-1)));
return ((x__6874__auto__ > y__6875__auto__) ? x__6874__auto__ : y__6875__auto__);
})(),clojure$math$combinatorics$init_$_iter__13058(cljs.core.rest(s__13059__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2((1),(n + (1))));
})());
} else {
return cljs.core.vec(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(n,(0)));
}
});
clojure.math.combinatorics.growth_strings_H = (function clojure$math$combinatorics$growth_strings_H(var_args){
var args13065 = [];
var len__7647__auto___13071 = arguments.length;
var i__7648__auto___13072 = (0);
while(true){
if((i__7648__auto___13072 < len__7647__auto___13071)){
args13065.push((arguments[i__7648__auto___13072]));

var G__13073 = (i__7648__auto___13072 + (1));
i__7648__auto___13072 = G__13073;
continue;
} else {
}
break;
}

var G__13067 = args13065.length;
switch (G__13067) {
case 3:
return clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 5:
return clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$5((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args13065.length)].join('')));

}
});

clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$3 = (function (n,r,s){
return clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$5(n,clojure.math.combinatorics.init(n,s),cljs.core.vec(cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(n,(1))),r,s);
});

clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$5 = (function (n,a,b,r,s){
return cljs.core.cons(a,(new cljs.core.LazySeq(null,(function (){
if((function (){var and__6531__auto__ = (cljs.core.peek(b) > cljs.core.peek(a));
if(and__6531__auto__){
if(cljs.core.truth_(r)){
return (cljs.core.peek(a) < (r - (1)));
} else {
return true;
}
} else {
return and__6531__auto__;
}
})()){
return clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$5(n,clojure.math.combinatorics.update(a,(n - (1)),cljs.core.inc),b,r,s);
} else {
var j = (function (){var j = (n - (2));
while(true){
if((function (){var and__6531__auto__ = ((a.cljs$core$IFn$_invoke$arity$1 ? a.cljs$core$IFn$_invoke$arity$1(j) : a.call(null,j)) < (b.cljs$core$IFn$_invoke$arity$1 ? b.cljs$core$IFn$_invoke$arity$1(j) : b.call(null,j)));
if(and__6531__auto__){
var and__6531__auto____$1 = (cljs.core.truth_(r)?((a.cljs$core$IFn$_invoke$arity$1 ? a.cljs$core$IFn$_invoke$arity$1(j) : a.call(null,j)) < (r - (1))):true);
if(and__6531__auto____$1){
if(cljs.core.truth_(s)){
return (((s - (b.cljs$core$IFn$_invoke$arity$1 ? b.cljs$core$IFn$_invoke$arity$1(j) : b.call(null,j))) - clojure.math.combinatorics.reify_bool((((a.cljs$core$IFn$_invoke$arity$1 ? a.cljs$core$IFn$_invoke$arity$1(j) : a.call(null,j)) + (1)) === (b.cljs$core$IFn$_invoke$arity$1 ? b.cljs$core$IFn$_invoke$arity$1(j) : b.call(null,j))))) <= (n - j));
} else {
return true;
}
} else {
return and__6531__auto____$1;
}
} else {
return and__6531__auto__;
}
})()){
return j;
} else {
var G__13075 = (j - (1));
j = G__13075;
continue;
}
break;
}
})();
if((j === (0))){
return cljs.core.List.EMPTY;
} else {
var a__$1 = clojure.math.combinatorics.update(a,j,cljs.core.inc);
var x = (cljs.core.truth_(s)?(s - ((b.cljs$core$IFn$_invoke$arity$1 ? b.cljs$core$IFn$_invoke$arity$1(j) : b.call(null,j)) + clojure.math.combinatorics.reify_bool(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((a__$1.cljs$core$IFn$_invoke$arity$1 ? a__$1.cljs$core$IFn$_invoke$arity$1(j) : a__$1.call(null,j)),(b.cljs$core$IFn$_invoke$arity$1 ? b.cljs$core$IFn$_invoke$arity$1(j) : b.call(null,j)))))):null);
var vec__13068 = (function (){var a__$2 = a__$1;
var b__$1 = b;
var i = (j + (1));
var current_max = ((b__$1.cljs$core$IFn$_invoke$arity$1 ? b__$1.cljs$core$IFn$_invoke$arity$1(j) : b__$1.call(null,j)) + clojure.math.combinatorics.reify_bool(((b__$1.cljs$core$IFn$_invoke$arity$1 ? b__$1.cljs$core$IFn$_invoke$arity$1(j) : b__$1.call(null,j)) === (a__$2.cljs$core$IFn$_invoke$arity$1 ? a__$2.cljs$core$IFn$_invoke$arity$1(j) : a__$2.call(null,j)))));
while(true){
if((i === n)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [a__$2,b__$1], null);
} else {
if(cljs.core.truth_((function (){var and__6531__auto__ = s;
if(cljs.core.truth_(and__6531__auto__)){
return (i > ((n - x) - (1)));
} else {
return and__6531__auto__;
}
})())){
var new_a_i = ((i - n) + s);
var G__13076 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(a__$2,i,new_a_i);
var G__13077 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(b__$1,i,current_max);
var G__13078 = (i + (1));
var G__13079 = (function (){var x__6874__auto__ = current_max;
var y__6875__auto__ = (new_a_i + (1));
return ((x__6874__auto__ > y__6875__auto__) ? x__6874__auto__ : y__6875__auto__);
})();
a__$2 = G__13076;
b__$1 = G__13077;
i = G__13078;
current_max = G__13079;
continue;
} else {
var G__13080 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(a__$2,i,(0));
var G__13081 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(b__$1,i,current_max);
var G__13082 = (i + (1));
var G__13083 = current_max;
a__$2 = G__13080;
b__$1 = G__13081;
i = G__13082;
current_max = G__13083;
continue;

}
}
break;
}
})();
var a__$2 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13068,(0),null);
var b__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13068,(1),null);
return clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$5(n,a__$2,b__$1,r,s);
}
}
}),null,null)));
});

clojure.math.combinatorics.growth_strings_H.cljs$lang$maxFixedArity = 5;

clojure.math.combinatorics.lex_partitions_H = (function clojure$math$combinatorics$lex_partitions_H(var_args){
var args__7654__auto__ = [];
var len__7647__auto___13095 = arguments.length;
var i__7648__auto___13096 = (0);
while(true){
if((i__7648__auto___13096 < len__7647__auto___13095)){
args__7654__auto__.push((arguments[i__7648__auto___13096]));

var G__13097 = (i__7648__auto___13096 + (1));
i__7648__auto___13096 = G__13097;
continue;
} else {
}
break;
}

var argseq__7655__auto__ = ((((1) < args__7654__auto__.length))?(new cljs.core.IndexedSeq(args__7654__auto__.slice((1)),(0),null)):null);
return clojure.math.combinatorics.lex_partitions_H.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__7655__auto__);
});

clojure.math.combinatorics.lex_partitions_H.cljs$core$IFn$_invoke$arity$variadic = (function (N,p__13086){
var map__13087 = p__13086;
var map__13087__$1 = ((((!((map__13087 == null)))?((((map__13087.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__13087.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__13087):map__13087);
var from = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__13087__$1,cljs.core.cst$kw$min);
var to = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__13087__$1,cljs.core.cst$kw$max);
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(N,(0))){
if((((function (){var or__6543__auto__ = from;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (0);
}
})() <= (0))) && (((0) <= (function (){var or__6543__auto__ = to;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (0);
}
})()))){
return cljs.core.list(cljs.core.List.EMPTY);
} else {
return cljs.core.List.EMPTY;
}
} else {
var from__$1 = (cljs.core.truth_((function (){var and__6531__auto__ = from;
if(cljs.core.truth_(and__6531__auto__)){
return (from <= (1));
} else {
return and__6531__auto__;
}
})())?null:from);
var to__$1 = (cljs.core.truth_((function (){var and__6531__auto__ = to;
if(cljs.core.truth_(and__6531__auto__)){
return (to >= N);
} else {
return and__6531__auto__;
}
})())?null:to);
if(!((((1) <= (function (){var or__6543__auto__ = from__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (1);
}
})())) && ((((function (){var or__6543__auto__ = from__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (1);
}
})() <= (function (){var or__6543__auto__ = to__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return N;
}
})())) && (((function (){var or__6543__auto__ = to__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return N;
}
})() <= N))))){
return cljs.core.List.EMPTY;
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(N,(0))){
return cljs.core.list(cljs.core.List.EMPTY);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(N,(1))){
return cljs.core.list(cljs.core.list(new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0)], null)));
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(to__$1,(1))){
return cljs.core.sequence.cljs$core$IFn$_invoke$arity$1(cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$1((function (){var x__7380__auto__ = cljs.core.sequence.cljs$core$IFn$_invoke$arity$1(cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$1((function (){var x__7380__auto__ = cljs.core.range.cljs$core$IFn$_invoke$arity$1(N);
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})())));
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})())));
} else {
var growth_strings = clojure.math.combinatorics.growth_strings_H.cljs$core$IFn$_invoke$arity$3(N,to__$1,from__$1);
var iter__7326__auto__ = ((function (growth_strings,from__$1,to__$1,map__13087,map__13087__$1,from,to){
return (function clojure$math$combinatorics$iter__13089(s__13090){
return (new cljs.core.LazySeq(null,((function (growth_strings,from__$1,to__$1,map__13087,map__13087__$1,from,to){
return (function (){
var s__13090__$1 = s__13090;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13090__$1);
if(temp__4657__auto__){
var s__13090__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13090__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13090__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13092 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13091 = (0);
while(true){
if((i__13091 < size__7325__auto__)){
var growth_string = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13091);
var groups = cljs.core.group_by(growth_string,cljs.core.range.cljs$core$IFn$_invoke$arity$1(N));
cljs.core.chunk_append(b__13092,cljs.core.map.cljs$core$IFn$_invoke$arity$2(groups,cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(groups))));

var G__13098 = (i__13091 + (1));
i__13091 = G__13098;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13092),clojure$math$combinatorics$iter__13089(cljs.core.chunk_rest(s__13090__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13092),null);
}
} else {
var growth_string = cljs.core.first(s__13090__$2);
var groups = cljs.core.group_by(growth_string,cljs.core.range.cljs$core$IFn$_invoke$arity$1(N));
return cljs.core.cons(cljs.core.map.cljs$core$IFn$_invoke$arity$2(groups,cljs.core.range.cljs$core$IFn$_invoke$arity$1(cljs.core.count(groups))),clojure$math$combinatorics$iter__13089(cljs.core.rest(s__13090__$2)));
}
} else {
return null;
}
break;
}
});})(growth_strings,from__$1,to__$1,map__13087,map__13087__$1,from,to))
,null,null));
});})(growth_strings,from__$1,to__$1,map__13087,map__13087__$1,from,to))
;
return iter__7326__auto__(growth_strings);

}
}
}
}
}
});

clojure.math.combinatorics.lex_partitions_H.cljs$lang$maxFixedArity = (1);

clojure.math.combinatorics.lex_partitions_H.cljs$lang$applyTo = (function (seq13084){
var G__13085 = cljs.core.first(seq13084);
var seq13084__$1 = cljs.core.next(seq13084);
return clojure.math.combinatorics.lex_partitions_H.cljs$core$IFn$_invoke$arity$variadic(G__13085,seq13084__$1);
});

clojure.math.combinatorics.partitions_H = (function clojure$math$combinatorics$partitions_H(var_args){
var args__7654__auto__ = [];
var len__7647__auto___13131 = arguments.length;
var i__7648__auto___13132 = (0);
while(true){
if((i__7648__auto___13132 < len__7647__auto___13131)){
args__7654__auto__.push((arguments[i__7648__auto___13132]));

var G__13133 = (i__7648__auto___13132 + (1));
i__7648__auto___13132 = G__13133;
continue;
} else {
}
break;
}

var argseq__7655__auto__ = ((((1) < args__7654__auto__.length))?(new cljs.core.IndexedSeq(args__7654__auto__.slice((1)),(0),null)):null);
return clojure.math.combinatorics.partitions_H.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__7655__auto__);
});

clojure.math.combinatorics.partitions_H.cljs$core$IFn$_invoke$arity$variadic = (function (items,args){
var items__$1 = cljs.core.vec(items);
var N = cljs.core.count(items__$1);
var lex = cljs.core.apply.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics.lex_partitions_H,N,args);
var iter__7326__auto__ = ((function (items__$1,N,lex){
return (function clojure$math$combinatorics$iter__13101(s__13102){
return (new cljs.core.LazySeq(null,((function (items__$1,N,lex){
return (function (){
var s__13102__$1 = s__13102;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13102__$1);
if(temp__4657__auto__){
var s__13102__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13102__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13102__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13104 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13103 = (0);
while(true){
if((i__13103 < size__7325__auto__)){
var parts = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13103);
cljs.core.chunk_append(b__13104,(function (){var iter__7326__auto__ = ((function (i__13103,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function clojure$math$combinatorics$iter__13101_$_iter__13119(s__13120){
return (new cljs.core.LazySeq(null,((function (i__13103,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (){
var s__13120__$1 = s__13120;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13120__$1);
if(temp__4657__auto____$1){
var s__13120__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13120__$2)){
var c__7324__auto____$1 = cljs.core.chunk_first(s__13120__$2);
var size__7325__auto____$1 = cljs.core.count(c__7324__auto____$1);
var b__13122 = cljs.core.chunk_buffer(size__7325__auto____$1);
if((function (){var i__13121 = (0);
while(true){
if((i__13121 < size__7325__auto____$1)){
var part = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto____$1,i__13121);
cljs.core.chunk_append(b__13122,cljs.core.persistent_BANG_(cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (i__13121,i__13103,part,c__7324__auto____$1,size__7325__auto____$1,b__13122,s__13120__$2,temp__4657__auto____$1,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (v,o){
return cljs.core.conj_BANG_.cljs$core$IFn$_invoke$arity$2(v,(items__$1.cljs$core$IFn$_invoke$arity$1 ? items__$1.cljs$core$IFn$_invoke$arity$1(o) : items__$1.call(null,o)));
});})(i__13121,i__13103,part,c__7324__auto____$1,size__7325__auto____$1,b__13122,s__13120__$2,temp__4657__auto____$1,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,cljs.core.transient$(cljs.core.PersistentVector.EMPTY),part)));

var G__13134 = (i__13121 + (1));
i__13121 = G__13134;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13122),clojure$math$combinatorics$iter__13101_$_iter__13119(cljs.core.chunk_rest(s__13120__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13122),null);
}
} else {
var part = cljs.core.first(s__13120__$2);
return cljs.core.cons(cljs.core.persistent_BANG_(cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (i__13103,part,s__13120__$2,temp__4657__auto____$1,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (v,o){
return cljs.core.conj_BANG_.cljs$core$IFn$_invoke$arity$2(v,(items__$1.cljs$core$IFn$_invoke$arity$1 ? items__$1.cljs$core$IFn$_invoke$arity$1(o) : items__$1.call(null,o)));
});})(i__13103,part,s__13120__$2,temp__4657__auto____$1,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,cljs.core.transient$(cljs.core.PersistentVector.EMPTY),part)),clojure$math$combinatorics$iter__13101_$_iter__13119(cljs.core.rest(s__13120__$2)));
}
} else {
return null;
}
break;
}
});})(i__13103,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,null,null));
});})(i__13103,parts,c__7324__auto__,size__7325__auto__,b__13104,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
;
return iter__7326__auto__(parts);
})());

var G__13135 = (i__13103 + (1));
i__13103 = G__13135;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13104),clojure$math$combinatorics$iter__13101(cljs.core.chunk_rest(s__13102__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13104),null);
}
} else {
var parts = cljs.core.first(s__13102__$2);
return cljs.core.cons((function (){var iter__7326__auto__ = ((function (parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function clojure$math$combinatorics$iter__13101_$_iter__13125(s__13126){
return (new cljs.core.LazySeq(null,((function (parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (){
var s__13126__$1 = s__13126;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13126__$1);
if(temp__4657__auto____$1){
var s__13126__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13126__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13126__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13128 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13127 = (0);
while(true){
if((i__13127 < size__7325__auto__)){
var part = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13127);
cljs.core.chunk_append(b__13128,cljs.core.persistent_BANG_(cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (i__13127,part,c__7324__auto__,size__7325__auto__,b__13128,s__13126__$2,temp__4657__auto____$1,parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (v,o){
return cljs.core.conj_BANG_.cljs$core$IFn$_invoke$arity$2(v,(items__$1.cljs$core$IFn$_invoke$arity$1 ? items__$1.cljs$core$IFn$_invoke$arity$1(o) : items__$1.call(null,o)));
});})(i__13127,part,c__7324__auto__,size__7325__auto__,b__13128,s__13126__$2,temp__4657__auto____$1,parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,cljs.core.transient$(cljs.core.PersistentVector.EMPTY),part)));

var G__13136 = (i__13127 + (1));
i__13127 = G__13136;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13128),clojure$math$combinatorics$iter__13101_$_iter__13125(cljs.core.chunk_rest(s__13126__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13128),null);
}
} else {
var part = cljs.core.first(s__13126__$2);
return cljs.core.cons(cljs.core.persistent_BANG_(cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(((function (part,s__13126__$2,temp__4657__auto____$1,parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex){
return (function (v,o){
return cljs.core.conj_BANG_.cljs$core$IFn$_invoke$arity$2(v,(items__$1.cljs$core$IFn$_invoke$arity$1 ? items__$1.cljs$core$IFn$_invoke$arity$1(o) : items__$1.call(null,o)));
});})(part,s__13126__$2,temp__4657__auto____$1,parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,cljs.core.transient$(cljs.core.PersistentVector.EMPTY),part)),clojure$math$combinatorics$iter__13101_$_iter__13125(cljs.core.rest(s__13126__$2)));
}
} else {
return null;
}
break;
}
});})(parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
,null,null));
});})(parts,s__13102__$2,temp__4657__auto__,items__$1,N,lex))
;
return iter__7326__auto__(parts);
})(),clojure$math$combinatorics$iter__13101(cljs.core.rest(s__13102__$2)));
}
} else {
return null;
}
break;
}
});})(items__$1,N,lex))
,null,null));
});})(items__$1,N,lex))
;
return iter__7326__auto__(lex);
});

clojure.math.combinatorics.partitions_H.cljs$lang$maxFixedArity = (1);

clojure.math.combinatorics.partitions_H.cljs$lang$applyTo = (function (seq13099){
var G__13100 = cljs.core.first(seq13099);
var seq13099__$1 = cljs.core.next(seq13099);
return clojure.math.combinatorics.partitions_H.cljs$core$IFn$_invoke$arity$variadic(G__13100,seq13099__$1);
});


clojure.math.combinatorics.multiset_partitions_M = (function clojure$math$combinatorics$multiset_partitions_M(var_args){
var args13137 = [];
var len__7647__auto___13182 = arguments.length;
var i__7648__auto___13183 = (0);
while(true){
if((i__7648__auto___13183 < len__7647__auto___13182)){
args13137.push((arguments[i__7648__auto___13183]));

var G__13184 = (i__7648__auto___13183 + (1));
i__7648__auto___13183 = G__13184;
continue;
} else {
}
break;
}

var G__13139 = args13137.length;
switch (G__13139) {
case 3:
return clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 11:
return clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$11((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]),(arguments[(5)]),(arguments[(6)]),(arguments[(7)]),(arguments[(8)]),(arguments[(9)]),(arguments[(10)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args13137.length)].join('')));

}
});

clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$3 = (function (multiset,r,s){
var n = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,cljs.core.vals(multiset));
var m = cljs.core.count(multiset);
var f = cljs.core.PersistentVector.EMPTY;
var c = cljs.core.PersistentVector.EMPTY;
var u = cljs.core.PersistentVector.EMPTY;
var v = cljs.core.PersistentVector.EMPTY;
var vec__13140 = (function (){var j = (0);
var c__$1 = c;
var u__$1 = u;
var v__$1 = v;
while(true){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(j,m)){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [c__$1,u__$1,v__$1], null);
} else {
var G__13186 = (j + (1));
var G__13187 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(c__$1,j,(j + (1)));
var G__13188 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(u__$1,j,(function (){var G__13143 = (j + (1));
return (multiset.cljs$core$IFn$_invoke$arity$1 ? multiset.cljs$core$IFn$_invoke$arity$1(G__13143) : multiset.call(null,G__13143));
})());
var G__13189 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$1,j,(function (){var G__13144 = (j + (1));
return (multiset.cljs$core$IFn$_invoke$arity$1 ? multiset.cljs$core$IFn$_invoke$arity$1(G__13144) : multiset.call(null,G__13144));
})());
j = G__13186;
c__$1 = G__13187;
u__$1 = G__13188;
v__$1 = G__13189;
continue;
}
break;
}
})();
var c__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13140,(0),null);
var u__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13140,(1),null);
var v__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13140,(2),null);
var a = (0);
var b = m;
var l = (0);
var f__$1 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$variadic(f,(0),(0),cljs.core.array_seq([(1),m], 0));
var stack = cljs.core.List.EMPTY;
return clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$11(n,m,f__$1,c__$1,u__$1,v__$1,a,b,l,r,s);
});

clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$11 = (function (n,m,f,c,u,v,a,b,l,r,s){
while(true){
var vec__13145 = (function (){var j = a;
var k = b;
var x = false;
var u__$1 = u;
var v__$1 = v;
var c__$1 = c;
while(true){
if((j >= b)){
return new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [u__$1,v__$1,c__$1,j,k], null);
} else {
var u__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(u__$1,k,((u__$1.cljs$core$IFn$_invoke$arity$1 ? u__$1.cljs$core$IFn$_invoke$arity$1(j) : u__$1.call(null,j)) - (v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(j) : v__$1.call(null,j))));
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((u__$2.cljs$core$IFn$_invoke$arity$1 ? u__$2.cljs$core$IFn$_invoke$arity$1(k) : u__$2.call(null,k)),(0))){
var G__13190 = (j + (1));
var G__13191 = k;
var G__13192 = true;
var G__13193 = u__$2;
var G__13194 = v__$1;
var G__13195 = c__$1;
j = G__13190;
k = G__13191;
x = G__13192;
u__$1 = G__13193;
v__$1 = G__13194;
c__$1 = G__13195;
continue;
} else {
if(!(x)){
var c__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(c__$1,k,(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(j) : c__$1.call(null,j)));
var v__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$1,k,(function (){var x__6881__auto__ = (v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(j) : v__$1.call(null,j));
var y__6882__auto__ = (u__$2.cljs$core$IFn$_invoke$arity$1 ? u__$2.cljs$core$IFn$_invoke$arity$1(k) : u__$2.call(null,k));
return ((x__6881__auto__ < y__6882__auto__) ? x__6881__auto__ : y__6882__auto__);
})());
var x__$1 = ((u__$2.cljs$core$IFn$_invoke$arity$1 ? u__$2.cljs$core$IFn$_invoke$arity$1(k) : u__$2.call(null,k)) < (v__$2.cljs$core$IFn$_invoke$arity$1 ? v__$2.cljs$core$IFn$_invoke$arity$1(j) : v__$2.call(null,j)));
var k__$1 = (k + (1));
var j__$1 = (j + (1));
var G__13196 = j__$1;
var G__13197 = k__$1;
var G__13198 = x__$1;
var G__13199 = u__$2;
var G__13200 = v__$2;
var G__13201 = c__$2;
j = G__13196;
k = G__13197;
x = G__13198;
u__$1 = G__13199;
v__$1 = G__13200;
c__$1 = G__13201;
continue;
} else {
var c__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(c__$1,k,(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(j) : c__$1.call(null,j)));
var v__$2 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$1,k,(u__$2.cljs$core$IFn$_invoke$arity$1 ? u__$2.cljs$core$IFn$_invoke$arity$1(k) : u__$2.call(null,k)));
var k__$1 = (k + (1));
var j__$1 = (j + (1));
var G__13202 = j__$1;
var G__13203 = k__$1;
var G__13204 = x;
var G__13205 = u__$2;
var G__13206 = v__$2;
var G__13207 = c__$2;
j = G__13202;
k = G__13203;
x = G__13204;
u__$1 = G__13205;
v__$1 = G__13206;
c__$1 = G__13207;
continue;
}
}
}
break;
}
})();
var u__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13145,(0),null);
var v__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13145,(1),null);
var c__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13145,(2),null);
var j = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13145,(3),null);
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13145,(4),null);
if(cljs.core.truth_((function (){var and__6531__auto__ = r;
if(cljs.core.truth_(and__6531__auto__)){
return ((k > b)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(l,(r - (1))));
} else {
return and__6531__auto__;
}
})())){
return (clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11 ? clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11(n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s) : clojure.math.combinatorics.m5.call(null,n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s));
} else {
if(cljs.core.truth_((function (){var and__6531__auto__ = s;
if(cljs.core.truth_(and__6531__auto__)){
return ((k <= b)) && (((l + (1)) < s));
} else {
return and__6531__auto__;
}
})())){
return (clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11 ? clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11(n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s) : clojure.math.combinatorics.m5.call(null,n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s));
} else {
if((k > b)){
var a__$1 = b;
var b__$1 = k;
var l__$1 = (l + (1));
var f__$1 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(f,(l__$1 + (1)),b__$1);
var G__13208 = n;
var G__13209 = m;
var G__13210 = f__$1;
var G__13211 = c__$1;
var G__13212 = u__$1;
var G__13213 = v__$1;
var G__13214 = a__$1;
var G__13215 = b__$1;
var G__13216 = l__$1;
var G__13217 = r;
var G__13218 = s;
n = G__13208;
m = G__13209;
f = G__13210;
c = G__13211;
u = G__13212;
v = G__13213;
a = G__13214;
b = G__13215;
l = G__13216;
r = G__13217;
s = G__13218;
continue;
} else {
var part = (function (){var iter__7326__auto__ = ((function (n,m,f,c,u,v,a,b,l,r,s,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function clojure$math$combinatorics$iter__13148(s__13149){
return (new cljs.core.LazySeq(null,((function (n,m,f,c,u,v,a,b,l,r,s,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function (){
var s__13149__$1 = s__13149;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13149__$1);
if(temp__4657__auto__){
var s__13149__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13149__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13149__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13151 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13150 = (0);
while(true){
if((i__13150 < size__7325__auto__)){
var y = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13150);
cljs.core.chunk_append(b__13151,(function (){var first_col = (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(y) : f.call(null,y));
var last_col = ((function (){var G__13168 = (y + (1));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__13168) : f.call(null,G__13168));
})() - (1));
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,(function (){var iter__7326__auto__ = ((function (i__13150,n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,c__7324__auto__,size__7325__auto__,b__13151,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function clojure$math$combinatorics$iter__13148_$_iter__13169(s__13170){
return (new cljs.core.LazySeq(null,((function (i__13150,n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,c__7324__auto__,size__7325__auto__,b__13151,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function (){
var s__13170__$1 = s__13170;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13170__$1);
if(temp__4657__auto____$1){
var s__13170__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13170__$2)){
var c__7324__auto____$1 = cljs.core.chunk_first(s__13170__$2);
var size__7325__auto____$1 = cljs.core.count(c__7324__auto____$1);
var b__13172 = cljs.core.chunk_buffer(size__7325__auto____$1);
if((function (){var i__13171 = (0);
while(true){
if((i__13171 < size__7325__auto____$1)){
var z = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto____$1,i__13171);
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2((v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z)),(0))){
cljs.core.chunk_append(b__13172,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(z) : c__$1.call(null,z)),(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z))], null));

var G__13219 = (i__13171 + (1));
i__13171 = G__13219;
continue;
} else {
var G__13220 = (i__13171 + (1));
i__13171 = G__13220;
continue;
}
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13172),clojure$math$combinatorics$iter__13148_$_iter__13169(cljs.core.chunk_rest(s__13170__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13172),null);
}
} else {
var z = cljs.core.first(s__13170__$2);
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2((v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z)),(0))){
return cljs.core.cons(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(z) : c__$1.call(null,z)),(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z))], null),clojure$math$combinatorics$iter__13148_$_iter__13169(cljs.core.rest(s__13170__$2)));
} else {
var G__13221 = cljs.core.rest(s__13170__$2);
s__13170__$1 = G__13221;
continue;
}
}
} else {
return null;
}
break;
}
});})(i__13150,n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,c__7324__auto__,size__7325__auto__,b__13151,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k))
,null,null));
});})(i__13150,n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,c__7324__auto__,size__7325__auto__,b__13151,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2(first_col,(last_col + (1))));
})());
})());

var G__13222 = (i__13150 + (1));
i__13150 = G__13222;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13151),clojure$math$combinatorics$iter__13148(cljs.core.chunk_rest(s__13149__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13151),null);
}
} else {
var y = cljs.core.first(s__13149__$2);
return cljs.core.cons((function (){var first_col = (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(y) : f.call(null,y));
var last_col = ((function (){var G__13175 = (y + (1));
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__13175) : f.call(null,G__13175));
})() - (1));
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,(function (){var iter__7326__auto__ = ((function (n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function clojure$math$combinatorics$iter__13148_$_iter__13176(s__13177){
return (new cljs.core.LazySeq(null,((function (n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function (){
var s__13177__$1 = s__13177;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13177__$1);
if(temp__4657__auto____$1){
var s__13177__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13177__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13177__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13179 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13178 = (0);
while(true){
if((i__13178 < size__7325__auto__)){
var z = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13178);
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2((v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z)),(0))){
cljs.core.chunk_append(b__13179,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(z) : c__$1.call(null,z)),(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z))], null));

var G__13223 = (i__13178 + (1));
i__13178 = G__13223;
continue;
} else {
var G__13224 = (i__13178 + (1));
i__13178 = G__13224;
continue;
}
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13179),clojure$math$combinatorics$iter__13148_$_iter__13176(cljs.core.chunk_rest(s__13177__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13179),null);
}
} else {
var z = cljs.core.first(s__13177__$2);
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2((v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z)),(0))){
return cljs.core.cons(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(c__$1.cljs$core$IFn$_invoke$arity$1 ? c__$1.cljs$core$IFn$_invoke$arity$1(z) : c__$1.call(null,z)),(v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(z) : v__$1.call(null,z))], null),clojure$math$combinatorics$iter__13148_$_iter__13176(cljs.core.rest(s__13177__$2)));
} else {
var G__13225 = cljs.core.rest(s__13177__$2);
s__13177__$1 = G__13225;
continue;
}
}
} else {
return null;
}
break;
}
});})(n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k))
,null,null));
});})(n,m,f,c,u,v,a,b,l,r,s,first_col,last_col,y,s__13149__$2,temp__4657__auto__,vec__13145,u__$1,v__$1,c__$1,j,k))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2(first_col,(last_col + (1))));
})());
})(),clojure$math$combinatorics$iter__13148(cljs.core.rest(s__13149__$2)));
}
} else {
return null;
}
break;
}
});})(n,m,f,c,u,v,a,b,l,r,s,vec__13145,u__$1,v__$1,c__$1,j,k))
,null,null));
});})(n,m,f,c,u,v,a,b,l,r,s,vec__13145,u__$1,v__$1,c__$1,j,k))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1((l + (1))));
})();
return cljs.core.cons(part,(new cljs.core.LazySeq(null,((function (n,m,f,c,u,v,a,b,l,r,s,part,vec__13145,u__$1,v__$1,c__$1,j,k){
return (function (){
return (clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11 ? clojure.math.combinatorics.m5.cljs$core$IFn$_invoke$arity$11(n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s) : clojure.math.combinatorics.m5.call(null,n,m,f,c__$1,u__$1,v__$1,a,b,l,r,s));
});})(n,m,f,c,u,v,a,b,l,r,s,part,vec__13145,u__$1,v__$1,c__$1,j,k))
,null,null)));

}
}
}
break;
}
});

clojure.math.combinatorics.multiset_partitions_M.cljs$lang$maxFixedArity = 11;

clojure.math.combinatorics.m5 = (function clojure$math$combinatorics$m5(n,m,f,c,u,v,a,b,l,r,s){
var j = (function (){var j = (b - (1));
while(true){
if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2((v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(j) : v.call(null,j)),(0))){
return j;
} else {
var G__13238 = (j - (1));
j = G__13238;
continue;
}
break;
}
})();
if(cljs.core.truth_((function (){var and__6531__auto__ = r;
if(cljs.core.truth_(and__6531__auto__)){
return (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(j,a)) && (((((v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(j) : v.call(null,j)) - (1)) * (r - l)) < (u.cljs$core$IFn$_invoke$arity$1 ? u.cljs$core$IFn$_invoke$arity$1(j) : u.call(null,j))));
} else {
return and__6531__auto__;
}
})())){
return (clojure.math.combinatorics.m6.cljs$core$IFn$_invoke$arity$11 ? clojure.math.combinatorics.m6.cljs$core$IFn$_invoke$arity$11(n,m,f,c,u,v,a,b,l,r,s) : clojure.math.combinatorics.m6.call(null,n,m,f,c,u,v,a,b,l,r,s));
} else {
if((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(j,a)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((v.cljs$core$IFn$_invoke$arity$1 ? v.cljs$core$IFn$_invoke$arity$1(j) : v.call(null,j)),(1)))){
return (clojure.math.combinatorics.m6.cljs$core$IFn$_invoke$arity$11 ? clojure.math.combinatorics.m6.cljs$core$IFn$_invoke$arity$11(n,m,f,c,u,v,a,b,l,r,s) : clojure.math.combinatorics.m6.call(null,n,m,f,c,u,v,a,b,l,r,s));
} else {
var v__$1 = clojure.math.combinatorics.update(v,j,cljs.core.dec);
var diff_uv = (cljs.core.truth_(s)?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core._PLUS_,(function (){var iter__7326__auto__ = ((function (v__$1,j){
return (function clojure$math$combinatorics$m5_$_iter__13232(s__13233){
return (new cljs.core.LazySeq(null,((function (v__$1,j){
return (function (){
var s__13233__$1 = s__13233;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13233__$1);
if(temp__4657__auto__){
var s__13233__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13233__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13233__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13235 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13234 = (0);
while(true){
if((i__13234 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13234);
cljs.core.chunk_append(b__13235,((u.cljs$core$IFn$_invoke$arity$1 ? u.cljs$core$IFn$_invoke$arity$1(i) : u.call(null,i)) - (v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(i) : v__$1.call(null,i))));

var G__13239 = (i__13234 + (1));
i__13234 = G__13239;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13235),clojure$math$combinatorics$m5_$_iter__13232(cljs.core.chunk_rest(s__13233__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13235),null);
}
} else {
var i = cljs.core.first(s__13233__$2);
return cljs.core.cons(((u.cljs$core$IFn$_invoke$arity$1 ? u.cljs$core$IFn$_invoke$arity$1(i) : u.call(null,i)) - (v__$1.cljs$core$IFn$_invoke$arity$1 ? v__$1.cljs$core$IFn$_invoke$arity$1(i) : v__$1.call(null,i))),clojure$math$combinatorics$m5_$_iter__13232(cljs.core.rest(s__13233__$2)));
}
} else {
return null;
}
break;
}
});})(v__$1,j))
,null,null));
});})(v__$1,j))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$2(a,(j + (1))));
})()):null);
var v__$2 = (function (){var ks = cljs.core.range.cljs$core$IFn$_invoke$arity$2((j + (1)),b);
var v__$2 = v__$1;
while(true){
if(cljs.core.empty_QMARK_(ks)){
return v__$2;
} else {
var k = cljs.core.first(ks);
var G__13240 = cljs.core.rest(ks);
var G__13241 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$2,k,(u.cljs$core$IFn$_invoke$arity$1 ? u.cljs$core$IFn$_invoke$arity$1(k) : u.call(null,k)));
ks = G__13240;
v__$2 = G__13241;
continue;
}
break;
}
})();
var min_partitions_after_this = (cljs.core.truth_(s)?(s - (l + (1))):(0));
var amount_to_dec = (cljs.core.truth_(s)?(function (){var x__6874__auto__ = (0);
var y__6875__auto__ = (min_partitions_after_this - diff_uv);
return ((x__6874__auto__ > y__6875__auto__) ? x__6874__auto__ : y__6875__auto__);
})():(0));
var v__$3 = ((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(amount_to_dec,(0)))?v__$2:(function (){var k_1 = (b - (1));
var v__$3 = v__$2;
var amount = amount_to_dec;
while(true){
var vk = (v__$3.cljs$core$IFn$_invoke$arity$1 ? v__$3.cljs$core$IFn$_invoke$arity$1(k_1) : v__$3.call(null,k_1));
if((amount > vk)){
var G__13242 = (k_1 - (1));
var G__13243 = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$3,k_1,(0));
var G__13244 = (amount - vk);
k_1 = G__13242;
v__$3 = G__13243;
amount = G__13244;
continue;
} else {
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(v__$3,k_1,(vk - amount));
}
break;
}
})());
return clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$11(n,m,f,c,u,v__$3,a,b,l,r,s);

}
}
});
clojure.math.combinatorics.m6 = (function clojure$math$combinatorics$m6(n,m,f,c,u,v,a,b,l,r,s){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(l,(0))){
return cljs.core.List.EMPTY;
} else {
var l__$1 = (l - (1));
var b__$1 = a;
var a__$1 = (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(l__$1) : f.call(null,l__$1));
return clojure.math.combinatorics.m5(n,m,f,c,u,v,a__$1,b__$1,l__$1,r,s);
}
});
clojure.math.combinatorics.partitions_M = (function clojure$math$combinatorics$partitions_M(var_args){
var args__7654__auto__ = [];
var len__7647__auto___13370 = arguments.length;
var i__7648__auto___13371 = (0);
while(true){
if((i__7648__auto___13371 < len__7647__auto___13370)){
args__7654__auto__.push((arguments[i__7648__auto___13371]));

var G__13372 = (i__7648__auto___13371 + (1));
i__7648__auto___13371 = G__13372;
continue;
} else {
}
break;
}

var argseq__7655__auto__ = ((((1) < args__7654__auto__.length))?(new cljs.core.IndexedSeq(args__7654__auto__.slice((1)),(0),null)):null);
return clojure.math.combinatorics.partitions_M.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__7655__auto__);
});

clojure.math.combinatorics.partitions_M.cljs$core$IFn$_invoke$arity$variadic = (function (items,p__13247){
var map__13248 = p__13247;
var map__13248__$1 = ((((!((map__13248 == null)))?((((map__13248.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__13248.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__13248):map__13248);
var from = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__13248__$1,cljs.core.cst$kw$min);
var to = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__13248__$1,cljs.core.cst$kw$max);
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.count(items),(0))){
if((((function (){var or__6543__auto__ = from;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (0);
}
})() <= (0))) && (((0) <= (function (){var or__6543__auto__ = to;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (0);
}
})()))){
return cljs.core.list(cljs.core.List.EMPTY);
} else {
return cljs.core.List.EMPTY;
}
} else {
var items__$1 = cljs.core.vec(items);
var ditems = cljs.core.vec(cljs.core.distinct.cljs$core$IFn$_invoke$arity$1(items__$1));
var freqs = cljs.core.frequencies(items__$1);
var N = cljs.core.count(items__$1);
var M = cljs.core.count(ditems);
var from__$1 = (cljs.core.truth_((function (){var and__6531__auto__ = from;
if(cljs.core.truth_(and__6531__auto__)){
return (from <= (1));
} else {
return and__6531__auto__;
}
})())?null:from);
var to__$1 = (cljs.core.truth_((function (){var and__6531__auto__ = to;
if(cljs.core.truth_(and__6531__auto__)){
return (to >= N);
} else {
return and__6531__auto__;
}
})())?null:to);
if(!((((1) <= (function (){var or__6543__auto__ = from__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (1);
}
})())) && ((((function (){var or__6543__auto__ = from__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return (1);
}
})() <= (function (){var or__6543__auto__ = to__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return N;
}
})())) && (((function (){var or__6543__auto__ = to__$1;
if(cljs.core.truth_(or__6543__auto__)){
return or__6543__auto__;
} else {
return N;
}
})() <= N))))){
return cljs.core.List.EMPTY;
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(N,(1))){
return cljs.core.sequence.cljs$core$IFn$_invoke$arity$1(cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$1((function (){var x__7380__auto__ = cljs.core.sequence.cljs$core$IFn$_invoke$arity$1(cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$1((function (){var x__7380__auto__ = cljs.core.vec(cljs.core.sequence.cljs$core$IFn$_invoke$arity$1(cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$1((function (){var x__7380__auto__ = cljs.core.first(items__$1);
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})()))));
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})())));
return cljs.core._conj(cljs.core.List.EMPTY,x__7380__auto__);
})())));
} else {
var start_multiset = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,(function (){var iter__7326__auto__ = ((function (items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function clojure$math$combinatorics$iter__13250(s__13251){
return (new cljs.core.LazySeq(null,((function (items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (){
var s__13251__$1 = s__13251;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13251__$1);
if(temp__4657__auto__){
var s__13251__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13251__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13251__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13253 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13252 = (0);
while(true){
if((i__13252 < size__7325__auto__)){
var i = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13252);
var j = (i + (1));
cljs.core.chunk_append(b__13253,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [j,(function (){var G__13258 = (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(i) : ditems.call(null,i));
return (freqs.cljs$core$IFn$_invoke$arity$1 ? freqs.cljs$core$IFn$_invoke$arity$1(G__13258) : freqs.call(null,G__13258));
})()], null));

var G__13373 = (i__13252 + (1));
i__13252 = G__13373;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13253),clojure$math$combinatorics$iter__13250(cljs.core.chunk_rest(s__13251__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13253),null);
}
} else {
var i = cljs.core.first(s__13251__$2);
var j = (i + (1));
return cljs.core.cons(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [j,(function (){var G__13259 = (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(i) : ditems.call(null,i));
return (freqs.cljs$core$IFn$_invoke$arity$1 ? freqs.cljs$core$IFn$_invoke$arity$1(G__13259) : freqs.call(null,G__13259));
})()], null),clojure$math$combinatorics$iter__13250(cljs.core.rest(s__13251__$2)));
}
} else {
return null;
}
break;
}
});})(items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,null,null));
});})(items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
;
return iter__7326__auto__(cljs.core.range.cljs$core$IFn$_invoke$arity$1(M));
})());
var parts = clojure.math.combinatorics.multiset_partitions_M.cljs$core$IFn$_invoke$arity$3(start_multiset,to__$1,from__$1);
var iter__7326__auto__ = ((function (start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function clojure$math$combinatorics$iter__13260(s__13261){
return (new cljs.core.LazySeq(null,((function (start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (){
var s__13261__$1 = s__13261;
while(true){
var temp__4657__auto__ = cljs.core.seq(s__13261__$1);
if(temp__4657__auto__){
var s__13261__$2 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_(s__13261__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13261__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13263 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13262 = (0);
while(true){
if((i__13262 < size__7325__auto__)){
var part = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13262);
cljs.core.chunk_append(b__13263,(function (){var iter__7326__auto__ = ((function (i__13262,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function clojure$math$combinatorics$iter__13260_$_iter__13318(s__13319){
return (new cljs.core.LazySeq(null,((function (i__13262,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (){
var s__13319__$1 = s__13319;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13319__$1);
if(temp__4657__auto____$1){
var s__13319__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13319__$2)){
var c__7324__auto____$1 = cljs.core.chunk_first(s__13319__$2);
var size__7325__auto____$1 = cljs.core.count(c__7324__auto____$1);
var b__13321 = cljs.core.chunk_buffer(size__7325__auto____$1);
if((function (){var i__13320 = (0);
while(true){
if((i__13320 < size__7325__auto____$1)){
var multiset = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto____$1,i__13320);
cljs.core.chunk_append(b__13321,cljs.core.vec(cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic(((function (i__13320,i__13262,multiset,c__7324__auto____$1,size__7325__auto____$1,b__13321,s__13319__$2,temp__4657__auto____$1,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (p__13334){
var vec__13335 = p__13334;
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13335,(0),null);
var numtimes = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13335,(1),null);
return cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(numtimes,(function (){var G__13338 = (index - (1));
return (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(G__13338) : ditems.call(null,G__13338));
})());
});})(i__13320,i__13262,multiset,c__7324__auto____$1,size__7325__auto____$1,b__13321,s__13319__$2,temp__4657__auto____$1,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,cljs.core.array_seq([multiset], 0))));

var G__13374 = (i__13320 + (1));
i__13320 = G__13374;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13321),clojure$math$combinatorics$iter__13260_$_iter__13318(cljs.core.chunk_rest(s__13319__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13321),null);
}
} else {
var multiset = cljs.core.first(s__13319__$2);
return cljs.core.cons(cljs.core.vec(cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic(((function (i__13262,multiset,s__13319__$2,temp__4657__auto____$1,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (p__13339){
var vec__13340 = p__13339;
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13340,(0),null);
var numtimes = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13340,(1),null);
return cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(numtimes,(function (){var G__13343 = (index - (1));
return (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(G__13343) : ditems.call(null,G__13343));
})());
});})(i__13262,multiset,s__13319__$2,temp__4657__auto____$1,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,cljs.core.array_seq([multiset], 0))),clojure$math$combinatorics$iter__13260_$_iter__13318(cljs.core.rest(s__13319__$2)));
}
} else {
return null;
}
break;
}
});})(i__13262,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,null,null));
});})(i__13262,part,c__7324__auto__,size__7325__auto__,b__13263,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
;
return iter__7326__auto__(part);
})());

var G__13375 = (i__13262 + (1));
i__13262 = G__13375;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13263),clojure$math$combinatorics$iter__13260(cljs.core.chunk_rest(s__13261__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13263),null);
}
} else {
var part = cljs.core.first(s__13261__$2);
return cljs.core.cons((function (){var iter__7326__auto__ = ((function (part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function clojure$math$combinatorics$iter__13260_$_iter__13344(s__13345){
return (new cljs.core.LazySeq(null,((function (part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (){
var s__13345__$1 = s__13345;
while(true){
var temp__4657__auto____$1 = cljs.core.seq(s__13345__$1);
if(temp__4657__auto____$1){
var s__13345__$2 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_(s__13345__$2)){
var c__7324__auto__ = cljs.core.chunk_first(s__13345__$2);
var size__7325__auto__ = cljs.core.count(c__7324__auto__);
var b__13347 = cljs.core.chunk_buffer(size__7325__auto__);
if((function (){var i__13346 = (0);
while(true){
if((i__13346 < size__7325__auto__)){
var multiset = cljs.core._nth.cljs$core$IFn$_invoke$arity$2(c__7324__auto__,i__13346);
cljs.core.chunk_append(b__13347,cljs.core.vec(cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic(((function (i__13346,multiset,c__7324__auto__,size__7325__auto__,b__13347,s__13345__$2,temp__4657__auto____$1,part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (p__13360){
var vec__13361 = p__13360;
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13361,(0),null);
var numtimes = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13361,(1),null);
return cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(numtimes,(function (){var G__13364 = (index - (1));
return (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(G__13364) : ditems.call(null,G__13364));
})());
});})(i__13346,multiset,c__7324__auto__,size__7325__auto__,b__13347,s__13345__$2,temp__4657__auto____$1,part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,cljs.core.array_seq([multiset], 0))));

var G__13376 = (i__13346 + (1));
i__13346 = G__13376;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons(cljs.core.chunk(b__13347),clojure$math$combinatorics$iter__13260_$_iter__13344(cljs.core.chunk_rest(s__13345__$2)));
} else {
return cljs.core.chunk_cons(cljs.core.chunk(b__13347),null);
}
} else {
var multiset = cljs.core.first(s__13345__$2);
return cljs.core.cons(cljs.core.vec(cljs.core.mapcat.cljs$core$IFn$_invoke$arity$variadic(((function (multiset,s__13345__$2,temp__4657__auto____$1,part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to){
return (function (p__13365){
var vec__13366 = p__13365;
var index = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13366,(0),null);
var numtimes = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__13366,(1),null);
return cljs.core.repeat.cljs$core$IFn$_invoke$arity$2(numtimes,(function (){var G__13369 = (index - (1));
return (ditems.cljs$core$IFn$_invoke$arity$1 ? ditems.cljs$core$IFn$_invoke$arity$1(G__13369) : ditems.call(null,G__13369));
})());
});})(multiset,s__13345__$2,temp__4657__auto____$1,part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,cljs.core.array_seq([multiset], 0))),clojure$math$combinatorics$iter__13260_$_iter__13344(cljs.core.rest(s__13345__$2)));
}
} else {
return null;
}
break;
}
});})(part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,null,null));
});})(part,s__13261__$2,temp__4657__auto__,start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
;
return iter__7326__auto__(part);
})(),clojure$math$combinatorics$iter__13260(cljs.core.rest(s__13261__$2)));
}
} else {
return null;
}
break;
}
});})(start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
,null,null));
});})(start_multiset,parts,items__$1,ditems,freqs,N,M,from__$1,to__$1,map__13248,map__13248__$1,from,to))
;
return iter__7326__auto__(parts);

}
}
}
});

clojure.math.combinatorics.partitions_M.cljs$lang$maxFixedArity = (1);

clojure.math.combinatorics.partitions_M.cljs$lang$applyTo = (function (seq13245){
var G__13246 = cljs.core.first(seq13245);
var seq13245__$1 = cljs.core.next(seq13245);
return clojure.math.combinatorics.partitions_M.cljs$core$IFn$_invoke$arity$variadic(G__13246,seq13245__$1);
});

/**
 * All the lexicographic distinct partitions of items.
 *  Optionally pass in :min and/or :max to specify inclusive bounds on the number of parts the items can be split into.
 */
clojure.math.combinatorics.partitions = (function clojure$math$combinatorics$partitions(var_args){
var args__7654__auto__ = [];
var len__7647__auto___13379 = arguments.length;
var i__7648__auto___13380 = (0);
while(true){
if((i__7648__auto___13380 < len__7647__auto___13379)){
args__7654__auto__.push((arguments[i__7648__auto___13380]));

var G__13381 = (i__7648__auto___13380 + (1));
i__7648__auto___13380 = G__13381;
continue;
} else {
}
break;
}

var argseq__7655__auto__ = ((((1) < args__7654__auto__.length))?(new cljs.core.IndexedSeq(args__7654__auto__.slice((1)),(0),null)):null);
return clojure.math.combinatorics.partitions.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__7655__auto__);
});

clojure.math.combinatorics.partitions.cljs$core$IFn$_invoke$arity$variadic = (function (items,args){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.count(items),(0))){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics.partitions_H,items,args);
} else {
if(cljs.core.truth_(clojure.math.combinatorics.all_different_QMARK_(items))){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics.partitions_H,items,args);
} else {
return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(clojure.math.combinatorics.partitions_M,items,args);

}
}
});

clojure.math.combinatorics.partitions.cljs$lang$maxFixedArity = (1);

clojure.math.combinatorics.partitions.cljs$lang$applyTo = (function (seq13377){
var G__13378 = cljs.core.first(seq13377);
var seq13377__$1 = cljs.core.next(seq13377);
return clojure.math.combinatorics.partitions.cljs$core$IFn$_invoke$arity$variadic(G__13378,seq13377__$1);
});

