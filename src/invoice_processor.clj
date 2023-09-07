(ns invoice-processor
  (:require
    [clojure.spec.alpha :as s]
    [clojure.data.json :as json]
    [invoice-spec :as spec]))

(defn load-json [file-name]
  (json/read-str (slurp file-name)))
(defn adjust-invoice-map [invoice-map]
  (pr-str invoice-map ))
;I have a error, because i cant parse json to spec
(defn validate-invoice [invoice-map]
  (let [result (s/valid? ::spec/invoice invoice-map)]
    (if (not result)
      (println (s/explain ::spec/invoice invoice-map)))
    result))

(defn process-invoice [file-name]
  (let [raw-invoice-map (load-json file-name)
        adjusted-invoice-map (adjust-invoice-map raw-invoice-map)]
    (if (validate-invoice adjusted-invoice-map)
      adjusted-invoice-map
      )))

(defn -main []
  (let [file-name "invoice.json"]
    (let [result (process-invoice file-name)]
      (if result
        (println "Valid invoice")
        (println "Invalid invoice" ))
      )))



