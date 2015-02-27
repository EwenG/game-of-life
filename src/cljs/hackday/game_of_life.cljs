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

(defn neighbours [game i]
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
  (let [alive-neighbour-nb (-> (filter #(= :alive %) neighbours)
                             count)]
    (cond
      (< alive-neighbour-nb 2) :dead
      (= alive-neighbour-nb 2) :alive
      (= alive-neighbour-nb 3) :alive
      (> alive-neighbour-nb 3) :dead)))



(defn cell-step [game cell index]
  (let [neighbours (neighbours game index)]
    (if (= :dead cell)
      (dead-cell-step neighbours)
      (alive-cell-step neighbours))))

(defn game-step* [game indexes]
  (map (partial cell-step game) game indexes))

(defn game-step [game]
  (game-step* game (-> game count range)))

(defn display-game [game]
  (let [tag (.getElementById js/document "game")]
    (aset tag "textContent" (game->string game))))





(let [game (init-game game)
      games (iterate game-step game)]
  #_(display-game game)
  (display-game (nth games 2))
  )








