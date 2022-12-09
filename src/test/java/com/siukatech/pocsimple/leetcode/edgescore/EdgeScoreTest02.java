package com.siukatech.pocsimple.leetcode.edgescore;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EdgeScoreTest02 {

    public static void main(String[] args) {
        EdgeScoreTest02 test = new EdgeScoreTest02();
        int edgeScore = -1;
        //edgeScore = test.edgeScore(new int[]{1, 0, 0, 0, 0, 7, 7, 5}); // 7
        //edgeScore = test.edgeScore(new int[]{2,0,0,2}); // 0
        //edgeScore = test.edgeScore(new int[]{3,3,3,0}); // 0
        System.out.println("edgeScore: [" + edgeScore + "]");
    }

    public int edgeScore(int[] edges) {
        int edgeResult = -1;
        Map<Integer, Integer> edgesMap = edgesMap = IntStream.range(0, edges.length)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> edges[i], (a, b) -> b));
        IntStream edgesIntStream = IntStream.range(0, edges.length);
        Map<Integer, Integer> sumMap = new HashMap<>();
        System.out.println("edgeScore - edgesMap.keySet: [" + StringUtils.join(edgesMap.keySet(), ",")
                + "], edgesMap.values :[" + StringUtils.join(edgesMap.values(), ",")
                + "]");
        for (int i = 0; i < edges.length; i++) {
            final int x = i;
            Integer sum = edgesMap.entrySet().stream()
                    .filter(ele -> ele.getValue() == x)
                    .mapToInt(ele -> ele.getKey())
                    .sum()
                    ;
            //Integer sum = sumMapEntryOptional.isPresent()?sumMapEntryOptional.get().getValue():null;
            System.out.println("edgeScore - i: [" + i + "], sum: [" + sum + "]");
            if (sum != null && !sumMap.containsKey(sum)) {
                sumMap.put(sum, new Integer(i));
            }
        }
        Integer edgeResultKey = sumMap.keySet().stream().mapToInt(v -> v).max().orElse(-1);
        edgeResult = sumMap.get(edgeResultKey);
        System.out.println("edgeScore - edgeResultKey: [" + edgeResultKey
                + "], edgeResult: [" + edgeResult
                + "]");
        return edgeResult;
    }

}
