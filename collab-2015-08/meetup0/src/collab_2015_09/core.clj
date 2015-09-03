(ns collab-2015-09.core)

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; explore reduce:
;; provide _

(fn do-not-compile []
  (let [_ :replace-me]
    (reduce _ [1 2 3]))

  (let [_ :replace-me]
    (reduce + _ [1 2 3]))

  (let [_ :replace-me]
    (= _ (reduce conj nil [1 2 3])))

  (let [_ :replace-me]
    (reduce conj [nil 1 2 3]))

  (let [_ :replace-me]
    (reduce conj [] [1 2 3]))

  (let [_ :replace-me]
    (reduce vector [1 2 3 4 5 6]))

  (let [_ :replace-me]
    ;; Don't do this. Really.
    (reduce #(%2 %1) 1 [inc #(range 50 100 %) #(map inc %) (juxt last first second) #(apply - %)])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; mapl, filterl, and mapcatl implemented via 'reduce
;; see https://youtu.be/6mTbuzafcII?t=21m41s
(defn mapl [f coll]
  (reduce (fn [r item] (conj r (f item)))
          [] coll))

(defn filterl [pred coll]
  (reduce (fn [r item]
            (if (pred item)
              (conj r item)
              r))
          [] coll))

(defn mapcatl [f coll]
  (reduce (fn [r item]
            (reduce conj r (f item)))
          []
          coll))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; see https://youtu.be/6mTbuzafcII?t=23m19s
(defn mapping [f]
  (fn [step]
    (fn [r x] (step r (f x)))))
#_
(reduce ((mapping inc) conj) [] [1 2 3 4 5])

(defn filtering [pred]
  (fn [step]
    (fn [r x] (if (pred x) (step r x) r))))

(def catting
  (fn [step]
    (fn [r x] (reduce step r x))))

(defn mapcatting [f]
  (comp (mapping f) catting))


;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;


