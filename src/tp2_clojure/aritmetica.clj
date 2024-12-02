(ns tp2-clojure.aritmetica)

(defn operacion [tipo estado]
	(let [pila (:pila estado)
		[a b & rest] pila]
		(if (= tipo \!)
			(assoc estado :pila (conj (cons b rest) (if (= 0 a) 1 0)))
			(assoc estado :pila (conj rest (int (tipo b a)))))))

