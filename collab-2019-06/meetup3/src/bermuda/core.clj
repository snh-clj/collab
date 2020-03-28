(ns bermuda.core
  [:require [zprint.core :as zp]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]])

;; Based on Bermuda Triangle puzzle: https://goo.gl/h1MEFM

(def default-borders
  [[:BL :RD :GN :BK]
   [:GN :WT :GN :GN]
   [:YL :WT :RD :WT]])

(def default-tiles
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

;; NOTE Terms:
;; pip - a place holding a color constraint or an unconstrained color
;; tile - a configuration of three pips
;; option - a set three of rotations of a tile
;; slot - a place that can hold a tile and possible options
;; board - a triangle configuration of slots

(deftype LVal []
   java.lang.Object
   (equals [_ _] true)
   (toString [_] "<?>"))

(def lval (LVal.))

(defmethod clojure.core/print-method LVal
 [o w]
 (.write w (str o)))

(s/def ::pip #{lval :RD :GN :BL :BK :WT :YL})
(s/def ::tile (s/coll-of ::pip :kind vector? :count 3))
(s/def ::row (s/coll-of ::tile :kind vector?))
(s/def ::board (s/coll-of ::row :kind vector?))

(defn tile= [constraint tilesyms]
  (some #(= constraint %) tilesyms))

(defn symmetry [[a b c]]
  #{[a b c] [b c a] [c a b]})

(defn down?
  "Direction pips at position points, opposite of triangle direction"
  [[r c]]
  (even? c))

#_
(defn constrained-tiles [constraint tile-list]
  (filter #(tile= constraint %) tile-list))

#_
(defn drop-tile [tile tile-map]
  (dissoc tile-map (symmetry tile)))

#_
(defn update-self [board pos tile]
  (let [slot (get-in board pos)
        new-slot (with-meta tile (meta slot))]
       (constrain new-slot)))

(defn neighbors-of [[r c :as pos] board]
  (let [middle (if (down? pos)
                [-1 -1]
                [1 1])
        directions [[0 -1] middle [0 1]]]
      (reduce #(nebbby) board directions))
  (for [d (range 3)
        :when (if (get-in) false)]
       true))

(defn positions [board]
  (for [[r row] (map-indexed list board)
        [c _] (map-indexed list row)]
       [r c]))

(defn set-pip [path board v]
  (assoc-in board path v))

(defn constrained-options [tile options]
  (into {} (filter (fn [[k v]]
                       (tile= tile k))
                   options)))

(defn constrain [slot]
  (let [{:keys [options]} (meta slot)
        new-options (constrained-options slot options)]
      (with-meta slot {:options new-options})))

(defn sort-constrained [board]
  (map second
   (sort
     ;; filter zero count positions
    (map #(vector (-> board (get-in %) meta :options count)
                  %))
    #_
    (map #(vector (apply + (-> board (get-in %) meta :options vals))
                  %)
          (positions board)))))

(defn done [board]
  (not (some pos?
            (map (comp count :options meta)
                 (apply concat board)))))

(defn solved? [board]
  (not (some #(= lval %) (flatten board))))

(defn consume-tile [board tile])

(defn update-self [board position tile]
  (with-meta (update-in position tile)
             (meta (get-in board position))))

(defn update-neighbors [board position tile]
            :let [neighbors (neighbors-of position)
                  board (reduce constrain-ish board nbs)
                  board (limit board)])

(defn try-tile [board position tile]
  (-> board
    (update-self position tile)
    (update-neighbors position tile)
    (consume-tile tile)))

(defn solve [board]
  (if (done board)
   (when (solved? board)
     board)
   ;; wants to be loop.. recur
   (for [position (sort-constrained board)
         [option _] (-> (board position) meta :options)
         :let [board (solve (try-tile board position option))]
         :when somethin'?
         :while somethin'?]
      board)))


(defn sym-tile-counts [tiles]
  (let [syms (group-by identity (map symmetry tiles))]
    (zipmap (keys syms)
            (map count (vals syms)))))

(defn build-board [borders tiles]
  (let [size (count (first borders))
        opts (sym-tile-counts tiles)]
   (into []
    (for [r (range size)
          :let [cmax (* 2 r)]]
      (into []
       (for [c (range (inc cmax))
             :let [t (cond-> [lval lval lval]
                        (= c 0)                          (assoc 0 (nth (reverse (borders 0)) r))
                        (and (= r (dec size)) (even? c)) (assoc 1 (nth (borders 2) (/ c 2)))
                        (= c cmax)                       (assoc 2 (nth (borders 1) r)))]]
        (constrain (with-meta t {:options opts}))))))))

(def triangle-templates
  [[ "       " "/\\"]
   [ "      " "/  \\"]
   [ "     " "/    \\"]
   [ "    " "/%s  %s\\"]
   [ "   " "/        \\"]
   [ "  " "/    %s    \\"]
   [ " " "--------------"]])

(defn apply-row-format [row-format row]
  (apply format row-format
                (map #(if (= % lval) "??" name)
                      (flatten (concat (map (fn [[a b c]] [a c]) row)
                                       (map (fn [[a b c]] [ b ]) row))))))

(defn compose-row-format [formats]
  (str/join "\n" (apply map #(str/join "" %&) formats)))

(defn triangle-strings [template board]
  (let [[pad shape] (apply map list template)
        blank (map #(apply str (map (constantly " ") (str %1 %2))) shape (reverse shape))
        row-count (count board)
        blanks (take row-count (map #(take % %2) (iterate dec (dec row-count))
                                                 (repeat blank)))
        shapes (concat [shape] (iterate reverse shape))
        rows (map #(concat %1 [pad] %2) blanks triangles)
        rows-formats (for [[row row-num] (map list board (range))]
                         (let [row-format (concat (repeat (- row-count row-num) blanks)
                                                  [pad]
                                                  (take (inc (count row)) shapes))]
                           (compose-row-format row-format)))
        _ (prn :row-formats row-formats)
        output (str/join "\n" (map apply-row-format rows-formats board))]
      (prn :blanks blanks)
      (prn :row-count row-count)
      output))


(defn -main []
  (let [dist (group-by identity (concat (flatten default-borders) (flatten default-tiles)))
        syms (group-by identity (map symmetry default-tiles))]
    (zp/zprint (zipmap (keys syms)
                       (map count (vals syms))))
    (zp/zprint (zipmap (keys dist)
                      (map count (vals dist))))))

(comment
  (s/valid? ::board [[[lval :BK :PP]]]))
