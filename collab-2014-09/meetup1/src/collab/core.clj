(ns collab.core)

;;;; Functional 2048

;;  1. Familiarize yourself with the game at http://web2048.com

;; We are aiming to create a simple implementation which might be
;; played in a manner like this at the REPL:

;; game-2048.core> (def b (atom (init-board)))
;;     0    0    0    0
;;     2    0    0    0
;;     0    0    0    0
;;     0    0    0    2
; ...gameplay happens...
;; game-2048.core> (swap! b play-round :left)
;;     4    0    0    0
;;     8    0    0    0
;;     2   16    2    0
;;   256    8    8    2
;; game-2048.core> (swap! b play-round :left)
;;     4    0    2    0
;;     8    0    0    0
;;     2   16    2    0
;;   256   16    2    0
;; game-2048.core> (swap! b play-round :down)
;;     4    0    0    0
;;     8    0    0    0
;;     2    2    2    0
;;   256   32    4    0
;; game-2048.core> (swap! b play-round :left)
;;     4    0    0    0
;;     8    0    0    2
;;     4    2    0    0
;;   256   32    4    0
;; game-2048.core>

;; This is just a suggestion, feel free to build what you like. The
;; following steps are also just guidelines but may be helpful to
;; guide you on the path.

;;  2. Consider several board representations and type in these
;;  representations below:

(comment
  #_an_implementation_here
  ;; vectors
  [[4 0 0 0]
   [8 0 0 2]
   [4 2 0 0]
   [256 32 4 0]]

  #_another_implementation_here
  {[0 0] 4
   [0 1] 8
   [3 1] 2
   [0 2] 4
   [1 2] 2
   [0 3] 256
   [1 3] 32
   [2 3] 4}

  #_maybe_a_third_implementation_here
  #_have_you_considered_a_fourth? ;-)
  )

;;  3. Pick one of the above board representations...

;; It might also be a good idea to think about how you want to
;; represent directional moves. You'll need this later. :)

;;  4. Create a 'new-board function which returns an empty board using
;;  the representation you chose.

(defn rand-tile
  "Generate a single random tile value where x,y position is
   in size,size grid and value is 2 or 4"
  [size]
  (let [x (rand-int size)
        y (rand-int size)
        n (if (> (rand) 0.9) 4 2)]
    [[x y] n]))

(defn new-board
  "Returns a new, empty board structure. Takes an optional size
parameter, defaulting to 4x4."
  ([] (new-board 4 2))
  ([size] (new-board size 2))
  ([size tiles]
     (let [empty-board (vec  (repeat size (vec (repeat size 0))))
           starting-tiles (take tiles (distinct (repeatedly (* 10 tiles) #(rand-tile size))))]
       (reduce (fn [board [[x y] n]]
                 (assoc-in board [y x] n))
               empty-board
               starting-tiles))))

;;  5. Create a 'print-board function which prints out the board in a
;;  format useful for debug or game play.

(defn print-board
  "Prints a human readable version of the provided board state."
  [board]
  (println "[")
  (doseq [row board] (println row))
  (println "]")
  #_implementation_here)

;;  6. Create a 'add-random function

(defn add-random
  "Takes a board and returns that board with one of the blank spaces
filled with either a 2 (90% of the time) or a 4 (10% of the time)."
  [board]
  #_implementation_here)

;;  7. create an 'init-board function seeding a new-board with random additons

(defn init-board
  "Returns a new board, populated with two rounds of add-random."
  []
  #_implementation_here)

;;  8. Create 'move-direction function

(defn collapse-row
  [row]
  (loop [x (first row)
         row (rest row)
         result []]
    (if (or (nil? x) (nil? row))
      result
      (let [y (first row)]
        #_(prn x y row result)
        (if (= x y)
          (recur (second row) (rest (rest row)) (conj result (+ x y)))
          (recur y (rest row) (conj result x)))))))

(defn move-row-left
  [row]
  (let [size (count row)
        no-zeros (remove zero? row)
        collapsed (collapse-row no-zeros)
        new-row (first  (partition size size (repeat size 0) collapsed))]
    (or new-row
        (repeat size 0))))

(defn move-row
  [row dir]
  (case dir
    :left (move-row-left row)
    :right (-> row reverse move-row-left reverse)))

(defn move-direction
  "Takes a board and a direction and collapses blanks and duplicated
elements in the specified direction.
Note: Collapsing is a single pass. A row containing '4 4 8 0' will
result in '8 8 0 0', not '16 0 0 0'."
  [board dir]
  (if (#{:left :right} dir)
    (map (fn [row] (move-row row dir)) board)
    (prn "Up and down are bad!"))
)

;;  9. create a 'play-round function which takes a board, a direction, collapsing, adding random and printing

(declare detect-game-end) ; ignore this for now

(defn play-round
  "Takes a board and a direction.
After moving in the specified direction, will 'add-random to the
board. A move which does not move or collapse tiles is not considered
an error but a no-op. In this case, 'add-random should be skipped."
  [board dir]
  #_implementation_here)

;; 10. Detect end of game (full or winning) and call this from 'play-round.

(defn detect-game-end
  "Prints out some fancy message like 'You won!' or 'Board full!'
  given these conditions. Winning happens when one or more tiles is
  equal or greater than 2048. Board full is when there are no blank
  spaces and no tiles that can be collapsed."
  [board]
  #_implementation_here)

