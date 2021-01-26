package com.fanchengxin.sort;

/**
 * 归并排序算法
 */
public class MergeSort {
    public static void main(String[] args) {
        // 验证归并排序算法的正确性
        // int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        // System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr.length]; // 临时的存放数组需要合需要排序的数组大小一样（空间换时间）
        long before = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long after = System.currentTimeMillis();
        System.out.println("归并排序算法花费时间为：" + (after - before) + "毫秒");
        // System.out.println("排序后的为\n" + Arrays.toString(arr));
    }

    /**
     * 分+合并的方法
     * 递归使用此方法
     *
     * @param arr   需要分的有序数组
     * @param left  需要分的数组的左下标
     * @param right 需要分的数组右下标
     * @param temp  临时存放的数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (left < right) { //  将数组分为 每个序列只有一个为止
            int mid = (left + right) / 2;
            // 继续分左右序列 直到长度为1
            mergeSort(arr, left, mid, temp);  // 向左递归进行分解
            mergeSort(arr, mid + 1, right, temp); // 向右递归进行分解
            // 然后 开始合并 第一次为顶栈 mergeSort方法
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   需要排序的数组
     * @param left  有序数组（分解后的部分）的坐下标
     * @param mid   有序数组的中间下标
     * @param right 有序数组的右下标
     * @param temp  临时存放合并的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left; // 初始化l 左边序列的的初始索引
        int r = mid + 1; // 初始化 r 右边序列的初始索引
        int t = 0;// 临时数组的下标
        // 先把左右两边的数据 按照规则填充到temp中
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) { // 如果左下标 小于右下标的值 那么就把左下标的值存放在temp[t]中
                temp[t] = arr[l];
                l++;
                t++;
            } else {// 反之 右边的比左边的小 那么存放右边到temp中
                temp[t] = arr[r];
                t++;
                r++;
            }
        }
        // 如果此时左右序列中有一个序列中还有值那么就依次放入到temp中
        // 先判断左边
        while (l <= mid) {
            temp[t] = arr[l];
            l++;
            t++;
        }
        // 在判断右边
        while (r <= right) {
            temp[t] = arr[r];
            t++;
            r++;
        }
        // 此时 将temp中的数据copy到arr中 left:0,right:1 2,3 4,5 .... 0,arr.length-1
        t = 0;
        int leftTemp = left; // 临时左下标 并不总是为0
        while (leftTemp <= right) {
            arr[leftTemp] = temp[t];
            leftTemp++;
            t++;
        }
    }
}
