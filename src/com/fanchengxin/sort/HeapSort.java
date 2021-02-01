package com.fanchengxin.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 将一个无序数组看成一个无序和有序两部分 刚开始无序部分为数组的长度
 * 构造一个大顶堆 然后将大顶堆的元素和无序数组的最后一个值兑换 然后再次构建大顶堆 将无序数组的长度-1
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = {10, 9, 4, 6, 5, 3, -1, -100};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        long before = System.currentTimeMillis();
        int[] array = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        heapSort(array);
        long after = System.currentTimeMillis();
        System.out.println("排序花费时间为" + (after - before) + "毫秒");
    }

    /**
     * 构建大顶堆的方法
     *
     * @param arr    需要排序的数组
     * @param length 数组的长度
     * @param index  堆的子树节点
     */
    public static void adjustHeap(int[] arr, int length, int index) {

        int temp = arr[index]; // 临时变量
        int left = 2 * index + 1; // 子树节点的左节子点
        int right = 2 * index + 2; // 子树节点的右子节点
        int maxIndex = index; // 临时最大子节点
        // 大顶堆的规则要求子树节点要大于等于左子节点 和右子节点
        if (left < length && arr[left] > arr[maxIndex]) {
            maxIndex = left;
        }
        if (right < length && arr[right] > arr[maxIndex]) {
            maxIndex = right;
        }
        if (index != maxIndex) {
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            adjustHeap(arr, length, maxIndex);
        }
    }
    /**
     * 堆排序的方法
     *
     * @param arr 需要待排序的数组
     */
    public static void heapSort(int[] arr) {

        int temp; // 临时变量
        int i = arr.length / 2 - 1; // 该数组的最后一个子树节点
        for (int j = i; j >= 0; j--) { // cong最后的子树节点开始 构建大顶堆
            adjustHeap(arr, arr.length, j);
        }
        // 构建完大顶堆后 将顶堆最大值与 最后一个子节点交换 然后无序数组的长度-1 再将将换的后的数组构建成新的大顶堆
        for (int length = arr.length - 1; length > 0; length--) {
            temp = arr[0];
            arr[0] = arr[length];
            arr[length] = temp;
            adjustHeap(arr, length, 0);
        }
    }

}
