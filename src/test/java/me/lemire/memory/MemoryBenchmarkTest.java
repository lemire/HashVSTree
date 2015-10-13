package me.lemire.memory;

import net.sourceforge.sizeof.SizeOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;


public class MemoryBenchmarkTest {


    @Test
    public void benchmark() throws Exception {
        SizeOf.setMinSizeToLog(0);
        for(int s = 10; s<=1000000; s*=10) {
            HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
            TreeMap<Integer,Integer> tree = new TreeMap<Integer,Integer>();
            Hashtable<Integer,Integer> lhash = new Hashtable<Integer,Integer>();
            int[] arr = new int[s];

            for(int k = 0; k < s; ++k) {
                hash.put(k,k);
                lhash.put(k,k);
                tree.put(k,k);
                arr[k] = k;
            }
            System.out.println();
            System.out.println("Number of entries: "+s);

            System.out.println("Computing hash map size...");
            String hashsize = SizeOf.humanReadable(SizeOf.deepSizeOf(hash));

            System.out.println("Computing hash table size...");
           String lhashsize = SizeOf.humanReadable(SizeOf.deepSizeOf(lhash));

            System.out.println("Computing tree map size...");
            String treesize = SizeOf.humanReadable(SizeOf.deepSizeOf(tree));

            System.out.println("Computing array size...");
            String arrsize = SizeOf.humanReadable(SizeOf.deepSizeOf(arr));

            System.out.println("==============");
            System.out.println( " Hash map size = " +hashsize
                                + " / hash table size = " + lhashsize
                                + " / tree map size = " + treesize
                                + " / array size = " + arrsize);
            System.out.println("==============");
        }
    }


}
