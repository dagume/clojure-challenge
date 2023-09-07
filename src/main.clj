(ns main
  (:require [clojure.edn :as edn]))

(def invoice (clojure.edn/read-string (slurp "../invoice.edn")))

(defn iva-19? [item]
  (some #(= (:tax/rate %) 19) (:taxable/taxes item)))

(defn retention-1? [item]
  (some #(= (:retention/rate %) 1) (:retentionable/retentions item)))

(defn meets-conditions? [item]
  (let [meets-iva (iva-19? item)
        meets-retention (retention-1? item)]
    (and (or meets-iva meets-retention) (not (and meets-iva meets-retention)))))

(defn filter-items [invoice]
  (->> invoice
       :invoice/items
       (filter meets-conditions?)))

(def filtered-items (filter-items invoice))
(println filtered-items)



