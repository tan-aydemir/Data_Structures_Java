# Data Structure Implementations in Java

This repository contains two Java code sets that implement essential data structures for efficient data storage and retrieval: a Chained Hash Table and a Binary Search Tree (Linked Tree).

## Chained Hash Table
The Chained Hash Table is a dynamic data structure that allows for the storage and retrieval of key-value pairs. It uses a hash function to map keys to indices in an array and resolves collisions using separate chaining, where multiple values with the same hash are stored in linked lists. The ChainedHashTable class provides methods for insertion, search, deletion, and resizing the hash table.

### Usage
To use the Chained Hash Table, create an instance of the ChainedHashTable class, specify the desired size, and start inserting, searching, and removing key-value pairs. You can also obtain statistics like load factor and retrieve all keys.

## Linked Tree
The Linked Tree is a binary search tree that maintains its elements in a sorted order. Each node in the tree contains a key and a list of data items associated with that key. The LinkedTree class provides methods for inserting, deleting, and searching for keys in the tree. Additionally, it offers different traversal methods for printing the keys, such as preorder, postorder, and level-order.

### Usage
Create a LinkedTree instance and insert keys with associated data. You can then perform searches and deletions on the keys and use various iterators for traversing the tree in different orders.

---

These data structures can be beneficial for various applications where efficient storage, retrieval, and manipulation of data are required.

Feel free to explore the code and adapt it to your specific needs. Each data structure is contained within its respective Java class, making it easy to integrate into your projects.
