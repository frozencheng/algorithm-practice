package com.fanchengxin.sort;

/**
 *
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, -1};
        int[] array = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        long before = System.currentTimeMillis();
        sort(array, 0, array.length - 1);
        long after = System.currentTimeMillis();
        System.out.println("排序时间花费" + (after - before) + "毫秒");
    }

    // 快速排序算法(从小到大)， 去数组最左边的值为pivot 递归
    public static void sort(int[] array, int L, int R) {
        // 该算法需要用递归 所以 需要一个退出条件（说明该数组排序已经完毕了）
        if (L >= R || array.length == 1) {
            return;
        }
        int left = L;
        int right = R;
        int pivot = array[left];

        while (left < right) {
            // 先移动右边的值 直到碰到小于等于pivot 然后赋值给array[left]
            while (array[right] >= pivot && right > left) { //
                right--;
            }
            // 右边可能已经找到了 所以要判断
            if (right > left) {
                array[left] = array[right];
                left++;
            }

            while (array[left] <= pivot && left < right) {
                left++;
            }
            if (array[left] > pivot) {
                array[right] = array[left];
                right--;
            }
            if (left == right) {
                array[left] = pivot;
                break;
            }
//            System.out.println(Arrays.toString(array));
        }
        if (left == right) {
            left++;
            right--;
        }

        if (right > L) {// 将左半边的 继续排序
            sort(array, L, right);
        }
        if (left < R) {// 将右半边的 继续排序
            sort(array, left, R);
        }
    }
}
