(ns hackday.game-of-life)

(def game
  (let [deads (repeatedly 100 (constantly :dead))
        alives (take 10 (repeatedly (fn [] (rand-int 100))))
        zipped (mapv (fn[d i] [d i]) deads (range (count deads)))
        board (partition 10 (map (fn[[d i]](if (some (fn[x] (= x i)) alives) :alive d)) zipped))]
    (mapv (fn[l](vec l)) board)))
    ;;(mapv (fn[row i] [(mapv (fn[c i] [c i]) row (range (count row))) i]) board (range (count board)))))

(defn game->string [game]
  (->> (interpose "\n" game)
      (apply str)))

(defn neighbours [game l c]
  (let [
        _ (.log js/console (str (get-in game [3 5])))
        _ (.log js/console (str game))
        _ (.log js/console (str [(- l 1) c]))
        _ (.log js/console (get-in game [3, 4]))
        h (get-in game [l (+ c 1)])
        g (get-in game [l (- c 1)])
        f (get-in game [(- l 1) (- c 1)])
        e (get-in game [(- l 1) (+ c 1)])
        d (get-in game [(- l 1) c])
        a (get-in game [(+ l 1) c])
        b (get-in game [(+ l 1) (+ c 1)])
        c (get-in game [(+ l 1) (- c 1)])]
    [a b c d e f g h]))

#_(step [game])

;(.log js/console (game->string game))

;(.log js/console (str game))

;(.log js/console (str (get-in game [3 5])))


(def x
  [[:dead :dead :dead :alive :dead :dead :alive :dead :dead :dead] [:dead :dead :dead :dead :dead :dead :dead :dead :dead :dead] [:dead :alive :dead :dead :dead :dead :dead :dead :dead :dead] [:dead :dead :dead :dead :dead :dead :dead :dead :dead :dead] [:dead :dead :dead :dead :alive :dead :dead :dead :alive :dead] [:dead :dead :dead :alive :dead :dead :dead :dead :dead :alive] [:alive :dead :dead :alive :dead :dead :dead :dead :dead :dead] [:dead :dead :dead :dead :dead :dead :dead :dead :dead :dead] [:dead :dead :dead :dead :dead :dead :dead :dead :dead :dead] [:dead :alive :dead :dead :dead :dead :dead :dead :dead :dead]])

(.log js/console (str (get-in x [3 4])))

(.log js/console (str (neighbours x 4 4)))
