spell-checker  
======  
Spell-checker is a spell-checker written in Clojure.  
  
Done  
---  
1. Spell-recommendation for any sentence with at most one word-spelling error. (The heuristic is most misspelled sentence is with only one word spelled wrong. Almost all spell-checker just consider one-word error case.)  
2. When parsing the paragraph, don't intepret the digit sign in a number as a period (e.g. "It costs $1.22." is one sentence, but not two.).  
  
To-do List  
---  
1. Add sentence-ending punctionations back to the result.  
  
Test Result  
---  
Input:  
"Doing a spell-checker is a great way two learn NLP. Doing a spell-checker is a graet way to learn NLP.
  Doing a spell-checker is a great way to learnt NLP. Doing a spell-checker is a gkeat way to learn NLP?
  Doing a spell-checker is a grat way to learn NLP! Doing a spell-checker is a great way to learn NLP."  
  
Output:  
(Doing a spell-checker is a great way to learn NLP  Doing a spell-checker is a great way to learn NLP 
  Doing a spell-checker is a great way to learn NLP  Doing a spell-checker is a great way to learn NLP 
  Doing a spell-checker is a great way to learn NLP  Doing a spell-checker is a great way to learn NLP) 
  
Analysis:  
  
The format of following test result is:  
input sentence ==> output sentence by this spell-checker  
  
ALL PASSED:  
(semantically wrong: "two" should be "to")  
Doing a spell-checker is a great way two learn NLP. ==> Doing a spell-checker is a great way to learn NLP  
(transposition: "graet" should be "great")  
Doing a spell-checker is a graet way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP  
(addition: "learnt" should be "learn")  
Doing a spell-checker is a great way to learnt NLP. ==> Doing a spell-checker is a great way to learn NLP  
(replacement: "gkeat" should be "great")  
Doing a spell-checker is a gkeat way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP   
(deletion: "grat" should be "great")  
Doing a spell-checker is a grat way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP  
(correct sentence: no change needed)  
Doing a spell-checker is a great way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP  