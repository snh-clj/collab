(ns snjclj-loco-demo.core
  (:use loco.core loco.constraints))

(def model
  [($in :x 1 6)  ; x is in the domain ranging from 1 to 6, inclusive
   ($in :y 3 7)  ; y is in the domain ranging from 3 to 7, inclusive
   ($= ($+ :x :y) 10)])

;; TODO: motherboards can have CPU and memory constraints

;; motherboards -> sata ports, cost
;; sata drives -> size, cost
;; logical volume -> N drives, redundancy scheme
;; redundancy scheme -> % usable data

(def mobos
  #{[:mobo1 10 150]
    [:mobo2 8 100]
    [:mobo3 8 200]})

(def disks
  #{[:disk1 1000 60]
    [:disk2 2000 110]
    [:disk3 4000 130]})

(def disk-inventories
  #{1 2 3})

(def config
  [($in :mobo-cost 0 1000)
   ($in :mobo-slots 1 100)
   ($in :disk1-cost 0 1000)
   ($in :disk1-size 0 100000)
   ($in :disk2-cost 0 1000)
   ($in :disk2-size 0 100000)
   ($and ($or ($and ($in :mobo-slots 1 10)
                    ($= :mobo-cost 150))
              ($and ($in :mobo-slots 1 8)
                    ($= :mobo-cost 100))
              ($and ($in :mobo-slots 1 8)
                    ($= :mobo-cost 200)))
         ($or ($and ($= :disk1-cost 60)
                    ($= :disk1-size 1000))
              ($and ($= :disk1-cost 110)
                    ($= :disk1-size 2000))
              ($and ($= :disk1-cost 130)
                    ($= :disk1-size 4000)))
         ($or ($and ($= :disk2-cost 60)
                    ($= :disk2-size 1000))
              ($and ($= :disk2-cost 110)
                    ($= :disk2-size 2000))
              ($and ($= :disk2-cost 130)
                    ($= :disk2-size 4000))))

   ($<= :disk1-size :disk2-size)

   ($in :mobo-slots1 1 100)
   ($in :mobo-slots2 1 100)
   ($= :mobo-slots ($+ :mobo-slots1 :mobo-slots2))

   ($in :total-size 13000 100000)
   ($= :total-size ($+ ($* :disk1-size :mobo-slots1)
                       ($* :disk2-size :mobo-slots2)))

   ($in :total-cost 0 10000000)
   ($= :total-cost ($+ :mobo-cost
                     ($* :disk1-cost :mobo-slots1)
                     ($* :disk2-cost :mobo-slots2)))])
