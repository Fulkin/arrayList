# ArrayList

* Resizable-array implementation to store data of any type and sort. An array has methods for adding elements to any part of the list, removing elements, and getting elements from them.

## About

* Elements are stored in an array, when filled it expands.
* List implementation based on Java Generics.
* Supports sorting of elements that implement interface Comparable.
* Also sorts elements based on the Comparator interface.
* Methods for interaction:

1. `аdd(E element)`: appends the specified element to the end of this list;

2. `аdd(int index, E element)`: inserts the specified element at the specified position in this list;

3. `get(E element)`: getting an element from a list;

4. `delete(E element)`: removes the first occurrence of the specified element from this list, if it is present;

5. `delete(int index)`: removes the element at the specified position in this list;

6. `contains(E element)`: returns true if this list contains the specified element;
   
7. `size()`: returns the number of elements in this list;
   
8. `isEmpty()`: returns true if this list contains no elements;
   
9. `sort(Comparator<? super E> c)`: sorts the list based on the Comparator interface using quicksort, if `(c == null)` compare elements in natural order;

