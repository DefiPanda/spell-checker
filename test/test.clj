(ns spell-checker.test.test
  (use [core :only (get-counts correct-paragraph)]))

; test sample paragraph
(def test-paragraph "Doing a spell-checker is a great way two learn NLP. Doing a spell-checker is a graet way to learn NLP.
Doing a spell-checker is a great way to learnt NLP. Doing a spell-checker is a gkeat way to learn NLP?
Doing a spell-checker is a grat way to learn NLP! Doing a spell-checker is a great way to learn NLP.
It won't spilt the digit symbol into two sentences, like 1.2212. What about plural, such as one men? For some pluralizations this correct can makes it.
It can works! It work! Isn't that veri cool?")

(let [dict (get-counts "../data/count_1w.txt")
      bigram (get-counts "../data/count_2w.txt")
      edit-dict (get-counts "../data/count_1edit.txt")]
  (println (correct-paragraph test-paragraph dict bigram edit-dict)))