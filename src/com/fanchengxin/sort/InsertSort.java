package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 插入排序算法
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {6, 5, 4, 3, 2, 1};
        System.out.println("排序前:\n" + Arrays.toString(array));
        System.out.println("排序后");
        sortAsc(array);
        sortDesc(array);
    }
    //
    // 插入排序算法 从小到大 时间复杂度为O(n^2)
    public static void sortAsc(int[] array) {

        int insertVal;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertVal = array[i];
            insertIndex = i - 1;// array[i]前面一个数的下标
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }

            array[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "轮" + Arrays.toString(array));
        }
    }
    // 插入排序 从大到小
    public static void sortDesc(int[] array) {
        int insertVal;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertVal = array[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal > array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertVal;
            System.out.println("第" + i + "轮" + Arrays.toString(array));
        }
    }
}
