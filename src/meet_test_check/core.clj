(ns meet-test-check.core)

(defn bad-fn-2
  "Takes a string and returns a collection with the same count as the
  string.

  N.B.: This docstring is false."
  [s]
  (if (and (= (first s) \a)
           (< 7 (count s)))
    "Oh, no!"
    (map (fn [zarp-layer] 1) s)))

(defn bad-fn-3
  "Takes a Long, returns a ratio or an integer.

  N.B.: This fn has a bug."
  [number]
  (/ 23 (mod (- (* 123 23) number) 7)))

(defn bad-fn-4
  "Takes a string and returns a shorter string.

  N.B.: This fn is fine, but this docstring is false."
  [s]
  (subs s (.indexOf s "z") (.indexOf s "a")))

(defn noah?
  "One or two of something is OK. Three or more: right out."
  [things]
  (< (apply max (vals (frequencies things))) 3))
