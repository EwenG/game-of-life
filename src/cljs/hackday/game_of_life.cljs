(ns hackday.game-of-life)

(def param 10)
(def game (-> (repeatedly (* param param)
                          (constantly :dead))
              vec))

(defn game->string [game]
  (->> (interpose "\n" (partition 10 game))
      (apply str)))

(defn init-game [game]
  (let [init-indexes (take 10 (repeatedly (fn [] (rand-int 100))))
        init-values (interpose :alive init-indexes)]
    init-game (apply (partial assoc game) init-values)))

(defn neighbour-vals [game i]
  [(get game (- i (+ param 1)))
   (get game (- i param))
   (get game (- i (- param 1)))
   (get game (+ i 1))
   (get game (+ i (+ param 1)))
   (get game (+ i param))
   (get game (+ i (- param 1)))
   (get game (- i 1))])

(defn dead-cell-step [neighbours]
  (if (= 3 (-> (filter #(= :alive %) neighbours)
               count))
    :alive
    :dead))

(defn alive-cell-step [neighbours]
  :alive)

(defn cell-step [cell neighbours]
  (if (= :dead cell)
     (dead-cell-step neighbours)
    :alive))

(defn game-step* [game neighbours]
  (map cell-step game neighbours))

(defn game-step [game]
  (let [neighbours (map (partial neighbour-vals game)
                        (range (count game)))]
    (game-step* game neighbours)))

(let [game (init-game game)]
  (.log js/console (str (game->string game)))
  (.log js/console (str (-> game
                            game-step
                            game->string)))
  )







