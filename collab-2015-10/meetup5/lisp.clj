
(defn my-eval [env exp]
  (if (list? exp)

    (let [ev #(my-eval env %)
          en #(ev (nth exp %))
          ea (map ev (rest exp))]

      (condp = (first exp)
        'if  (if (en 1) (en 2) (en 3))
        'do  (last ea)
        'let (let [[_ [k v] & b] exp]
               (recur (assoc env k (ev v))
                      (cons 'do b)))
        (apply (en 0) ea)))

    (env exp exp)))
