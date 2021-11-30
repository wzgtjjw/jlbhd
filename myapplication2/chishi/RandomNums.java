package com.example.myapplication2.chishi;


import java.util.HashSet;
import java.util.Set;


import java.util.Random;




public class RandomNums {
    private  Set<Integer> nums;
    int [] arr1 ;
    public RandomNums(){
        nums = new HashSet<>();
        while (nums.size()<5){
            int num =(int)(Math.random()*9)+1;
            nums.add(num);
        }
        Object[] arr =  nums.toArray();
        arr1 = new int[arr.length];
        for(int j = 0;j<arr.length;j++) {
            arr1[j] =(Integer) arr[j];
        }
        randomSort(arr1);

    }


    public int[] getNums(){
        return arr1;
    }



    public  void randomSort(int[] arr){
        int[] temp = arr.clone();
        int size = arr.length;
        for(int i = 0; i < size; i ++){
            if(arr[i] == temp[i]){//与原数组元素相等，说明未改变位置
                change2(temp, arr, i);
            }
        }
    }

    private  void change(int[] original, int[] arr, int target){
        int size = arr.length;
        int[] indexes = getIndexesWithout( size, target);
        int middle = arr[target];
        Random random = new Random();
        //尝试交换的元素下标
        int index = indexes[random.nextInt(size -1)];
        // System.out.println("index----------> " + index);
        if(arr[index] == original[index]){//与原数组元素相等，未变位置，可以交换
            arr[target] = arr[index];
            arr[index] = middle;
        }else {//若选中的元素是已经更换位置的，则继续尝试与数组中的其它元素交换位置
            for(int i = 0; i < size && i != index && i != target; i ++){
                if(arr[i] == original[i]){
                    arr[target] = arr[i];
                    arr[i] = middle;
                }
            }
        }
    }

    /**
     * 使用递归完成
     * @param original
     * @param arr
     * @param target
     */
    private  void  change2(int[] original, int[] arr, int target){
        int size = arr.length;
        int[] indexes = getIndexesWithout( size, target);
        Random random = new Random();
        int index = indexes[random.nextInt(size -1)];
        if(arr[index] == original[index]){//与原数组元素相等，未变位置，可以交换
            int middle = arr[target];
            arr[target] = arr[index];
            arr[index] = middle;
        }else {//若选中的元素是已经更换位置的，则继续尝试与数组中的其它元素交换位置
            change(original, arr, target);
        }
    }

    //获取目标下标之外的下标
    private static int[] getIndexesWithout(int size, int targetIndex){
        int[] ta = new int[size - 1];
        for(int i = 0; i < size; i ++){
            if(i != targetIndex){
                if(i < targetIndex){
                    ta[i] = i;
                }else{
                    ta[i-1] = i;
                }
            }
        }
        return ta;
    }


}

