(ns [myclojureapp.core :refer [vec-postoji]])

(def employees [{:id 1 :name "Dusan" :surname "Tavic" :rate 10 :job {:id 1 :name ".NET Junior Developer" :expected 10}}
                {:id 2 :name "Mila" :surname "Miladinovic" :rate 9.5 :job {:id 2 :name "Java Junior Developer" :expected 10}}
                {:id 3 :name "Nevena" :surname "Cvijovic" :rate 10 :job {:id 2 :name "Java Junior Developer" :expected 10}}
                {:id 4 :name "Veljko" :surname "Uskokovic" :rate 9 :job {:id 1 :name ".NET Junior Developer" :expected 10}}
                {:id 5 :name "Nemanja" :surname "Vukelic" :rate 8 :job {:id 1 :name ".NET Junior Developer" :expected 10}}
                {:id 6 :name "Marija" :surname "Grulovic" :rate 7 :job {:id 1 :name ".NET Junior Developer" :expected 10}}
                {:id 7 :name "Bogdan" :surname "Ivanovic" :rate 5 :job {:id 1 :name ".NET Junior Developer" :expected 10}}])

(def jobs [{:id 1 :name ".NET Junior Developer"},
           {:id 2 :name "Java Junior Developer"}])

(defn get-employees-rate
  [employee]
  (get employee :rate))

(defn get-employees-job
  [employee]
  (get employee :job))

(defn get-job-standard
  [job]
  (get job :expected))

(defn get-employees-name
  [employee]
  (str (get employee :name) " " (get employee :surname)))

(defn calculate
  [rate, job]
  (- (get job :expected) rate))

(defn get-training-advice
  [gap]
  (if (> gap 3)
    "SERIOUS NEED FOR TRAINING"
    (if (> gap 1)
      "TRAINING IS ADVISED"
      "NO NEED FOR TRAINING")))

(get (get employees 0) :rate)
;(calculate (get-employees-rate (get employees 0)) (get-employees-job (get employees 0)))
;(calculate (get-employees-rate (get employees 0)) (get-employees-job (get employees 0)))
;(calculate (get-employees-rate (get employees 0)) (get-employees-job (get employees 0)))
;(calculate (get-employees-rate (get employees 0)) (get-employees-job (get employees 0)))


(calculate (get-employees-rate (get employees 0)) (get-employees-job (get employees 0)))

(defn training-need

  ([employees]
   (loop
    [iteration 0]
     (let [emp (get employees iteration)]
       (println (str (get-employees-name emp) "| training gap: " (calculate (get-employees-rate emp) (get-employees-job emp)) "| "
                     (get-training-advice (calculate (get-employees-rate emp) (get-employees-job emp))))))
     (if (< iteration (- (count employees) 1))
       (recur (inc iteration)))))

  ([employees jobname]
   (loop
    [iteration 0]
     (if (= (get (get-employees-job (get employees iteration)) :name) jobname)
       (let [emp (get employees iteration)]
         (println (str (get-employees-name emp) "| training gap: " (calculate (get-employees-rate emp) (get-employees-job emp)) "| "
                       (get-training-advice (calculate (get-employees-rate emp) (get-employees-job emp)))))))

     (if (< iteration (- (count employees) 1))
       (recur (inc iteration))))))



(training-need employees)
(training-need employees "Java Junior Developer")

(defn calculate-transfer
  [employee]
  (- (get employee :rate-after) (get employee :rate-before)))

(require '[clojure.data.json :as json])

(def employees (json/read-str (slurp "mydata.json")
                                :key-fn keyword))

employees

(defn login 
  [username, password]
  (require '[clojure.data.json :as json])
  (def signal false)
  (let [users, (json/read-str (slurp "users.json")
                            :key-fn keyword)]
       (loop [iteration 0]
         (if (< iteration (count users))
           (do
             (if (and (= (:username (get users iteration)) username)
                      (= (:password (get users iteration)) password))
               (def signal true)
               )
             (recur (inc iteration)))
           signal))
       ))

(login "dusan" "dusan")