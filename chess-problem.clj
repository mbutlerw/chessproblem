(ns chess-problem)
  ;; (:require [clojure.contrib.seq-utils :as s-utils]))

;; Backrow of the chess board.
;; Bishops are on different colours.
;; King has to be between the rooks.
;; A function that takes a number n
;; And returns n valid backrows.

(def std-backrow [:r :kn :b :ki :q :b :kn :r])
(def inv-backrow [:r :kn :b :ki :b :q :kn :r])
(def invk-backrow [:r :kn :b :r :b :ki :q :kn])

(defn gen-backrow []
  (shuffle std-backrow))

(gen-backrow)

(defn gen-backrows []
  (repeatedly gen-backrow))

(defn many-backrows [number]
  (take number (filter #(and (valid-bishops %) (valid-king %)) (gen-backrows))))

(defn index-of [row match]
  (keep-indexed
   (fn [idx val] (when (= match val) idx))
   row))

(defn valid-bishops [row]
  (odd? (apply - (index-of row :b))))

(valid-bishops std-backrow)
(valid-bishops inv-backrow)

(defn valid-king [row]
  (and (< (first (index-of row :r)) (first (index-of row :ki)))
  (> (second (index-of row :r)) (first (index-of row :ki))))
)


(valid-king invk-backrow)

(many-backrows 10)

;; (index :a [:a :b])

;; (defn indexes [index item pred]
;;   (if (my-pred item)
;;     index
;;     nil))

;; (defn my-pred [item]
;;     (= :b item))

;; (keep-indexed #(indexes % %2 my-pred)
;;  std-backrow)
