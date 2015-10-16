Memory usage: HashMap vs TreeMap
==

Do hash table use more memory than trees? 

Let us consider Java's HashMap vs. the TreeMap.

```
$ mvn -q test

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
memory usage of an empty string (deep): 40
memory usage of an empty string: 24

=============
Number of keys (strings) : 100000
We report the number bytes per key-value pair.
Hash map size = 152.9
hash table size = 150.3
tree map size = 150.4
concurrent hash map size = 152.9
linked hash map size = 160.9
skip list size = 146.3
fastutil (open addressing) size = 131.4
array size = 118.4
==============
memory usage of an Integer (deep): 16
memory usage of an Integer: 16

==============
Number of keys (integers) : 100000
We report the number bytes per key-value pair.
Hash map size = 74.5
hash table size = 72.0
tree map size = 72.0
concurrent hash map size = 74.5
linked hash map size = 82.5
skip list size = 67.9
fastutil OpenHashMap (open addressing, native ints) size = 21.0
fastutil ArrayMap (native ints) size = 8.0
fastutil RBTreeMap (native ints) size = 32.0
fastutil AVLTreeMap (native ints) size = 32.0
arrayObjWrapper size = 40.0
arrayPrimitiveWrapper size = 8.0
==============
For this test, we see that each Integer object takes 20 bytes, 5x what is necessary.
```

These results suggest that a TreeMap is no more concise than a HashMap.
In fact, all Java data structures seem to use nearly an optimal amount
of memory. The biggest source of waste is the fact that integers
are wrapped in an Integer object, using 5 times as much memory as they should.
The String objects also use a lot of memory compared to their content: they 
could be "packed" much more tightly.

The most "wasteful" data structure appears to be the linked hash map. The most
concise one is the simple array (which can be considered nearly optimal space wise).

Warning: This test may or may not be reliable. Please review my source
code for yourself.

