package me.lemire.memory;

import org.github.jamm.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import it.unimi.dsi.fastutil.objects.*;
import it.unimi.dsi.fastutil.ints.*;

import java.util.*;
import java.util.concurrent.*;
import java.text.DecimalFormat;


public class MemoryBenchmarkTest {


    @Test
    public void benchmark() throws Exception {
        MemoryMeter meter = new MemoryMeter().ignoreKnownSingletons();
        System.out.println("memory usage of an Integer (deep): "+meter.measureDeep(new Integer(0)));
        System.out.println("memory usage of an Integer: "+meter.measure(new Integer(0)));

        DecimalFormat df = new DecimalFormat("0.0");
        for(int s = 10000; s<=10000; s*=10) {
            HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>(10000);
            TreeMap<Integer,Integer> tree = new TreeMap<Integer,Integer>();
            Hashtable<Integer,Integer> lhash = new Hashtable<Integer,Integer>(10000);
            ConcurrentHashMap<Integer,Integer> chash = new ConcurrentHashMap<Integer,Integer>(10000);
            LinkedHashMap<Integer,Integer> lihash = new LinkedHashMap<Integer,Integer>(10000);
            ConcurrentSkipListMap<Integer,Integer> skip = new ConcurrentSkipListMap<Integer,Integer>();
            Int2IntMap fastutilOhm = new Int2IntOpenHashMap(10000);
            Int2IntMap fastutilAm = new Int2IntArrayMap(10000);
            Int2IntMap fastutilRb = new Int2IntRBTreeMap();
            Int2IntMap fastutilAVL = new Int2IntAVLTreeMap();
            Integer[] arrObj = new Integer[2*s];
            int[] arrPrimitive = new int[2*s];

            for(int k = 0; k < s; ++k) {
                hash.put(k,k);
                lhash.put(k,k);
                tree.put(k,k);
                chash.put(k,k);
                lihash.put(k,k);
                skip.put(k,k);
                fastutilOhm.put(k,k);
                fastutilAm.put(k,k);
                fastutilRb.put(k,k);
                fastutilAVL.put(k,k);
                arrObj[2*k] = new Integer(k);
                arrObj[2*k+1] = new Integer(k);
                arrPrimitive[2*k] = k;
                arrPrimitive[2*k+1] = k;
            }
            System.out.println();
            String hashsize = df.format(meter.measureDeep(hash)*1.0/s);
            String lhashsize = df.format(meter.measureDeep(lhash)*1.0/s);
            String treesize = df.format(meter.measureDeep(tree)*1.0/s);
            String arrObjsize = df.format(meter.measureDeep(arrObj)*1.0/s);
            String arrPrimitiveSize = df.format(meter.measureDeep(arrPrimitive)*1.0/s);
            String csize = df.format(meter.measureDeep(chash)*1.0/s);
            String lisize = df.format(meter.measureDeep(lihash)*1.0/s);
            String skipsize = df.format(meter.measureDeep(skip)*1.0/s);
            String fastutilsizeOhm = df.format(meter.measureDeep(fastutilOhm)*1.0/s);
            String fastutilsizeAm = df.format(meter.measureDeep(fastutilAm)*1.0/s);
            String fastutilsizeRb = df.format(meter.measureDeep(fastutilRb)*1.0/s);
            String fastutilsizeAVL = df.format(meter.measureDeep(fastutilAVL)*1.0/s);

            System.out.println("==============");
            System.out.println("Number of keys (integers) : "+s);
            System.out.println("We report the number bytes per key-value pair.");
            System.out.println( "Hash map size = " +hashsize
                                + "\nhash table size = " + lhashsize
                                + "\ntree map size = " + treesize
                                + "\nconcurrent hash map size = " + csize
                                + "\nlinked hash map size = " + lisize
                                + "\nskip list size = " + skipsize
                                + "\nfastutil OpenHashMap (open addressing, native ints) size = " + fastutilsizeOhm
                                + "\nfastutil ArrayMap (native ints) size = " + fastutilsizeAm
                                + "\nfastutil ArrayMap (native ints) size = " + fastutilsizeAm
                                + "\nfastutil RBTreeMap (native ints) size = " + fastutilsizeRb
                                + "\nfastutil AVLTreeMap (native ints) size = " + fastutilsizeAVL
                                + "\narrayObjWrapper size = " + arrObjsize
                                + "\narrayPrimitiveWrapper size = " + arrPrimitiveSize
                                );
            System.out.println("==============");

        }
        System.out.println("For this test, we see that each Integer object takes 20 bytes, 5x what is necessary.");

    }

    @Test
    public void benchmarkString() throws Exception {
        MemoryMeter meter = new MemoryMeter().ignoreKnownSingletons();
        System.out.println("memory usage of an empty string (deep): "+meter.measureDeep(new String()));
        System.out.println("memory usage of an empty string: "+meter.measure(new String()));
        DecimalFormat df = new DecimalFormat("0.0");
        for(int s = 10000; s<=10000; s*=10) {
            HashMap<String,String> hash = new HashMap<String,String>();
            TreeMap<String,String> tree = new TreeMap<String,String>();
            Hashtable<String,String> lhash = new Hashtable<String,String>();
            ConcurrentHashMap<String,String> chash = new ConcurrentHashMap<String,String>();
            LinkedHashMap<String,String> lihash = new LinkedHashMap<String,String>();
            ConcurrentSkipListMap<String,String> skip = new ConcurrentSkipListMap<String,String>();
            Object2ObjectMap<String,String> fastutil = new Object2ObjectOpenHashMap<String,String>();
            String[] arr = new String[2*s];

            for(int k = 0; k < s; ++k) {
              hash.put(""+k,""+k);
              lhash.put(""+k,""+k);
              tree.put(""+k,""+k);
              chash.put(""+k,""+k);
              lihash.put(""+k,""+k);
              skip.put(""+k,""+k);
              fastutil.put(""+k,""+k);
              arr[2*k] = ""+k;
              arr[2*k+1] = ""+k;
            }
            System.out.println();
            String hashsize = df.format(meter.measureDeep(hash)*1.0/s);
            String lhashsize = df.format(meter.measureDeep(lhash)*1.0/s);
            String treesize = df.format(meter.measureDeep(tree)*1.0/s);
            String arrsize = df.format(meter.measureDeep(arr)*1.0/s);
            String csize = df.format(meter.measureDeep(chash)*1.0/s);
            String lisize = df.format(meter.measureDeep(lihash)*1.0/s);
            String skipsize = df.format(meter.measureDeep(skip)*1.0/s);
            String fastutilsize = df.format(meter.measureDeep(fastutil)*1.0/s);

            System.out.println("==============");
            System.out.println("Number of keys (strings) : "+s);
            System.out.println("We report the number bytes per key-value pair.");

            System.out.println( "Hash map size = " +hashsize
                                + "\nhash table size = " + lhashsize
                                + "\ntree map size = " + treesize
                                + "\nconcurrent hash map size = " + csize
                                + "\nlinked hash map size = " + lisize
                                + "\nskip list size = " + skipsize
                                + "\nfastutil (open addressing) size = " + fastutilsize
                                + "\narray size = " + arrsize);
            System.out.println("==============");
            //meter = meter.enableDebug();
        }
    }

}
