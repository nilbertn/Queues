Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?

For deque implementation I used two linked lists with two pointers one for the previous
node and one for the next node to allow to add items and remove items from both sides
of the list there is no cost of resizing. I also implimented an iteratot that
traverses the deque by following the next pointer.

For randomized deque I used an array data structure, I useed dynamic array resizing
when the array is full I double ht esize and when the array is quarter ful I hald
the size. Arrays have constant time, so I chose them as it allows for efficient
access when randomizing the access. Enqueue also adds items at the back of the array
which is constant time, a copy is made before shuffling to avoid editing original array
 **************************************************************************** */


/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  16  bytes

16 bytes ~ object overhead
24 bytes ~ array overhead
4 bytes ~ primitive int data type
8 bytes ~ reference to array

2n * 8 bytes ~ Variable overhead worst case

= 16n + 52 bytes
using tilde notation ~ 16n



Deque:              ~  40 bytes
16 bytes ~ object overhead
8 bytes ~ reference to Item (don't count memory)
8 bytes ~ reference to first pointer
8 bytes ~ reference to last pointer
4 bytes ~ primitive int datatype

16 bytes ~ object overhead
8 bytes ~ non-static nested class extra overhead (ignored)
8 bytes ~ reference to Item
8 bytes ~ reference to first pointer
8 bytes ~ reference to last pointer


= 40n + 36 bytes
using tilde notation ~ 40n





/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
There is an unchecked cast that cannot be avoided and deprecation when using
the Std.Random

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
I struggled to use less memory in Permutation


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
I did NOT enjoy coding this assignment!!
