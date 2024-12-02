(ns tp2-clojure.direccion
	(:require [clojure.core :refer [rand-nth]]))
	
(defn cambiar-direccion [estado nueva-direccion]
	(assoc-in estado [:pc :dir] nueva-direccion))

(defn direccion-aleatoria [estado]
	(let [pc (:pc estado)
	nueva-direccion (rand-nth [:derecha :izquierda :arriba :abajo])]
	(assoc-in estado [:pc :dir] nueva-direccion)))

(defn movimiento-PC [pc]
  (let [{:keys [x y dir]} pc]
    (case dir
      :derecha   {:x (mod (inc x) 80) :y y :dir dir}
      :izquierda {:x (mod (dec x) 80) :y y :dir dir}
      :abajo     {:x x :y (mod (inc y) 25) :dir dir}
      :arriba    {:x x :y (mod (dec y) 25) :dir dir})))
      
(defn saltar-instruccion [estado]
	(assoc estado :pc (movimiento-PC (:pc estado))))
	

