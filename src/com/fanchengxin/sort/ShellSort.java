package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 希尔排序算法(缩小增量)
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {10, 4, 3, 2, 1, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1, 1, 1, 2, 3, 4, 100, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000); // 生成 [1,80000) 中的随机数
        }
        long timeBefore = System.currentTimeMillis();
        sortAscAsInsert(arr);
        System.out.println(Arrays.toString(array));
        long timeAfter = System.currentTimeMillis();
        System.out.println("花费时间" + (timeAfter - timeBefore) + "毫秒");
    }

    // 采用交换式 从小到大排序 缺点 交换太费时间
    public static void sortAscAsChange(int[] array) {
        int temp;
        for (int avg = array.length / 2; avg > 0; avg /= 2) {

            for (int i = avg; i < array.length; i++) { // 对 分组的组数(avg)进行遍历
                for (int j = i - avg; j >= 0; j -= avg) { // 对每组的元素 以步长为 avg 进行遍历判断大小决定是否交换
                    if (array[j] > array[j + avg]) { // 判断 大小 是否需要交换位置
                        temp = array[j];
                        array[j] = array[j + avg];
                        array[j + avg] = temp;
                    }
                }
            }
        }
    }

    // 采用移动式(针对分组的每一组进行插入排序,对交换式进行的优化)
    public static void sortAscAsInsert(int[] array) {
        int insertVal;
        int insertIndex;
        for (int avg = array.length / 2; avg > 0; avg /= 2) { // 将数组分为 avg组
            for (int i = avg; i < array.length; i++) { // 对分组的 每组 进行 插入排序
                insertIndex = i - avg;
                insertVal = array[i];
                while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                    array[insertIndex + avg] = array[insertIndex];
                    insertIndex -= avg;
                }
                if (insertIndex + avg != i) {
                    array[insertIndex + avg] = insertVal;
                }
            }
        }
    }
}
