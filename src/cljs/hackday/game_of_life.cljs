(ns hackday.game-of-life)

(set! *print-fn* #(.log js/console %))

(def param 10)



(def old-game #js ["dead" "dead" "dead" "alive" "dead" "dead" "alive" "dead" "alive" "dead" "alive" "alive" "dead" "alive" "alive" "dead" "dead" "dead" "alive" "alive" "alive" "dead" "alive" "alive" "alive" "alive" "dead" "dead" "dead" "alive" "dead" "dead" "alive" "alive" "dead" "dead" "dead" "dead" "alive" "dead" "dead" "dead" "dead" "alive" "alive" "dead" "alive" "dead" "dead" "alive" "alive" "dead" "dead" "dead" "alive" "dead" "alive" "dead" "alive" "alive" "alive" "dead" "dead" "dead" "alive" "alive" "alive" "dead" "alive" "dead" "dead" "alive" "alive" "dead" "dead" "alive" "alive" "alive" "dead" "dead" "dead" "dead" "dead" "alive" "alive" "alive" "dead" "dead" "dead" "dead" "dead" "alive" "dead" "dead" "dead" "dead" "alive" "dead" "dead" "dead"])
(def new-game #js [])

(defn game->string [game]
  (->> (js->clj game)
       (partition 10)
       (interpose "\n")
       (apply str)))


(defn neighbours [game i]
  #js [(aget game (- i (+ param 1)))
   (aget game (- i param))
   (aget game (- i (- param 1)))
   (aget game (+ i 1))
   (aget game (+ i (+ param 1)))
   (aget game (+ i param))
   (aget game (+ i (- param 1)))
   (aget game (- i 1))])

(defn dead-cell-step [neighbours]
  (if (= 3 (-> (filter #(= "alive" %) neighbours)
               count))
    "alive"
    "dead"))

(defn alive-cell-step [neighbours]
  (let [alive-neighbour-nb (-> (filter #(= "alive" %) neighbours)
                               count)]
    (cond
      (< alive-neighbour-nb 2) "dead"
      (= alive-neighbour-nb 2) "alive"
      (= alive-neighbour-nb 3) "alive"
      (> alive-neighbour-nb 3) "dead")))

(defn cell-step [game cell index]
  (let [neighbours (neighbours game index)]
    (if (= "dead" cell)
      (dead-cell-step neighbours)
      (alive-cell-step neighbours))))




#_(.log js/console (str (game->string old-game)))




(comment
(set! *print-fn* #(.log js/console %))

(def param 10)

(defn game->string [game]
  (->> (interpose "\n" (partition 10 game))
      (apply str)))

(defn init-game [game]
  (let [init-indexes (take 60 (repeatedly (fn [] (rand-int 100))))
        init-values (vec (interpose :alive init-indexes))
        init-values (conj init-values :alive)]
    init-game (apply (partial assoc game) init-values)))

(defn neighbours [game i]
  [(nth game (- i (+ param 1)) nil)
   (nth game (- i param) nil)
   (nth game (- i (- param 1)) nil)
   (nth game (+ i 1) nil)
   (nth game (+ i (+ param 1)) nil)
   (nth game (+ i param) nil)
   (nth game (+ i (- param 1)) nil)
   (nth game (- i 1) nil)])

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



#_(def game (-> (repeatedly (* param param)
                          (constantly :dead))
              vec
              init-game))

(def game [:dead :dead :dead :alive :dead :dead :alive :dead :alive :dead :alive :alive :dead :alive :alive :dead :dead :dead :alive :alive :alive :dead :alive :alive :alive :alive :dead :dead :dead :alive :dead :dead :alive :alive :dead :dead :dead :dead :alive :dead :dead :dead :dead :alive :alive :dead :alive :dead :dead :alive :alive :dead :dead :dead :alive :dead :alive :dead :alive :alive :alive :dead :dead :dead :alive :alive :alive :dead :alive :dead :dead :alive :alive :dead :dead :alive :alive :alive :dead :dead :dead :dead :dead :alive :alive :alive :dead :dead :dead :dead :dead :alive :dead :dead :dead :dead :alive :dead :dead :dead])



(def games (iterate game-step game))


(defn loop-game [i]
  (time (loop [game game
               i i]
          (if (= i 0)
            (game->string game)
            (recur (game-step game) (- i 1))))))



(defn nth-game [i]
  (display-game (nth games i)))


(display-game game)

(.log js/console (str game))

)





