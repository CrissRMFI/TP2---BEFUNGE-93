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
			
(defn entrada-user [comando estado]
	(let [caracter (read-line)
		pila (:pila estado)]
		(if (= comando \&)
			(assoc estado :pila (conj  (int (first caracter)) pila))
			(assoc estado :pila (conj  (int (first caracter)) pila))
		)))
	
(defn entrada-user [comando estado]
	(let [entrada (read-line)
		pila (:pila estado)]
			(if (= comando \&)
				(let [numero (Integer. entrada)]
					(assoc estado :pila (conj pila numero)))
				(let [primer-caracter (first entrada)]
					(assoc estado :pila (conj pila (int primer-caracter))))
			)))  



