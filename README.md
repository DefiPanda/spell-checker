spell-checker  
======  
Spell-checker is a spell-checker written in Clojure.  
  
Done  
---  
1. Spell-recommendation for any sentence with at most one word-spelling error. (The heuristic is most misspelled sentence is with only one word spelled wrong. Almost all spell-checker just consider one-word error case.)  
  
To-do List  
---  
1. Allow user to pass in a whole paragraph instead of one sentence. This requires the program to recognize a sentence. It is hard to decide whether a period is in a middle of a sentence or at the end of it (e.g. "It costs $1.22." is one sentence, but not two.). Simply splitting a sentence by punctuations won't work well.  
  
Test Result  
---  
The format of following test result is:  
input sentence ==> output sentence by this spell-checker  
  
PASSED:  
(semantically wrong: "two" should be "to")  
Doing a spell-checker is a great way two learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(transposition: "graet" should be "great")  
Doing a spell-checker is a graet way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(deletion: "grat" should be "great")
Doing a spell-checker is a grat way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(addition: "learnt" should be "learn")  
Doing a spell-checker is a great way to learnt NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(replacement: "gkeat" should be "great")   
Doing a spell-checker is a gkeat way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(correct sentence: no change needed)  
Doing a spell-checker is a great way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  