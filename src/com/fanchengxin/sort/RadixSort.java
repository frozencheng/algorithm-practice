package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 基数排序算法
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = {10, 0, 1, 6, 66, 77, 999, 9995, 322, 748};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");
        int[] arr2 = {2, -99, 0, 1, 99, 444, 323, 789, -22, -1000};
        sort2(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println("=======================");
        int[] array = new int[80000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        long before = System.currentTimeMillis();
        sort(array);
        long after = System.currentTimeMillis();
        System.out.println("排序花费时间" + (after - before) + "毫秒");
    }

    /**
     * 基数排序的方法(不能对含有负数的数组进行排序)
     * 1 创建一个二维数组 10个桶 按照规则将数据存放到 对应的桶
     * 2 创建一个辅助数组 针对每次循环存放到对应的桶的数据进行计数
     * 3 确定需要排序数组的最大值 根据最大值的位数来确定需要基数排序的次数
     */
    public static void sort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCount = new int[bucket.length];
        int temp = arr[0]; // 临时变量 假设索引0的值位最大值
        int indexHelper;
        // 遍历 比较 来获取最大值
        for (int i = 1; i < arr.length; i++) {
            if (temp < arr[i]) {
                temp = arr[i];
            }
        }
        String str = temp + "";
        int element;
        for (int k = 0, n = 1; k < str.length(); k++, n *= 10) {
            for (int value : arr) {
                element = value / n % 10; // 当前第K次循环 arr中元素的基数
                bucket[element][bucketEleCount[element]] = value; // 将arr[i] 放入对应的桶中
                bucketEleCount[element]++; // 对放入对应桶的数据进行基数
            }
            // 将bucket中的数据依次插入到arr中
            int j = 0;
            for (int i = 0; i < bucketEleCount.length; i++) {
                indexHelper = 0;
                while (bucketEleCount[i] != 0) {
                    arr[j] = bucket[i][indexHelper];
                    indexHelper++;
                    bucketEleCount[i]--;
                    j++;
                }
            }
        }
    }

    /**
     * 对包含负数的数组进行排序
     */
    public static void sort2(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCount = new int[bucket.length];
        int temp = Math.abs(arr[0]); // 临时变量 假设索引0的值位最大值（绝对值）
        int indexHelper;
        // 遍历 比较 来获取最大值(绝对值)
        for (int i = 1; i < arr.length; i++) {
            if (temp < Math.abs(arr[i])) {
                temp = Math.abs(arr[i]);
            }
        }
        String str = temp + "";
        int element;
        for (int k = 0, n = 1; k < str.length(); k++, n *= 10) {
            for (int value : arr) {
                element = value / n % 10; // 当前第K次循环 arr中元素的基数
                // 对element 判断是否位负数 如果为负数就去绝对
                element = Math.abs(element);
                bucket[element][bucketEleCount[element]] = value; // 将arr[i] 放入对应的桶中
                bucketEleCount[element]++; // 对放入对应桶的数据进行基数
            }
            // 将bucket中的数据依次插入到arr中
            int j = 0;
            // 先将桶从大到小将负数 添加  然后在从小到大添加正数
            for (int i = bucketEleCount.length - 1; i >= 0; i--) {
                indexHelper = 0;
                while (bucketEleCount[i] != 0 && indexHelper < bucketEleCount.length) {
                    if (bucket[i][indexHelper] < 0) {
                        arr[j] = bucket[i][indexHelper];
                        j++;
                        bucketEleCount[i]--;
                    }
                    indexHelper++;
                }
            }
            // 然后将正数添加
            for (int i = 0; i < bucketEleCount.length; i++) {
                indexHelper = 0;
                while (bucketEleCount[i] != 0 && indexHelper < bucketEleCount.length) {
                    if (bucket[i][indexHelper] >= 0) {
                        arr[j] = bucket[i][indexHelper];
                        j++;
                        bucketEleCount[i]--;
                    }
                    indexHelper++;
                }
            }
        }
    }

}
