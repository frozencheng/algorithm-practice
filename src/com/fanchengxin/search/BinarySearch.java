package com.fanchengxin.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找算法
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1, 3, 3, 5, 7, 9, 10, 100, 100};
        int index = searchIndex(arr, 0, arr.length - 1, 3);
        System.out.println(index);
        List<Integer> list = searchList(arr, 0, arr.length - 1, 3);
        System.out.println(list);
    }

    /**
     * 二分查找法 递归版本
     * 根据指定的数值 在数组中查找 找到返回对应下标 没找到 返回-1
     *
     * @param arr       查找的有序数组
     * @param left      查找数组范围的左下标
     * @param right     右下标
     * @param searchVal 需要查找的值
     * @return 返回下标
     */
    public static int searchIndex(int[] arr, int left, int right, int searchVal) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (searchVal > midVal) {
            return searchIndex(arr, mid + 1, right, searchVal);
        } else if (searchVal < midVal) {
            return searchIndex(arr, left, mid - 1, searchVal);
        } else {
            return mid;
        }
    }

    // 查找出 arr中 等于searchVal 的所有下标
    public static List<Integer> searchList(int[] arr, int left, int right, int searchVal) {

        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (searchVal > midVal) {
            return searchList(arr, mid + 1, right, searchVal);
        } else if (searchVal < midVal) {
            return searchList(arr, left, mid - 1, searchVal);
        } else {
            int help1 = mid - 1;
            List<Integer> list = new ArrayList<>();
            while (help1 >= 0 && arr[help1] == searchVal) {
                list.add(help1);
                help1--;
            }
            list.add(mid);
            int help2 = mid + 1;
            while (help2 <= arr.length - 1 && arr[help2] == searchVal) {
                list.add(help2);
                help2++;
            }
            Collections.sort(list);
            return list;
        }
    }

}
