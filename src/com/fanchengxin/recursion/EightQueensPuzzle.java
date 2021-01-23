package com.fanchengxin.recursion;

/**
 * 八皇后问题
 */

public class EightQueensPuzzle {

    // 设置 放置皇后的最大值
    public int max = 8;

    public int[] array = new int[max];
    public static void main(String[] args) {
        EightQueensPuzzle eightQueensPuzzle = new EightQueensPuzzle();

        eightQueensPuzzle.check(0);
    }
    // 打印八皇后的方法
    public void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // 编写一个方法来 判断 放置第N个皇后是否与之前的冲突
    // 1. array[i]==array[n] 判断为同一列
    // 2. Math.abs(n-i)==Math.abs(array[n]-array[i]) 判断为在斜线上
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {

            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 编写一个方法 来放置第n个皇后
    public void check(int n) {
        if (n == max) { // 如果n=8 //说明已经放置了8个皇后了那么就打印
            print();
        } else {
            for (int i = 0; i < max; i++) {
                array[n] = i; // 先放置第n个到 第n行的第i列
                if (judge(n)) { // 如果不冲突 就放下一个

                    check(n + 1);
                }
            }
        }
    }
}
