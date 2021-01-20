package com.fanchengxin.stack;

import java.util.ArrayList;

/**
 * 实现 简单的计算 四则运算表达式的结果(不含括号版本)
 * version: 1.0
 */
public class StackDemo {
    public static void main(String[] args) {

        /*ArrayStack arrayStack = new ArrayStack(10);
        boolean flag = true;
        while (flag) {
            System.out.println("输入pop删除数据");
            System.out.println("输入push添加数据");
            System.out.println("输入show显示数据");
            System.out.println("输入exit退出程序");
            System.out.println("请输入相应的字符:");
            Scanner scanner = new Scanner(System.in);
            String var = scanner.next();
            switch (var) {
                case "pop":
                    try {
                        int numb = arrayStack.pop();
                        System.out.printf("弹出数据为%d: \n",numb);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                        break;
                    }
                case "push":
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("请输入需要添加的数据(数字整数)");
                    int num = scanner1.nextInt();

                    arrayStack.push(num);
                    break;
                case "show":
                    arrayStack.show();
                    break;
                case "exit":
                    flag = false;
                    break;
            }
        }*/
        // 实现计算器功能
        String expression = "7*2*1-9+10000/1000";
        String keepInt = "";
        int num1; // 从数字栈中弹出的第一个
        int num2; // 从数字栈中弹出的第二个
        int res;  // 计算结果
        char ch;// 遍历表达式的辅助变量
        char oper; // 从操作栈中弹出的操作符
        int index = 0; // 辅助 索引
        // 创建两个栈 一个为数栈 一个为字符栈
        ArrayStack<Integer> numStack = new ArrayStack<>(new ArrayList<>());// 数字栈
        ArrayStack<Character> operStack = new ArrayStack<>(new ArrayList<>()); // 操作符栈
        // 对字符串expression进行遍历
        while (true) {
            if (index <= expression.length() - 1) {
                ch = expression.charAt(index);
                // 字符为操作符
                if (ArrayStack.isOper(ch)) {

                    // 判断操作栈是否为空
                    if (operStack.isEmpty()) {// 为空直接添加
                        operStack.push(ch);
                    } else {// 操作符不为空
                        // 判断 栈中的优先级和ch比较
                        if (ArrayStack.priory(ch) > ArrayStack.priory(operStack.top())) {// 遍历优先级大于栈中的 优先计算
                            operStack.push(ch);
                        } else {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            res = ArrayStack.calc(num1, num2, oper);
                            numStack.push(res);
                            // 在此判断操作栈中是否还有操作符
                            while (!operStack.isEmpty()) {// 如果有 再次判断优先级 直到操作栈为空
                                if (ArrayStack.priory(ch) > ArrayStack.priory(operStack.top())) {
                                    operStack.push(ch);
                                } else {// 优先级相等或者小于操作栈中的操作符
                                    num1 = numStack.pop();
                                    num2 = numStack.pop();
                                    oper = operStack.pop();
                                    res = ArrayStack.calc(num1, num2, oper);
                                    numStack.push(res);
                                }
                            }
                            // 操作栈为空 可以放入操作符
                            operStack.push(ch);
                        }
                    }
                } else {// 字符串为数字
                    keepInt += ch;
                    if (index == expression.length() - 1 || ArrayStack.isOper(expression.charAt(index + 1))) {
                        res = Integer.parseInt(keepInt);
                        keepInt = "";
                        System.out.println("-----");
                        numStack.push(res);
                    }
                }
                System.out.printf("第%d次1\n", index);
                numStack.show();

                System.out.println("操作栈");
                operStack.show();
                index++;
            } else {
                System.out.printf("第%d次2\n", index);
                numStack.show();

                System.out.println("操作栈");
                operStack.show();
                while (!operStack.isEmpty()) {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = ArrayStack.calc(num1, num2, oper);
                    numStack.push(res);
                }
                System.out.printf("算术表达式%s的计算结果为%d", expression, numStack.pop());
                break;
            }
        }
    }
}

// 用数组模拟栈结构
class ArrayStack<E> {
    // 属性需要 顶部指针
    public int top = -1; // 顶部指针

    public ArrayList<E> stack;

//        private int maxSize; // 栈的大小

    public ArrayStack(ArrayList<E> stack) {
        this.stack = stack;
    }

    // 实现运算功能
    public static int calc(int num1, int num2, char oper) {
        int num = 0;
        if (oper == '*' || oper == '/' || oper == '+' || oper == '-') {
            switch (oper) {

                case '*':
                    num = num2 * num1;
                    break;
                case '/':
                    if (num1 != 0) {

                        num = num2 / num1;
                    } else {
                        throw new RuntimeException("非法计算 ,不能除0");
                    }
                    break;
                case '+':
                    num = num2 + num1;
                    break;
                case '-':
                    num = num2 - num1;
                    break;
            }
            return num;
        } else {
            throw new RuntimeException("非法计算,请检查字符是否正确");
        }
    }

    // 判断运算符的优先级
    public static int priory(Character ch) {
        // * 和 / 的优先级为1
        if (ch == '*' || ch == '/') {
            return 1;
        }

        // + 和- 的优先级为0;
        if (ch == '+' || ch == '-') {
            return 0;
        } else {
            throw new RuntimeException("输入非法字符");
        }
    }

    // 判断 字符是否为运算符
    public static boolean isOper(Character ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    // 添加方法 push
    public void push(E num) {
        // 添加一个 指针向上移动一下
        top++;
        stack.add(top, num);
    }

    // 删除方法 pop
    public E pop() {
        // 先判断 是否为空
        if (!isEmpty()) {

            E e = stack.get(top);
            top--;
            return e;
        } else {

            throw new RuntimeException("栈数据为空,删除失败 ");
        }
    }

    // 显示栈的方法 show
    public void show() {
        // 判断是否为空
        if (top == -1) {
            System.out.println("栈数据为空");
        } else {// 不为空 遍历 输出
            for (int i = top; i >= 0; i--) {
                System.out.printf("栈数据为: %s \n", stack.get(i));
            }
        }
    }

    // 显示顶栈的数据
    public E top() {
        return stack.get(top);
    }

    // 判断栈是否为空
    public boolean isEmpty() {

        return top == -1;
    }
}