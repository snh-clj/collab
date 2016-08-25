(ns specifically-spec.core
  (:require [clojure.spec :as s]
            [clojure.spec.test :as test]
            [clojure.spec.gen :as gen]))

;; http://clojure.org/about/spec
;; http://clojure.org/guides/spec
;; http://clojure.github.io/clojure/branch-master/clojure.spec-api.html

;; http://blog.cognitect.com/blog/?tag=clojure.spec
;; v--v
;; http://blog.cognitect.com/blog/2016/7/13/screencast-spec-leverage
;; http://blog.cognitect.com/blog/2016/7/26/clojure-spec-screencast-testing
;; http://blog.cognitect.com/blog/2016/8/9/focus-on-spec-predicates
;; http://blog.cognitect.com/blog/2016/8/10/clojure-spec-screencast-customizing-generators

"
Suggestions:

[simple data structures]
int
str
name ; test cases: handles symbol, keyword, string (w & w/o ns, as appropriate)
...
with-meta ; no transmogrification (fantastic data structures and where defined them)
map
reduce

--

testing:
- a couple of select edge cases
- a run through some number of generated cases
- not testing the generator (we catch generators that generate invalid
  values but not assure that the generator generates all classes of
  valid values)

"

(def a-map
  {:card/suit :heart
   :card/rank :king})

(s/def :card/suit
  #{:heart :diamond :spade :club})

(s/def :card/rank
  (s/or
    :card/rank-numeric 
    (s/and
      integer?
      #(> % 1)
      #(< % 11))
    :card/rank-keyword
    #{:jack :queen :king :ace}))

(s/def :card/card
  (s/keys :req [:card/rank :card/suit]))

(comment

  (s/valid? :card/suit (:card/suit a-map))
  (s/valid? :card/rank (:card/rank a-map))
  (s/valid? :card/card a-map)

  (s/conform :card/card a-map)

  (clojure.pprint/pprint
    (s/exercise :card/card))

  )

(comment
  (let [pred _]
    (map #(s/conform pred %) [2 4 6 8]))

  (s/conform (s/coll-of _) [2 4 6 8])

  (s/conform (s/coll-of _) #{2 4 6 8})
  )
