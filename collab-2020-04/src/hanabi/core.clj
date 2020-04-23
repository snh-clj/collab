#!/home/meetup/bin/bb
(ns hanabi.core)

(def colors #{:y :b :r :g :w})

(def cards (for [n [1 1 1, 2 2, 3 3, 4 4, 5]
                 c colors]
                [n c]))

(defn new-game [nplayers]
  {:draw-pile cards
   :discard []
   :placed (zipmap colors (repeat []))
   :hands (repeat nplayers [])
   :clue-avail 8
   :clue-used 0
   :bomb 3})

(defn color-of-card [[number color]]
  color)

(defn number-of-card [[number color]]
  number)

(defn shuffle-deck [{:keys [draw-pile] :as game-state}]
  (assoc game-state :draw-pile (shuffle draw-pile)))

(defn cards-per-player [nplayers]
  ({2 5, 3 5, 4 4, 5 4} nplayers))

(defn draw-cards [{:keys [draw-pile hands] :as game-state}]
  (let [nplayers (count hands)
        ncards (cards-per-player nplayers)]
    (loop [hands hands donehands [] draw-pile draw-pile]
       (let [[my-hand & hands] hands]
         (if my-hand
           (let [n-my-hand (count my-hand)
                 [my-draw new-draw-pile] (split-at (- ncards n-my-hand) draw-pile)]
             (recur hands (conj donehands (concat my-hand my-draw)) new-draw-pile))
           (merge game-state {:hands donehands :draw-pile draw-pile}))))))

(defn discard-card
  "Adds the given 'card to the discard pile, returning a clue token if available."
  [{:keys [discard clue-avail clue-used] :as game-state} card]
  (prn :discard card)
  (merge game-state
        {:discard (conj discard card)}
        (when (< 0 clue-used)
          {:clue-avail (inc clue-avail)
           :clue-used (dec clue-used)})))

(defn player-discard-first-card
  "Player function: Discards first card in players hand. This is really dumb."
  [{:keys [hands] :as game-state}]
  (let [[first-hand & other-hands] hands
        [first-card & my-hand] first-hand
        _ (prn :first-card first-card)
        new-state (discard-card game-state first-card)]
    (assoc new-state :hands (concat [my-hand] other-hands))))

(defn play-card-strict
  "Adds the given 'card to the discard pile, returning a clue token if available."
  [{:keys [placed discard bomb] :as game-state} color card]
  (if (and (= color (color-of-card card))
           (= (number-of-card card) (-> placed color last inc)))
    (update-in game-state [:placed color] conj card )
    (merge game-state {:bomb (dec bomb)
                       :discard (conj discard card)})))

(defn play-card-fast-n-loose
  "Adds the given 'card to the discard pile, returning a clue token if available."
  [{:keys [discard clue-avail clue-used] :as game-state} card]
  (prn :discard card)
  (merge game-state
         {:discard (conj discard card)}
         (when (< 0 clue-used)
           {:clue-avail (inc clue-avail)
            :clue-used (dec clue-used)})))

(defn player-play-first-card
  "Player function: Plays first card in players hand. This is really dumb."
  [{:keys [hands] :as game-state}]
  (let [[first-hand & other-hands] hands
        [first-card & my-hand] first-hand
        _ (prn :first-card first-card)
        new-state (discard-card game-state first-card)]
    (assoc new-state :hands (concat [my-hand] other-hands))))

(defn next-player
  "Rotates player positions. The first player is always the next to take their turn."
  [{:keys [hands] :as game-state}]
  (let [[first-hand & hands] hands]
    (assoc game-state :hands (into (vec hands) [first-hand]))))

(defn play-rounds
  [{:keys [bomb hands] :as game-state} take-turn]
  (let [nplayers (count hands)
        my-hand (first hands)
        ncards (cards-per-player nplayers)]
    (if (and (< 0 bomb) (= ncards (count my-hand)))
      (recur (-> game-state take-turn draw-cards next-player) take-turn)
      game-state)))

(defn count-score [{:keys [bomb placed]}]
  (if (= bomb 0)
     -1
     (count (apply concat (vals placed)))))

(defn play-game [game-state playerfn]
  (-> game-state
    shuffle-deck
    draw-cards
    (play-rounds playerfn)
    #_count-score))

(comment
  )
  (clojure.pprint/pprint (play-game (new-game 4) player-discard-first-card))

