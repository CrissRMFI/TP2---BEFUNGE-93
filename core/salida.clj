(ns tp2-clojure.core.salida)

(defn modo-string [estado]
	(assoc estado :string-mode (not (:string-mode estado))))
		
(defn salida [comando estado]
	(let [pila (:pila estado)
		[top & rest] pila]
		(if (= comando \.)
			(print  (str top  " "))
			(print (str (if (empty? pila) "" (char top)))))
			(assoc estado :pila (or rest '()))))
