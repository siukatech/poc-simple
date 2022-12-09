package com.siukatech.pocsimple.leetcode.edgescore;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EdgeScoreTest01 {

    public static void main(String[] args) {
        EdgeScoreTest01 test = new EdgeScoreTest01();
        int edgeScore = -1;
        //edgeScore = test.edgeScore(new int[]{1, 0, 0, 0, 0, 7, 7, 5}); // 7
        //edgeScore = test.edgeScore(new int[]{2,0,0,2}); // 0
        //edgeScore = test.edgeScore(new int[]{3,3,3,0}); // 0
        System.out.println("edgeScore: [" + edgeScore + "]");
    }

    public int edgeScore(int[] edges) {
        int edgeResult = -1;
        int edgeScore = -1;

//		Map edgesMap = Arrays.stream(edges).boxed().collect(Collectors.toMap(
//				(k -> {
//					System.out.println("edgeScore - k: [" + k + "]");
//					return k;
//				})
//				, (v -> {
//					System.out.println("edgeScore - v: [" + v + "]");
//					return v;
//				})
//		));
        Map<Integer, Integer> edgesMap = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> valueMap = new HashMap<Integer, List<Integer>>();
        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> sumValueMap = new HashMap<Integer, List<Integer>>();
        edgesMap = IntStream.range(0, edges.length)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> edges[i], (a, b) -> b));
        System.out.println("edgeScore - edgesMap.keySet: [" + StringUtils.join(edgesMap.keySet(), ",")
                + "], edgesMap.values :[" + StringUtils.join(edgesMap.values(), ",")
                + "]");
        IntStream edgesIntStream = IntStream.range(0, edges.length);
        for (int i = 0; i < edges.length; i++) {
            final int x = i;
            Optional<Map.Entry<Integer, Integer>> sumMapEntryOptional = edgesMap.entrySet().stream()
                    .filter(ele -> ele.getValue() == x)
                    .reduce((eleA, eleB) -> {
                        return new AbstractMap.SimpleEntry<Integer, Integer>(0, eleA.getKey() + eleB.getKey());
                    })
                    ;
            Integer sum = sumMapEntryOptional.isPresent()?sumMapEntryOptional.get().getValue():null;
            System.out.println("edgeScore - i: [" + i + "], sum: [" + sum + "]");
            if (sum != null && !sumMap.containsKey(sum)) {
                sumMap.put(sum, new Integer(i));
            }
        }
//		for (int i = 0; i < edges.length; i++) {
//			List<Integer> edgesIndexList = null;
//			Integer edgesIndex = new Integer(i);
//			Integer edgesValue = edges[edgesIndex];
//			edgesIndexList = valueMap.get(edgesValue);
//			edgesIndexList = edgesIndexList == null ? new ArrayList<>() : edgesIndexList;
//			edgesIndexList.add(i);
//			valueMap.put(edgesValue, edgesIndexList);
//		}
//		for (int i = 0; i < edges.length; i++) {
//            List<Integer> edgesIndexList = null;
//			Integer edgesIndex = new Integer(i);
//			Integer edgesValue = edges[edgesIndex];
//            edgesIndexList = valueMap.get(edgesValue);
//            edgesIndexList = edgesIndexList == null ? new ArrayList<>() : edgesIndexList;
////			Integer preSum = edgesIndexList.stream().reduce(0, (a, b) -> a + b);
////			edgesIndexList.add(i);
////			valueMap.put(edgesValue, edgesIndexList);
//
//			Integer edgesSum = edgesIndexList.stream().reduce(0, (a, b) -> a + b);
//			if (sumMap.get(edgesValue) == null) {
//				sumMap.put(edgesValue, edgesSum);
//			}
//			List<Integer> edgesValueList = null;
//			edgesValueList = sumValueMap.get(edgesSum);
//			//edgesValueList = sumValueMap.get(preSum);
//			edgesValueList = edgesValueList==null?new ArrayList<Integer>():edgesValueList;
//			if (!edgesValueList.contains(edgesValue) ) {
//				edgesValueList.add(edgesValue);
//			}
//			//sumValueMap.put(preSum, new ArrayList<>());
//			sumValueMap.put(edgesSum, edgesValueList);
//			System.out.println("edgeScore - edgesIndex: [" + edgesIndex
//					+ "], edgesValue: [" + edgesValue
//					+ "], edgesSum: [" + edgesSum
//					+ "], edgeScore: [" + edgeScore
//					//+ "], preSum: [" + preSum
//					+ "]");
//			if (edgesSum > edgeScore) {
//				edgeScore = edgesSum;
//				edgeResult = edgesValue;
//			}
//			System.out.println("edgeScore - edgesIndex: [" + edgesIndex
//					+ "], edgesValue: [" + edgesValue
//					+ "], edgesIndexList :[" + (edgesIndexList==null?"NULL":StringUtils.join(edgesIndexList, ","))
//					+ "], edgesSum: [" + edgesSum
//					+ "], edgeScore: [" + edgeScore
//					+ "], edgeResult: [" + edgeResult
//					+ "], edgesValueList 1: [" + edgesValueList.stream().mapToInt(v -> v).min().orElse(-1)
//					+ "], edgesValueList 2: [" + StringUtils.join(edgesValueList, ",")
//					+ "]");
//
//		}
        System.out.println("edgeScore - sumMap.keySet: [" + StringUtils.join(sumMap.keySet(), ",")
                + "], sumMap.values :[" + StringUtils.join(sumMap.values(), ",")
                + "]");
//		System.out.println("edgeScore - sumValueMap.keySet: [" + StringUtils.join(sumValueMap.keySet(), ",")
//				+ "], sumValueMap.values :[" + StringUtils.join(sumValueMap.values(), ",")
//				+ "]");
//		edgeResult = sumMap.keySet().stream().mapToInt(v -> v).max().orElse(-1);
//		System.out.println("edgeScore - edgeResult: [" + edgeResult
//				+ "], sumMap.get(edgeResult): [" + sumMap.get(edgeResult)
//				+ "], sumValueMap.get(sumMap.get(edgeResult)) :[" + StringUtils.join(sumValueMap.get(sumMap.get(edgeResult)), ",")
//				+ "]");
//		edgeResult = sumValueMap.get(sumMap.get(edgeResult)).stream().mapToInt(v -> v).min().orElse(-1);
        Integer edgeResultKey = sumMap.keySet().stream().mapToInt(v -> v).max().orElse(-1);
        edgeResult = sumMap.get(edgeResultKey);
        return edgeResult;
    }

}
