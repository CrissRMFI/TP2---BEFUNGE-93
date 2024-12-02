(ns tp2-clojure.core
  (:require 	[tp2-clojure.iniciacion :as iniciacion]))

(defn leer-archivo [ruta]
  (with-open [reader (clojure.java.io/reader ruta)]
    (doall (map vec (line-seq reader)))))


(defn ejecutar-archivo [ruta]
  (let [grid (leer-archivo ruta)
        estado (iniciacion/estado-inicial grid)]
        (iniciacion/iniciar-programa estado)))

(defn -main [& args]
	(let [ruta (first args)] 
    	(ejecutar-archivo ruta)))
