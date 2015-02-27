(ns hackday.game-of-life)

(def param 10)
(def game (-> (repeatedly (* param param)
                          (constantly :dead))
              vec))

(defn game->string [game]
  (->> (interpose "\n" (partition 10 game))
      (apply str)))

(defn neighbour-vals [game i]
  [(get game (- i (+ param 1)))
   (get game (- i param))
   (get game (- i (- param 1)))
   (get game (+ i 1))
   (get game (+ i (+ param 1)))
   (get game (+ i param))
   (get game (+ i (- param 1)))
   (get game (- i 1))])

(defn init-game [game]
  (let [init-indexes (take 10 (repeatedly (fn [] (rand-int 100))))
        init-values (interpose :alive init-indexes)]
    init-game (apply (partial assoc game) init-values)))


(.log js/console (str (neighbour-vals (-> game init-game) 0)))



(comment

  (.log js/console "e")

  )

