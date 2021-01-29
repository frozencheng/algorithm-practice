package com.fanchengxin.search;

import java.util.Arrays;

/**
 * 斐波那契查找法
 */
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 8, 100, 1234, 1235, 2000};
        int index = Search(arr, 8);
        System.out.println(index);
    }
    public static int[] f() {

        int[] fibonacci = new int[maxSize];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    // 斐波那契查找法 非递归
    public static int Search(int[] arr, int searchVal) {

        int left = 0;
        int right = arr.length - 1;
        int k = 0; // 斐波那契数列的索引值 初始值为0；
        int[] F = f();
        while (right > F[k] - 1) {
            k++;
        }
        System.out.println("k=" + k);
        // F[k]的值可能大于arr的长度 所以在创建一个新的数组来适配
        int[] copy = Arrays.copyOf(arr, F[k]); // 将 arr数组拷贝到新的适合斐波那契数列长度的新数组中 并将 >arr.length的值全部设为arr[right]
        for (int i = right + 1; i < copy.length; i++) {
            copy[i] = arr[right];
        }
        System.out.println(Arrays.toString(copy));
        // 开始用斐波那契的特性 查找数列匹配的值
        while (left <= right && k >= 1) {
            int mid = left + F[k - 1] - 1;
            if (searchVal < copy[mid]) {
                k--;
                // right=mid-1;
            } else if (searchVal > copy[mid]) {
                left = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, arr.length - 1);
            }
        }
        return -1;// 上面的循环如果没有返回 那么就是没有找到
    }

}
