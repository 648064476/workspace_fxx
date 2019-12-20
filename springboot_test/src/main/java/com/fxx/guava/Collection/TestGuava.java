package com.fxx.guava.Collection;


import com.google.common.collect.*;
import com.google.common.reflect.Invokable;
import sun.security.provider.certpath.Vertex;

import java.util.Map;
import java.util.Optional;

/**
 * 测试guava
 *
 * @author gantingting
 */
public class TestGuava {


    public static void multiset () {

        HashMultiset<Object> objects = HashMultiset.create();

        objects.add(1);
        objects.add(2);
        objects.add(1);
        objects.add(1);
        objects.add(2, 4);
        System.out.println(objects.count(1));
        System.out.println(objects.count(2));
        System.out.println(objects.elementSet());

    }

    public static void multimap () {
        ArrayListMultimap<Object, Object> map = ArrayListMultimap.create();
        map.put(1, 2);
        map.put(1, 3);
        System.out.println(map.get(1));

        HashMultimap<Object, Object> map1 = HashMultimap.create();
        map1.put(1, "a");
        map1.put(1, "b");
        System.out.println(map1.get(1));

    }


    public static void rangeSet () {
        RangeSet<String> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed("a", "e"));
        rangeSet.add(Range.closedOpen("f", "z"));
        rangeSet.add(Range.openClosed("1", "3"));
//        rangeSet.add(Range.openClosed(0, 0));
//        rangeSet.remove(Range.open(5, 10));

        System.out.println(rangeSet.asRanges());
        System.out.println(rangeSet.contains("b"));
        System.out.println(rangeSet.rangeContaining("1"));
        System.out.println(rangeSet.complement());

    }

    public static void classToInstanceMap () {
        ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
        numberDefaults.putInstance(Integer.class, Integer.valueOf(1));

        System.out.println(numberDefaults.getInstance(Integer.class));

    }

    public static void table () {
        Table<String, String, Double> weightedGraph = HashBasedTable.create();
        weightedGraph.put("a", "b", 4.0);
        weightedGraph.put("b", "c", 20.0);
        weightedGraph.put("c", "d", 5.0);

        System.out.println(weightedGraph.rowKeySet());
        System.out.println(weightedGraph.row("b"));
        System.out.println(weightedGraph.get("a", "b"));

    }

    public static void biMap () {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("a", 1);
        System.out.println(biMap.get("a"));
        System.out.println(biMap.inverse().get(1));

    }


//    public static void main (String[] args) {
//        multiset();
//        multimap();
//        biMap();
//        table();
//        classToInstanceMap();
//        rangeSet();
//        String aa = "aa";
//        System.out.println(Optional.ofNullable(aa).orElse("bb"));
//        System.out.println(Range.open(3, 5).isConnected(Range.closedOpen(5,10)));
//    }

}
