(ns invoice-item-test
  (:require [clojure.test :refer [deftest is]]
            [invoice-item :as ii]))

;The test doesn't work because I can't set the correct map vector structure, for this reason
;defn subtotal doesn't work because it doesn't find any value
(deftest test-subtotal-without-discount
  (let [item {:precise-quantity 5
              :precise-price 10
              :discount-rate 0}]
    (is (= 50 (ii/subtotal item)))))