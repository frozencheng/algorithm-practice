package com.fanchengxin.stack;

import java.util.*;

/**
 * 逆波兰表达式 计算
 */
public class PolandRotation {
    public static void main(String[] args) {
        // 验证 逆波兰表达式/后缀表达式
        String expression1 = "4 5 x 8 - 60 + 8 2 / +";// 预计计算结果为76
        List<String> list1 = turnList(expression1);
        int res1 = calc(list1);
        System.out.println(res1);

        // 验证 中缀表达式转后缀表达式
        String expression2 = "( 4 x 5 ) - 8 + 60 + 8 / 2 "; // 预计 ==> "4 5 x 8 - 60 + 8 2 / +"
        List<String> list2 = turnNotationExpr(expression2);
        int res2 = calc(list2);
        System.out.println(res2);

        // 验证字符串表达式转换为list表达式
        String str = "( 4 0 x 5 0 )-8+ 60+800/200 "; // 预计结果2056
        List<String> listExpr = strTurnList(str);
        System.out.println(listExpr);// 预计结果===> [(, 40, x, 50, ), -, 8, +, 60, +, 800, /, 200]
        List<String> list3 = turnNotationExpr(listExpr);
        int res3 = calc(list3);
        System.out.println(res3);
    }

    // 将一个字符串形式的表达式转换成 list形式的表达式
    public static List<String> strTurnList(String str) {
        List<String> list = new ArrayList<>();
        // 对 字符串进行遍历
        String tle;
        String tln;
        for (int i = 0; i < str.length(); i++) {
            tle = String.valueOf(str.charAt(i));// 将遍历的字符 先转成字符串
            // 如果是数字 对后一位也进行判断是否为数字 如果是就进行拼接,直到后一位不是为止
            if (tle.matches("\\d")) {
                if (i == str.length() - 1) {
                    list.add(tle);
                }

                for (int j = i + 1; j < str.length(); j++) {
                    tln = String.valueOf(str.charAt(j));
                    if (tln.matches("\\d")) {
                        if (j == str.length() - 1) {
                            tle += tln;
                            i = j;
                            list.add(tle);
                        }
                        tle += tln;
                    } else if (tln.matches("\\s")) { // 遇到空格就略过
                        if (j == str.length() - 1) {
                            i = j;
                            list.add(tle);
                        }
                    } else {

                        i = j - 1;
                        list.add(tle);

                        break;
                    }
                }
            }

            // 如果不是数字且不为空格就直接添加
            else {
                if (!tle.matches("\\s")) {
                    list.add(tle);
                }
            }
        }

        return list;
    }

    // 将逆波兰表达式(字符串)处理成list逆波兰表达式
    @Deprecated
    public static List<String> turnList(String expression) {
        List<String> list = new ArrayList<>();
        String[] split = expression.split(" ");
        Collections.addAll(list, split);
        return list;
    }

    // 判断操作符优先级
    public static int priory(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return 1;
        } else if ("/".equals(s) || "*".equals(s) || "x".equals(s) || "X".equals(s)) {
            return 2;
        } else {
            throw new RuntimeException("非法字符,请检查");
        }
    }

    // 计算波兰表达式
    public static int calc(List<String> list) {
        // 先创建一个栈
        Stack<String> stack = new Stack<>();
        int num1;
        int num2;
        int res;
        // 对list进行遍历
        for (String elem : list) {
            // 如果是数字就直接压栈
            if (elem.matches("\\d+")) {
                stack.push(elem);
            }

            // 如果不是数字 那么就是操作符 进行计算 + - * X x /
            else {
                switch (elem) {
                    case "+":
                        num1 = Integer.parseInt(stack.pop());
                        num2 = Integer.parseInt(stack.pop());
                        res = num1 + num2;
                        break;
                    case "-":
                        num1 = Integer.parseInt(stack.pop());
                        num2 = Integer.parseInt(stack.pop());
                        res = num2 - num1;
                        break;
                    case "/":
                        num1 = Integer.parseInt(stack.pop());
                        num2 = Integer.parseInt(stack.pop());
                        res = num2 / num1;
                        break;
                    case "*":
                    case "x":
                    case "X":
                        num1 = Integer.parseInt(stack.pop());
                        num2 = Integer.parseInt(stack.pop());
                        res = num2 * num1;
                        break;
                    default:
                        throw new RuntimeException("操作符错误,请检查");
                }
                stack.push(String.valueOf(res));
            }
        }
        // 判断 栈中是否有值 如果有值 那么计算失败 逆波兰表达式有误 如果是 那么逆波兰表达式计算成功 返回结果
        int result = Integer.parseInt(stack.pop());
        if (stack.empty()) {
            return result;
        } else {
            throw new RuntimeException("计算失败 逆波兰表达式有误");
        }
    }

    // 重载turnNotationExpr 参数为string
    public static List<String> turnNotationExpr(String expression) {
        String[] split = expression.split(" ");
        return turnNotationExpr(Arrays.asList(split));
    }

    //  将中缀表达式转换为后缀表达式(逆波兰表达式) 参数为list
    public static List<String> turnNotationExpr(List<String> list) {
        // 创建两个 栈 s1 s2(s2 可以使列表)
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        // 遍历 表达式 遇到

        // 1 , 遇到数字 直接压入 s2
        /*
         * 2,遇到 操作符
         *   1) 如果 s1为空 压入S1
         *   2)如果不为空
         *       1. 比较优先级 如果比S1栈顶的优先级大直接入
         *       2.否则循环将S1栈顶的操作符弹出并压入S2 直到大于S1栈顶的优先级
         * 3,遇到"(",直接入栈S1
         * 4,遇到")",将S1栈顶的操作符弹出并压入S2 直到 遇到"("为止并将"("弹出
         * */
        // 表达式遍历完后 如果s1中海油剩余的数据 则将数据弹出并压入s2中

        for (String elem : list) {

            if (elem.matches("\\d+")) { // 如果为数字
                s2.add(elem);
            }
            // 如果为操作符
            else if ("+".equals(elem) || "-".equals(elem) || "/".equals(elem)
                    || "x".equals(elem) || "X".equals(elem) || "*".equals(elem)) {
                // 如果 S1 为空
                if (s1.empty()) {
                    s1.push(elem);
                } else {// 不为空
                    // 先判断S1栈顶的操作符是否为"("
                    if (s1.peek().equals("(")) {
                        s1.push(elem);
                    }

                    // 不是"(", 如果优先级大于栈顶 直接入 否则弹出s1的栈顶 入s2中直到 大于s1的栈顶
                    else {
                        if (priory(elem) > priory(s1.peek())) {
                            s1.push(elem);
                        } else {
                            while (true) {
                                if (s1.empty() || priory(elem) > priory(s1.peek())) {
                                    s1.push(elem);
                                    break;
                                }
                                s2.add(s1.pop());
                            }
                        }
                    }
                }
            }
            // 如果是"("
            else if ("(".equals(elem)) {
                s1.push(elem);
            }
            // 如果是")"
            else if (")".equals(elem)) {
                String oper;
                while (true) {
                    oper = s1.pop();
                    if (oper.equals("(")) {
                        break;
                    }
                    s2.add(oper);
                }
            } else {
                throw new RuntimeException("表达式有误,请检查运算符是否有误");
            }
        }
        // 遍历完成后 将s1中剩余的字符 弹出并压入s2中
        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        // 将s2的中数据逆向翻转得到就是逆波兰表达式(如果S2开始用的是列表则不需要翻转)

        return s2;
    }
}
