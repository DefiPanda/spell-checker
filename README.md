spell-checker  
======  
Spell-checker is a spell-checker written in Clojure. It does spell-recommendation for any sentence with at most one word-spelling, within one Damerau–Levenshtein distance, error. (The heuristic is most misspelled sentence is with only one word spelled wrong. Almost all spell-checker just consider one-word error case.) Spell-checker can correct both syntax and semanatically wrong sentences, and it can also corrects some grammar mistakes, such as the singular vs. plural error.  
  
Licence  
---  
The MIT License (MIT)  
  
Copyright (c) 2014 Zhe Wang  
  
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:  
  
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.  
  
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.  
  
Support this project  
---  
Help us make this project viable through tipping! [Link to my gittip profile.](https://www.gittip.com/jw2013/)  
  
Usage  
---  
You can use your own dictionary, ngrams and edit-cost files to tailor your customized need. One example is in a highly-specialized field, such as computational biology, the traditional spell-checkers don't work well, but providing your customized data on this spell-checker will give a better spell-checking performance. If you don't want to provide your own spelling data, the default spelling-data is located at /data folder.  
  
The file at /test/test.clj provides a sample use of this spell checker.  
  
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
  
(plural vs. singular: "men" should be "man")  
What about plural, such as one men? ==> What about plural, such as one man?  
  
(verb following word "can" should be singular form: "makes" should be "make")  
For some pluralizations this correct can makes it. ==> For some pluralizations this correct can make it.  
  
(verb following word "can" should be singular form: "works" should be "work")  
It can works! => It can work!  
  
(third-person singular: "work" should be "works")  
It work! => It works!  
  
(replacement: "veri" should be "very")  
Isn't that veri cool? => Isn't that very cool?  
