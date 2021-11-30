package com.example.myapplication2.chishi;

import java.util.ArrayList;




public class GrtResult {

    public static int get3Num(int[] arr) {
        ArrayList<Integer> usedNum = new ArrayList<>();
//		RandomNums nums = new RandomNums();
//		int[] arr = nums.getNums();
        String[] allArr = new String[] { arr[0] + "", "+", arr[1] + "", "-", arr[2] + "", "-", arr[3] + "", "+",
                arr[4] + "" };

        int index = (int) (Math.random() * 5) * 2;
        usedNum.add(index);
        int index2 = 0;
        int index3 = 0;
        int[] canUsedNums = {};
        switch (index) {
            case 0:
                canUsedNums = new int[] { 1, 3 };

                break;
            case 2:
                canUsedNums = new int[] { 1, 5 };
                break;
            case 4:
                canUsedNums = new int[] { 1, 3, 5, 7 };
                break;
            case 6:
                canUsedNums = new int[] { 3, 7 };
                break;
            case 8:
                canUsedNums = new int[] { 5, 7 };
                break;

            default:
                break;
        }
        index2 = canUsedNums[getRandInex(canUsedNums.length)];
        usedNum.add(index2);

        int[] canUsedNums1 = {};
        switch (index2) {
            case 1:
                canUsedNums1 = new int[] { 0, 2, 4 };
                break;
            case 3:
                canUsedNums1 = new int[] { 0, 4, 6 };
                break;
            case 5:
                canUsedNums1 = new int[] { 2, 4, 8 };
                break;
            case 7:
                canUsedNums1 = new int[] { 4, 6, 8 };
                break;

            default:
                break;
        }
        int[] canUsedNums2 = canUsedNums1;
        for (int i = 0; i < canUsedNums2.length; i++) {
            for (int j = 0; j < usedNum.size(); j++) {
                if (canUsedNums2[i] == usedNum.get(j)) {
                    canUsedNums1 = remveArrayByIndex(canUsedNums1, i);
                }
            }
        }
        index3 = canUsedNums1[getRandInex(canUsedNums.length)];
        usedNum.add(index3);
        int result = 0;
        System.out.println("" + index + index2 + index3);
        System.out.println(allArr[index] + allArr[index2] + allArr[index3]);
        if (allArr[index2].equals("-")) {
            result = Math.abs(Integer.parseInt(allArr[index]) - Integer.parseInt(allArr[index3]));
        } else {
            result = Math.abs(Integer.parseInt(allArr[index]) + Integer.parseInt(allArr[index3]));
        }
        System.out.println(result);
        return result;
    }

    private static int[] remveArrayByIndex(int[] arr, int index) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            l.add(arr[i]);
        }
        int[] arr1 = new int[l.size()];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = l.get(i);
        }
        return arr1;
    }

//	public static void main(String[] args) {
//		get3Num(null);
//	}

    private void test1() {

        getRandInex(10);

    }
    private static int getRandInex(int n) {

        return (int) Math.random() * n;
    }
}