Memory usage: HashMap vs TreeMap
==

Do hash table use more memory than trees? 



Let us consider Java's HashMap vs. the TreeMap.

```
mvn test -Dtest=MemoryBenchmarkTest

Number of entries: 100
==============
 Hash size = 6.5546875Kb / tree size = 6.3359375Kb
==============

Number of entries: 1000
==============
 Hash size = 69.3671875Kb / tree size = 69.1796875Kb
==============

Number of entries: 10000
==============
 Hash size = 687.8671875Kb / tree size = 701.9921875Kb
==============

Number of entries: 100000
==============
 Hash size = 7.102409362792969Mb / tree size = 6.865348815917969Mb
==============

Number of entries: 1000000
==============
 Hash size = 69.03404998779297Mb / tree size = 68.66344451904297Mb
==============
```

These results suggest that a TreeMap is no more concise than a HashMap.

Warning: This test may or may not be reliable. Please review my source
code for yourself.

