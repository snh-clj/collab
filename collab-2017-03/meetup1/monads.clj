
(ns Monads)

(defprotocol Monad
  (wrap [_ x])
  (flat-map [v f])
  (zero [_]))

(defmacro for [bindings expr]
  (let [steps (rest (partition 2 bindings))
        val-sym (gensym "for_")]
    `(let [~val-sym ~(second bindings)]
       (Monads/flat-map ~val-sym
                        (fn [~(first bindings)]
                          ~(reduce (fn [expr [sym mv]]
                                     (cond
                                       (= :when sym) `(if ~mv
                                                        ~expr
                                                        (Monads/zero ~val-sym))
                                       (= :let sym) `(let ~mv
                                                       ~expr)
                                       :else `(Monads/flat-map ~mv (fn [~sym]
                                                                     ~expr))))
                                   `(Monads/wrap ~val-sym ~expr)
                                   (reverse steps)))))))
