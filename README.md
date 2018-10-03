# Palindrome

Lab exercise for CIS 112<sup>[1](#myfootnote1)</sup>. The **`Palindrome`** class tests a date to determine if the numerical representation of the date is a palindrome, while fulfilling the requirements to make use of the *stack* and *queue* data structures. It started as a simple assignment, but sort-of got out-of-hand relative to the indended scope of the project. Nevertheless, there's several things I'm proud of - it was especially rewarding to implement a *deque* from a double-linked list and use it in action.

This version has the following attributes:
  1. Stores the user-entered date in a Java `Calendar` instance variable.  
  2. Makes use of the `Calendar` `IllegalArgumentException` to validate user input.  
  3. Encodes the date into a `DoubleLinkedDeque` (which I extended from the authors' `LinkedQueue`) for simple evaluation.  
  4. Generates multiple versions of the date to accomodate single-digit month and day values to be expressed with or without leading zeroes. Each version is encoded into its own deque and pushed onto a `LinkedStack`.
  5. A robust `toString()` method which returns the result of the palindrome evaluation, and if true, the particular version of the date that applies.  

The support structures are from the textbook authors, with the exception of the `DoubleLinkedDeque`, which I which I extended from the authors' `LinkedQueue`<sup>[2](#myfootnote2)</sup>

### Footnotes
<a name="myfootnote1">1</a>: Adapted from Dale/Joyce/Weems (4th ed.), p.291 #29:
  > Create a program that identifies all palindromic dates in a given year. First a user enters a year. Then the program reports the palindromic dates. Finally, the program asks the user if he or she wishes to try again. Note that you need a class similar to the Palindrome class, but one that permits testing “digit” characters.   

<a name="myfootnote2">2</a>: c.f. Dale/Joyce/Weems (4th ed.), p.254-255
