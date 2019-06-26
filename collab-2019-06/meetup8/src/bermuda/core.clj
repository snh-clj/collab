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

(defn symmetry [tls]
  (map (fn [[a b c]] #{[a b c] [b c a] [c a b]}) tls))

(defn -main []
  (let [dist (group-by identity (concat (flatten border) (flatten tiles)))
        syms (group-by identity (symmetry tiles))]
    (zp/czprint (zipmap (keys syms)
                        (map count (vals syms))))
    (zp/czprint (zipmap (keys dist)
                       (map count (vals dist))))))
