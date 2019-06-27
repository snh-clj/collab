(ns bermuda.core
  [:require [zprint.core :as zp]])

;; Based on Bermuda Triangle puzzle: https://goo.gl/h1MEFM

(def border
  [[:BL :RD :GN :BK]
   [:GN :WT :GN :GN]
   [:YL :WT :RD :WT]])

(def corners
 [[:GN :BK :GN :WT]
  [:GN :GN :YL :WT]
  [:RD :WT :BL :RD]])

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

(defn cull-tile
  [tile tiles]
  (let [count (get tiles tile)]
    (if (> count 1)
      (assoc tiles tile (dec count))
      (dissoc tiles tile))))
(defn tile-has-correct-dots?
  [x y]
  true)

(defn pick-tile
  [tiles match-vector]
  (for [[tile count] tiles
       orientation [0 1 2]
    :when (tile-has-correct-dots? tile orientation)
    :when (> count 0)]
    [tile orientation (cull-tile tile tiles)]
  ))

(defn find-match
  [a b c d e f]
  "fooo")

(defn find-corner
  [edge tiles-rest]
  (for [[tile-0 orient-0 tiles-rest-0] (pick-tile (subvec edge 1 3) tiles-rest)
        [tile-1 orient-1 tiles-rest-1] (pick-tile (subvec edge 0 1) tiles-rest-0)
        [tile-2 orient-2 tiles-rest-2] (pick-tile (subvec edge 3 4) tiles-rest-1)
        :let [t3-match (find-match orient-1 orient-2 orient-3 tile-1 tile-2 tile-3)]
        [tile-3 orient-3 tiles-rest-3] (pick-tile t3-match tiles-rest)]

        [tiles-rest-3 [[tile-0 orient-0] [tile-1 orient-1] [tile-2 orient-2]] [tile-3 orient-3]]))


(defn symmetry [tls]
  (map (fn [[a b c]] #{[a b c] [b c a] [c a b]}) tls))

(defn -main []
  (let [dist (group-by identity (concat (flatten border) (flatten tiles)))
        syms (group-by identity (symmetry tiles))]
    (zp/czprint (zipmap (keys syms)
                        (map count (vals syms))))
    (zp/czprint (zipmap (keys dist)
                       (map count (vals dist))))))
