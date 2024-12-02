(ns tp2-clojure.iniciacion
	(:require [tp2-clojure.ejecutar :as ejecutar]))

(defn estado-inicial [grid]
	{ :grid (vec grid)
	:pila '()
	:pc {:x 0 :y 0 :dir :derecha}
	:string-mode false
	:output ""})

(defn iniciar-programa [estado]
	(loop [estado estado]
	(let [pc (:pc estado)
          	grid (:grid estado)
          	x (:x pc)
          	y (:y pc)
          	comando (get-in grid [y x])
          	pila (:pila estado)]
          	(if (and (not(:string-mode estado)) (= comando \@)) 
          	(:output estado)
          	(let [nuevo-estado
          			(ejecutar/ejecutar estado)]
          			(recur nuevo-estado))))))
