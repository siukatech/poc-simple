package com.siukatech.pocsimple.leetcode.twosum;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoSumTest01 {

    public static void main(String[] args) {
        TwoSumTest01 test = new TwoSumTest01();
        int[] twoSum;
        twoSum = test.twoSum(new int[] {3,2,4}, 6);
        twoSum = test.twoSum(new int[] {2,5,5,11}, 10);
        System.out.println("twoSum: [" + StringUtils.join(Arrays.stream(twoSum).boxed().collect(Collectors.toList()), ",") + "]");
    }

    public int[] twoSum(int[] nums, int target) {
        //List numList = Arrays.asList(nums);
        List numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int[] twoSum = null;
        System.out.println("twoSum - numList: [" + StringUtils.join(numList, ",") + "]");
        for (int i = 0; i < nums.length; i++) {
            Integer remaining = target - nums[i];
            Integer numIndex = numList.indexOf(remaining);
            Integer lastIndex = numList.lastIndexOf(remaining);
            Integer resultIndex = null;
            if (numIndex == i && lastIndex == i) {
                //continue;
            } else if (numIndex == i && lastIndex != i) {
                resultIndex = lastIndex;
            } else if (numIndex != i && lastIndex == i) {
                if (numIndex > i) {
                    resultIndex = numIndex;
                }
            } else {
                resultIndex = numIndex;
            }
            System.out.println("twoSum - i: [" + i + "], remaining: [" + remaining + "], numIndex: [" + numIndex
                    + "], lastIndex: [" + lastIndex + "], resultIndex: [" + resultIndex + "]");
            if (resultIndex == null || resultIndex == -1) {
                continue;
            } else {
                twoSum = new int[]{new Integer(i), resultIndex};
                break;
            }
        }
        twoSum = twoSum == null ? new int[0] : twoSum;
        return twoSum;
    }

}
