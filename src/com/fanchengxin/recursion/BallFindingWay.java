package com.fanchengxin.recursion;

/**
 * 小球找路 递归回溯
 */
public class BallFindingWay {
    public static void main(String[] args) {
        // 创建一个地图 8x8
        int[][] map = new int[8][8];
        // 设置地图边界为1
        // 1.设置第一行和第八行为1
        for (int i = 0; i <= 7; i++) {
            map[0][i] = 1;// 第1行为1
            map[7][i] = 1;// 第8行为1
        }
        // 设置第1,8列为1
        for (int i = 0; i <= 7; i++) {
            map[i][0] = 1;// 第一列为1
            map[i][7] = 1;// 第八列为1
        }
        // 设置障碍物
        map[2][3] = 1;
        map[3][2] = 1;
        map[4][5] = 1;
        // 打印地图
        System.out.println("当前地图为");
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        searchWay(map, 1, 1);
        System.out.println("小球寻路后的的地图");
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 创建小球寻路的方法 ====>递归回溯
     * 寻路策略: 右>下>左>上
     * 规则:如果map[6][6]=2就找到出路 然后打印地图
     */
    public static boolean searchWay(int[][] map, int i, int j) { // i , j 是起点坐标
        // 如果到map[6][6]==2 就是到了终点
        if (map[6][6] == 2) {
            return true;
        } else { // 判断 如果下一坐标的数字 1:为墙 走不通 2:为走过,3:死路,0:可以走
            if (map[i][j] == 0) {
                // 先假设
                map[i][j] = 2;
                if (searchWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (searchWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (searchWay(map, i, j - 1)) {// 向左走
                    return true;
                } else if (searchWay(map, i - 1, j)) {// 向上走
                    return true;
                } else {// 都走不通 那么就把当前坐标点改为3
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
