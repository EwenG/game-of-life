(ns hackday.game-of-life)

(defn gen-cell []
  (let [x (rand-int 2)]
    (if (= 0 x) :dead :alive)))

(def game (repeatedly 100 (constantly :dead)))

(defn game->string [game]
  (->> (interpose "\n" (partition 10 game))
      (apply str)))

#_(.log js/console (apply str (game->string game)))
(.log js/console (str (gen-cell)))

(let [init-indexes (take 10 (repeatedly (fn [] (rand-int 100))))
      init-values (doall (interpose :alive init-indexes))
      init-game (apply (partial assoc game) init-values)]
  (.log js/console (str init-values)))

(.log js/console (str (apply assoc [[1] 0 3])))



(comment

  (.log js/console "e")

  )

