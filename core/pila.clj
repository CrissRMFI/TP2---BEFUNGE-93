(ns tp2-clojure.core.pila
	(:require [tp2-clojure.core.direccion :as direccion])
)

(defn agregar-uno [estado]
	(let [pila (:pila estado)
	[a b & rest] pila]
	(if (> b a)
      (assoc estado :pila (cons 1 pila))
      (assoc estado :pila (cons 0 pila)))))

(defn duplica-top-pila [estado]
	(let [pila (:pila estado)]
	(assoc estado :pila (cons (or (first pila) 0) pila))))

(defn swap [estado]
	(let [pila (:pila estado)
	[a b & rest] pila]
	(assoc estado :pila (cons (or b 0) (cons (or a 0) rest)))))
	 
(defn eliminar-primero [estado]
	(let [pila (:pila estado)]
		(assoc estado :pila (rest pila))))

(defn apilar [estado comando]
	(let [pila (:pila estado)]
		(if (not (= comando \space))
		(let [valor (if (Character/isDigit comando)
			(- (int comando) (int \0))
			(int comando))]
			(assoc estado :pila (conj pila valor)))
		estado))) 

(defn obtener-elemento [estado]
	(let [pila (:pila estado)
		grid (:grid estado)
		[a b & rest] pila
		c (or (get-in grid [a b]) 0)]
		(assoc estado :pila (cons (int c) (or rest '())))))

(defn colocar-elemento [estado]
	(let [pila (:pila estado)
		[a b c & rest] pila
		grid (:grid estado)
		nueva-grid (assoc-in grid [a b] (char c))]
		(assoc estado :grid nueva-grid :pila (or rest '()))))
    
(defn horizontal-si [estado]
	(let [pila (:pila estado)
		[top & rest] pila]
	(-> estado
		(assoc :pila (or rest '()))
		(direccion/cambiar-direccion (if (= top 0) :derecha :izquierda)))))

(defn vertical-si [estado]
	(let [pila (:pila estado)
		[top & rest] pila]
	(-> estado
        (assoc :pila (or rest '()))
        (direccion/cambiar-direccion (if (= top 0) :abajo :arriba)))))
