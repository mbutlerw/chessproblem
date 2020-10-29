;; Backrow of the chess board.
;; Bishops are on different colours.
;; King has to be between the rooks.
;; A function that takes a number n
;; And returns n valid backrows.

(def std-backrow [:r :kn :b :ki :q :b :kn :r])

(defn gen-backrow []
  (shuffle std-backrow))

(defn gen-backrows []
  (repeatedly gen-backrow))

(defn index-of [row match]
  (keep-indexed
   (fn [idx val] (when (= match val) idx))
   row))

(defn valid-bishops [row]
  (odd? (apply - (index-of row :b))))

(defn valid-king [row]
  (and (< (first (index-of row :r)) (first (index-of row :ki)))
  (> (second (index-of row :r)) (first (index-of row :ki)))))

(vde)
(defn many-backrows [number]
  (take number (filter #(and (valid-bishops %) (valid-king %)) (gen-backrows))))

(many-backrows 2)
