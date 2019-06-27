(ns bermuda.core
  [:require [zprint.core :as zp]])

;; Based on Bermuda Triangle puzzle: https://goo.gl/h1MEFM

(def border
  [[:BL :RD :GN :BK]
   [:GN :WT :GN :GN]
   [:YL :WT :RD :WT]])

(def tiles
  [[:RD :GN :YL]
   [:BK :BK :GN]
   [:BK :GN :RD]
   [:WT :BL :BK]
   [:GN :BK :YL]
   [:WT :GN :YL]
   [:GN :RD :BK]
   [:WT :RD :GN]
   [:YL :GN :BL]
   [:WT :BL :BK]
   [:BL :WT :WT]
   [:WT :YL :RD]
   [:WT :GN :YL]
   [:BK :BL :YL]
   [:WT :BL :BL]
   [:BK :RD :GN]])

(def constraint-templates
  {0 [:BL :WC :WT]
   1 [:RD :WC :WC]
   2 [:WC :WC :WC]
   3 [:WC :WC :RD]
   4 [:GN :WC :WC]
   5 [:WC :WC :WC]
   6 [:WC :WC :WC]
   7 [:WC :WC :WC]
   8 [:WT :WC :WC]
   9 [:BK :GN :WC]
   10 [:WC :WC :WC]
   11 [:WC :WT :WC]
   12 [:WC :WC :WC]
   13 [:WC :GN :WC]
   14 [:WC :WC :WC]
   15 [:WC :GN :YL]})

(def inverted-triangle
  #{2 5 7 10 12 14})

(def right-side-up-triangle 
  #{0 1 3 4 6 8 9 11 13 15})

(defn symmetry [tls]
  (map (fn [[a b c]] #{[a b c] [b c a] [c a b]}) tls))

(defn color-match? [s t]
  (or (= s :WC)
      (= s t)))

(defn tile-match? [s0 s1 s2 [t0 t1 t2]]
  (and (color-match? s0 t0)
       (color-match? s1 t1)
       (color-match? s2 t2)))

(defn placed-tiles [board]
  (filter #(not= [:EM :EM :EM] %) board))

(defn remaining-tiles [board]
  (let [populated (placed-tiles board)]
    (loop [p populated
           ts tiles]
      (if (empty? p)
        ts
        (let [ps (partition-by #(= % (first p)) ts)]
          (recur (rest p)
                 (apply concat (first ps) (drop 2 ps))))))))

(defn initial-board
  ([]
   (initial-board 16))
  ([num]
   (repeat num (vec (repeat 3 :EM)))))

(defn initial-board2 []
  (repeat 16 (into #{} (repeat 3 (vec (repeat 3 :EM))))))

(defn insert-tile [board tile]
  (let [populated (placed-tiles board)
        fill (initial-board (- (count board) (count populated) 1))]
    (concat populated [tile] fill)))

(defn remove-tile [board]
  (let [populated (placed-tiles board)
        fill (initial-board (inc (- (count board) (count populated))))]
    (concat (drop-last populated) fill)))

(defn next-open-index [board]
  (let [populated (placed-tiles board)]
    (count populated)))

;(defn constraints [board index]

(defn symmetry-match? [s0 s1 s2 symmetry-set]
  (some #(tile-match? s0 s1 s2 %) symmetry-set))

(defn tile-set-match? [s0 s1 s2 tile-set]
  (filter #(symmetry-match? s0 s1 s2 %) tile-set))

(defn -main []
  (let [dist (group-by identity (concat (flatten border) (flatten tiles)))
        syms (group-by identity (symmetry tiles))]
    (zp/czprint (zipmap (keys syms)
                        (map count (vals syms))))
    (zp/czprint (zipmap (keys dist)
                       (map count (vals dist))))))

