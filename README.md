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

==============
Number of keys (strings) : 10000
We report the number bytes per key-value pair.
Hash map size = 134.6
hash table size = 137.8
tree map size = 136.0
concurrent hash map size = 134.6
linked hash map size = 142.6
skip list size = 132.0
fastutil (open addressing) size = 109.1
array size = 104.0
==============
memory usage of an Integer (deep): 16
memory usage of an Integer: 16

==============
Number of keys (integers) : 10000
We report the number bytes per key-value pair.
Hash map size = 70.4
hash table size = 71.8
tree map size = 71.8
concurrent hash map size = 70.4
linked hash map size = 78.4
skip list size = 68.2
fastutil OpenHashMap (open addressing, native ints) size = 13.1
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

