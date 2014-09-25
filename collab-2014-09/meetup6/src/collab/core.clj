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
  [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]]
  [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
  {[0 0] 0 [0 1] 0 [0 2] 0 ... 0}
  #_an_implementation_here
  #_another_implementation_here
  #_maybe_a_third_implementation_here
  #_have_you_considered_a_fourth? ;-)
  )

;;  3. Pick one of the above board representations...

  [[0 0 0 0] [0 0 0 0] [0 0 0 0] [0 0 0 0]]

;; It might also be a good idea to think about how you want to
;; represent directional moves. You'll need this later. :)

;;  4. Create a 'new-board function which returns an empty board using
;;  the representation you chose.

(defn new-board
  "Returns a new, empty board structure. Takes an optional size
parameter, defaulting to 4x4."
  ([] (new-board 4))
  ([size]
     (vec (repeat size (vec (repeat size 0))))))

;;  5. Create a 'print-board function which prints out the board in a
;;  format useful for debug or game play.

(defn print-board
  "Prints a human readable version of the provided board state."
  [board]
  (doall (map println board)))

;;  6. Create a 'add-random function

(defn add-random
  "Takes a board and returns that board with one of the blank spaces
filled with either a 2 (90% of the time) or a 4 (10% of the time)."
  [board]
  (let [size (count board)
        blanks (for [y (range size)
                     x (range size)
                     :when (zero? (get-in board [x y]))]
                 [x y])
        location (rand-nth blanks)
        randvalue (rand-nth (into [4] (repeat 9 2)))]
    (assoc-in board location randvalue)))

;;  7. create an 'init-board function seeding a new-board with random additons

(defn init-board
  "Returns a new board, populated with two rounds of add-random."
  []
  (-> (new-board) add-random add-random)
  )

;;  8. Create 'move-direction function

(defn shift-left
  "Take a single vector and shift and combine left"
  [row]
  (->> row
       (remove zero?)
       (partition-by identity)
       (mapcat #(partition-all 2 %))
       (map #(apply + %))
       (#(concat % (repeat 0)))
       (take (count row))
       vec))

(defn move-direction
  "Takes a board and a direction and collapses blanks and duplicated
elements in the specified direction.
Note: Collapsing is a single pass. A row containing '4 4 8 0' will
result in '8 8 0 0', not '16 0 0 0'."
  [board dir]
  #_implementation_here)

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

