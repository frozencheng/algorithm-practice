package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 冒泡排序 算法
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {110, 1, -1, -2, -3, -4};
        sortAsc(array);
        System.out.println(Arrays.toString(array));
    }

    // 冒泡排序 从小到大排序 时间复杂度为O(n^2)
    public static void sortAsc(int[] array) {
        int temp; // 临时变量
        boolean flag; // 标识符 用来判断 数组中的元素是否还在进行交换
        for (int i = 0; i < array.length - 1; i++) {
            flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {// 每次循环确定当前循环的最大值
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (!flag) { // 说明本次循环没有发生交换 也就是排序已经完毕了
                break;
            }
        }
    }
}
