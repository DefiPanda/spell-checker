(ns core
  (use [clojure.java.io :only (reader)])
  (use [clojure.string :only (split join)]))

(def NORMALIZE_FACTOR 1000000)
(def NO_ERROR_REWARD 2000)

(defn add-or-replace [seed i replace]
  (reduce (fn [result x]
            (conj result (apply str (concat (take i seed) (str x) (drop (+ replace i) seed))))) [] (map char (range 97 123))))

(defn transpose [seed i replace]
  [(apply str (concat (take i seed) (take 1 (drop (inc i) seed)) (take 1 (drop i seed)) (drop (+ i 2) seed)))])

(defn delete [seed i replace] [(apply str (concat (take i seed) (drop (inc i) seed)))])

(defn append-prop [seed word dict edit-dict replace i]
  (let [w_i (take 1 (drop i word)) w_i-1 (take 1 (drop (dec i) word)) w_i+1 (take 1 (drop (inc i) word))
        s_i (take 1 (drop i seed)) s_i-1 (take 1 (drop (dec i) seed)) s_i+1 (take 1 (drop (inc i) seed))]
    (cond
      (= replace 0) [word (get edit-dict (apply str (concat s_i-1 "|" (concat (if (= i 0) (concat w_i w_i+1) (concat w_i-1 w_i))))))]
      (= replace 1) [word (get edit-dict (apply str (concat s_i "|" w_i)))]
      (= replace 2) [word (get edit-dict (apply str (concat s_i s_i+1 "|" w_i w_i+1)))]
      (= replace 3) [word (get edit-dict (apply str (if (= i 0) (concat s_i "| ") (concat s_i-1 s_i "|" w_i-1))))])))

; replace flag: 0 for add, 1 for replace, 2 for transpose, and 3 for deletion
(defn edit-one [func seed dict edit-dict replace indices]
  (reduce (fn [acc i] (concat acc (map (fn [x] (append-prop seed x dict edit-dict replace i))
                                     (filter (fn [x] (and (contains? dict x) (not= x seed))) (func seed i replace))))) [] indices))

(defn candidates [seed dict edit-dict]
  (distinct (concat [[seed NO_ERROR_REWARD]] (edit-one add-or-replace seed dict edit-dict 0 (range (inc (count seed))))
              (edit-one add-or-replace seed dict edit-dict 1 (range (inc (count seed))))
              (edit-one transpose seed dict edit-dict 2 (range (dec (count seed))))
              (edit-one delete seed dict edit-dict 3 (range (count seed))))))

(defn get-prop [words mispell-rate dict bigram]
  (* mispell-rate (let [hit (get dict (take 1 words))] (if (= hit nil) 1 hit))
     (reduce (fn [acc i]
            (let [current (take 1 (drop i words))
                  prev (take 1 (drop (dec i) words))]
              (* acc (/ (let [hit (get bigram (apply str (concat prev " " current)))]
                       (if (= hit nil) 1 hit)) NORMALIZE_FACTOR)))) 1 (drop 1 (range (count words))))))

(defn possible-sentence [words dict edit-dict]
  (reduce (fn [acc i]
            (concat acc (let [current (apply str (take 1 (drop i words)))
                  prev (take i words)
                  next (drop (inc i) words)]
              (reduce (fn [sentences candidate] (conj sentences [(concat prev [(str (candidate 0))] next) (candidate 1)]))
                  [] (candidates current dict edit-dict))))) [] (range (count words))))

(defn correct-sentence [sentence dict bigram edit-dict]
  (((let [words (filter #(not= % []) (split sentence #" "))]
    (reduce (fn [acc sentence] (let [prop (get-prop (sentence 0)
                                                    (let [edit-prop (sentence 1)] (if (= edit-prop nil) 0.5 edit-prop)) dict bigram)]
                                  (if (> prop (acc 1)) [sentence prop] acc))) ["" 0] (possible-sentence words dict edit-dict))) 0) 0))

(defn correct-paragraph [paragraph dict bigram edit-dict]
  (apply str (let [punctuations (re-seq #"[.?!](?!\d)" paragraph)
        sentences (map (fn [sentence] (correct-sentence sentence dict bigram edit-dict)) (split paragraph #"[.?!](?!\d)"))]
    (let [joined-sentences (map #(join " " %) sentences)] (map str joined-sentences punctuations)))))

(defn get-counts [filename]
  (reduce (fn [acc line] (assoc acc ((split line #"\t") 0) (Long/parseLong (last (split line #"\t"))))) {} (line-seq (reader filename))))