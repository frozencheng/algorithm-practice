package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 选择排序算法
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {1, 1, 1, 1, 1, -2, -3, -1};
        System.out.println("排序前:" + Arrays.toString(array));
        sortAsc(array);
        System.out.println("排序后" + Arrays.toString(array));
    }
    // 选择排序算法 从小到大排序 时间复杂度为O(n^2)
    public static void sortAsc(int[] array) {
        int minIndex;// 临时最小值的索引
        int min;// 临时最小值
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}


