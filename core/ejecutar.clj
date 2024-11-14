(ns tp2-clojure.core.ejecutar
(:require [tp2-clojure.core.aritmetica :as aritmetica]
  			[tp2-clojure.core.pila :as pila]
  			[tp2-clojure.core.direccion :as direccion]
  			[tp2-clojure.core.salida :as salida])
)

(defn ejecutar-comando [comando estado]
	(case comando
      \+ (aritmetica/operacion + estado)
	\- (aritmetica/operacion - estado)      
      \* (aritmetica/operacion * estado)
      \/ (aritmetica/operacion / estado)
      \% (aritmetica/operacion mod estado)
      \! (aritmetica/operacion \! estado)
      \` (pila/agregar-uno estado)
      \> (direccion/cambiar-direccion estado :derecha)
      \< (direccion/cambiar-direccion estado :izquierda)
      \v (direccion/cambiar-direccion estado :abajo)
      \^ (direccion/cambiar-direccion estado :arriba)
      \? (direccion/direccion-aleatoria estado)
      \_ (pila/horizontal-si estado)
      \| (pila/vertical-si estado)
      \" (salida/modo-string estado)
      \: (pila/duplica-top-pila estado)
      \\ (pila/swap estado)
      \$ (pila/eliminar-primero estado)
      \. (salida/salida comando estado)
      \, (salida/salida comando estado)
      \# (direccion/saltar-instruccion estado)
      \g (pila/obtener-elemento estado)
      \p (pila/colocar-elemento estado)
      \& (salida/entrada-user comando estado)
      \~ (salida/entrada-user comando estado)
      (pila/apilar estado comando))
)

(defn ejecutar [estado]
  (let [pc (:pc estado)
        grid (:grid estado)
        x (:x pc)
        y (:y pc)
        comando (or (get-in grid [y x]) \space)
        cadena? (:string-mode estado)
        nuevo-estado (if  (= comando \")
		(salida/modo-string estado)
		(if cadena? 
		(pila/apilar estado (int comando))
		(ejecutar-comando comando estado)
		))]
		(assoc nuevo-estado :pc (direccion/movimiento-PC (:pc nuevo-estado)))))

