spell-checker  
======  
Spell-checker is a spell-checker written in Clojure.  
  
Done  
---  
1. Spell-recommendation for any sentence with at most one word-spelling error. (The heuristic is most misspelled sentence is with only one word spelled wrong. Almost all spell-checker just consider one-word error case.)  
2. When parsing the paragraph, don't intepret the digit sign in a number as a period (e.g. "It costs $1.22." is one sentence, but not two.).  
  
Test Result  
---  
Input:  
"Doing a spell-checker is a great way two learn NLP. Doing a spell-checker is a graet way to learn NLP.  
Doing a spell-checker is a great way to learnt NLP. Doing a spell-checker is a gkeat way to learn NLP?  
Doing a spell-checker is a grat way to learn NLP! Doing a spell-checker is a great way to learn NLP.  
It won't spilt the digit symbol into two sentences, like 1.2212. What about plural, such as one men? For some pluralizations this correct can makes it.  
It can works! It work! Isn't that veri cool?"   
  
Output:  
"Doing a spell-checker is a great way to learn NLP. Doing a spell-checker is a great way to learn NLP.  
Doing a spell-checker is a great way to learn NLP. Doing a spell-checker is a great way to learn NLP?  
Doing a spell-checker is a great way to learn NLP! Doing a spell-checker is a great way to learn NLP.  
It won't split the digit symbol into two sentences, like 1.2212. What about plural, such as one man? For some pluralizations this correct can make it.  
It can work! It works! Isn't that very cool?"  
  
Analysis:  
Let us do a sentence by sentence analysis on our spell-checker's output when given the above input. The format of following analysis is:  
input sentence ==> output sentence by this spell-checker  
  
ALL PASSED:  
(semantically wrong: "two" should be "to")  
Doing a spell-checker is a great way two learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(transposition: "graet" should be "great")  
Doing a spell-checker is a graet way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(addition: "learnt" should be "learn")  
Doing a spell-checker is a great way to learnt NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(replacement: "gkeat" should be "great")  
Doing a spell-checker is a gkeat way to learn NLP? ==> Doing a spell-checker is a great way to learn NLP?   
(deletion: "grat" should be "great")  
Doing a spell-checker is a grat way to learn NLP! ==> Doing a spell-checker is a great way to learn NLP!    
(correct sentence: no change needed)  
Doing a spell-checker is a great way to learn NLP. ==> Doing a spell-checker is a great way to learn NLP.  
(transposition: "spilt" should be "split". Notice spell-checker will not recognize the digit sign in the number 1.2212 as a period, and that is the correct thing to do)  
It won't spilt the digit symbol into two sentences, like 1.2212. ==> It won't split the digit symbol into two sentences, like 1.2212.  
(semantically wrong [plural vs. singular]: "men" should be "man")  
What about plural, such as one men? ==> What about plural, such as one man?  
(semantically wrong [verb following can should be singular form]: "makes" should be "make")  
For some pluralizations this correct can makes it. ==> For some pluralizations this correct can make it.  
(semantically wrong [verb following can should be singular form]: "wroks" should be "work")  
It can works! => It can work!  
(semantically wrong [third-person singular]: "work" should be "works")  
It work! => It works!  
(replacement: "veri" should be "very")  
Isn't that veri cool? => Isn't that very cool?  