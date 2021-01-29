package com.fanchengxin.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 插值查找法
 * 适用范围：数据量大，数组的关键值分布均匀 用插值查找法速度优于二分法 查找边界值速度快
 * 如果数组的关键值分布不均匀则 二分法优于插值查找法
 */
public class InsertSearch {

    public static void main(String[] args) {

        int[] arr = {-2, -1, 0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
        int index = search(arr, 0, arr.length - 1, 4);
        System.out.println(index);
        List<Integer> list = searchList(arr, 0, arr.length - 1, 1);
        System.out.println(list);
    }
    /**
     * 插值查找法 和 二分查找一样 只能在有序的数组里面查找
     *
     * @param arr       查找数组
     * @param left      左下标
     * @param right     右下标
     * @param searchVal 查找的目标值
     * @return 返回查找到的下标 若没有返回 -1
     */

    public static int search(int[] arr, int left, int right, int searchVal) {

        System.out.println("插值查找法index");
        // 这个条件是必须需要的 否则得到的mid可能越界
        if (left > right || searchVal > arr[right] || searchVal < arr[left]) {
            return -1;
        }
        int mid = left + (searchVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        if (searchVal < arr[mid]) {
            return search(arr, left, mid - 1, searchVal);
        } else if (searchVal > arr[mid]) {
            return search(arr, mid + 1, right, searchVal);
        } else {
            return mid;
        }
    }
    /**
     * 插值查找法 返回数组中等于目标数的所有下标
     * 插值查找法 和 二分查找一样 只能在有序的数组里面查找
     *
     * @param arr       查找数组
     * @param left      左下标
     * @param right     右下标
     * @param searchVal 查找的目标值
     * @return 返回查找到的所有下标list 若没有返回 空list
     */

    public static List<Integer> searchList(int[] arr, int left, int right, int searchVal) {

        System.out.println("插值查找法list");
        if (left > right || searchVal > arr[right] || searchVal < arr[left]) {
            return new ArrayList<>();
        }
        int mid = left + (searchVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        if (searchVal < arr[mid]) {
            return searchList(arr, left, mid - 1, searchVal);
        } else if (searchVal > arr[mid]) {
            return searchList(arr, mid + 1, right, searchVal);
        } else {
            List<Integer> list = new ArrayList<>();
            int help = mid - 1;
            // 向左变查找
            while (help >= 0 && arr[help] == arr[mid]) {
                list.add(help);
                help--;
            }
            list.add(mid);
            // 向右边查找
            help = mid + 1;
            while (help < arr.length && arr[help] == arr[mid]) {
                list.add(help);
                help++;
            }
            Collections.sort(list);
            return list;
        }
    }

}
