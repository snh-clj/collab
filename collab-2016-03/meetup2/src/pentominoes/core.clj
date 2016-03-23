(ns pentominoes.core)

;; https://en.wikipedia.org/wiki/Pentomino

;; Algorithm:
;;
;; - consider last square added "old"
;; - right most face of "old" is the next spot to attempt to put a square
;;   (we'll call this position 0)
;; - if a configuration is a rotation or reflection of something
;;   already enumerated, skip it. Otherwise, remember it.
;; - subsequent spaces enumerate clockwise from that face (1,2,3...)
;; - if the right most face is blocked, next clockwise open space
;;   starts enumeration (at 0)

;; X - 0
;;
;; ==
;;
;; AZ - 0,0
;;
;; ==
;;
;; AOZ - 0,0,0
;;
;; AO  - 0,0,1
;;  Z
;;
;; ==
;;
;; AXOZ - 0,0,0,0
;;
;; AXO  - 0,0,0,1
;;   Z
;;
;; AXO  - 0,0,0,2
;;  Z
;;
;; -
;;
;; AX   - 0,0,1,0
;;  OZ
;;
;; AX   - 0,0,1,1
;; ZO
;;
;; ==
;;
;; AXXOZ 0,0,0,0,0
;;
;; AXXO  0,0,0,0,1
;;    Z
;;
;; AXXO  0,0,0,0,2
;;   Z
;;
;; -
;;
;; AXX   0,0,0,1,0
;;   OZ
;;
;; AXX   0,0,0,1,1
;;   O
;;   Z
;;
;; AXX   0,0,0,1,2
;;  ZO
;;
;; AXX   0,0,0,1,3
;; Z O
;;
;; Z
;; AXX   0,0,0,1,4
;;   O
;;
;;  Z
;; AXX   0,0,0,1,5
;;   O
;;
;;   Z
;; AXX   0,0,0,1,6
;;   O
;;
;; -
;;
;;  Z
;; AXX  0,0,0,2,0
;;  O
;;
;; -
;;
;; AX
;;  XO    0,0,1,0,1
;;   Z
;;

(def pentominoes "
  XXXXX

  XXXX
     X

  XXXX
    X

  X
  XXX
    X

  X
  XXX
   X

   X
  XXX
   X

  X
  XXX
  X

  X
  X
  XXX

  X
  XX
   XX

  XXX
    XX

  XXX
   XX

  XXX
  X X
")
