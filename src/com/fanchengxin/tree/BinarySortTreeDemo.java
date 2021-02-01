package com.fanchengxin.tree;

import java.util.Scanner;

/**
 * 二叉排序树
 * 思路：一个节点包含数据部分，左节点，右节点，一个树结构 持有一个root节点的引用
 * 有序的将数据存放
 * 添加：
 * 1 root节点如果为空 直接添加
 * 2 root节点不为空 将要存放的数据 与root比较
 * 2-1 比该节点小 往左递归
 * 2-2 比该节点大 往右递归
 * 2-3 递归退出条件 递归的下次指向（左/右节点为空 那么就添加）
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        BinarySortTree binarySortTree = new BinarySortTree();
        boolean flag; // 标识位
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add  添加数据");
            System.out.println("list 显示数据");
            System.out.println("find 查找数据");
            System.out.println("del  根据id删除数据");
            System.out.println("exit 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入name：");
                    String name = scanner.next();
                    EmployeeNode employeeNode = new EmployeeNode(id, name);
                    flag = binarySortTree.add(employeeNode);
                    if (flag) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败，id重复");
                    }
                    break;
                case "list":
                    binarySortTree.suffixList();
                    break;
                case "find":
                    System.out.println("请输入查找的id");
                    int idd = scanner.nextInt();
                    EmployeeNode findOne = binarySortTree.findById(idd);
                    if (findOne != null) {
                        System.out.printf("id : %d name : %s\n", findOne.id, findOne.name);
                    } else {
                        System.out.println("没有找到id为" + idd + "的数据");
                    }
                    break;
                case "del":
                    int delId = scanner.nextInt();
                    boolean removeFlag = binarySortTree.removeNodeById(delId);
                    if (removeFlag) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败，id为" + delId + "不存在");
                    }
                    break;
                case "exit":
                    scanner.close();
                    return;

                default:
                    break;
            }
        }
    }

}

// 节点类
class EmployeeNode {

    // 左节点
    public EmployeeNode leftNode;

    // 右节点
    public EmployeeNode rightNode;

    // 存放数据
    public int id;

    public String name;
    public EmployeeNode(int id, String name) {

        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {

        return "EmployeeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    // 前序遍历
    public void preOrder() {

        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {

        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    // 后序遍历
    public void suffixOrder() {

        if (this.leftNode != null) {
            this.leftNode.suffixOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.suffixOrder();
        }
        System.out.println(this);
    }

}

// 二叉树 实现类 （存放数据为员工信息 要求id不能重复）
class BinarySortTree {

    // 持有根节点
    private EmployeeNode rootNode;
    // 添加节点的方法 返回值 true 添加成功 false 添加失败
    public boolean add(EmployeeNode employeeNode) {

        boolean flag;
        // 判断根节点是否为空
        if (rootNode == null) {
            rootNode = employeeNode;
            flag = true;
        } else { // 根节点不为空
            // 开始递归
            flag = add(employeeNode, rootNode);
        }
        return flag;
    }
    // 一个递归添加的方法
    /**
     * @param employeeNode 需要带添加的节点数据
     * @param curNode      从当前节点添加的节点 与待添加的节点做比较用
     */
    private boolean add(EmployeeNode employeeNode, EmployeeNode curNode) {

        if (employeeNode.id > curNode.id) { // 大于当前节点 向右递归
            if (curNode.rightNode == null) { // 先判断 该节点的右节点是否为空
                curNode.rightNode = employeeNode;
                return true;
            } else {
                return add(employeeNode, curNode.rightNode);
            }
        } else if (employeeNode.id < curNode.id) { // 小于当前节点 向左递归
            if (curNode.leftNode == null) {
                curNode.leftNode = employeeNode;
                return true;
            } else {
                return add(employeeNode, curNode.leftNode);
            }
        } else { // id值等于当前节点 添加失败
            return false;// 添加失败
        }
    }

    // 展示树结构的所有数据 递归
    /**
     * 展示数据分三种
     * 前序 中序 后序
     * 1 前序
     * 1-1 输出根节点
     * 1-2 遍历左节点
     * 1-3 遍历右节点
     * 2 中序
     * 2-1 遍历左节点
     * 2-2 输出根节点（当前节点）
     * 2-3 遍历右节点
     * 3 后序
     * 3-1 遍历左节点
     * 3-2 遍历右节点
     * 3-3 输出根节点
     */
    // 前序
    public void preList() {

        if (rootNode == null) {
            System.out.println("数据为空");
        } else {
            rootNode.preOrder();
        }
    }
    // 中序
    public void infixList() {

        if (rootNode == null) {
            System.out.println("数据为空");
        } else {
            rootNode.infixOrder();
        }
    }
    // 后序遍历
    public void suffixList() {

        if (rootNode == null) {
            System.out.println("数据为空");
        } else {
            rootNode.suffixOrder();
        }
    }

    // 根据id查找节点
    public EmployeeNode findById(int id) {

        if (rootNode == null) {
            return null;
        }
        if (rootNode.id == id) {
            return rootNode;
        } else {
            EmployeeNode curNode = rootNode;
            if (id > rootNode.id) {
                curNode = rootNode.rightNode;
            } else {
                curNode = curNode.leftNode;
            }
            while (curNode != null) {
                if (id > curNode.id) {
                    curNode = curNode.rightNode;
                } else if (id == curNode.id) {
                    break;
                } else {
                    curNode = curNode.leftNode;
                }
            }
            return curNode;
        }
    }
    // 根据ID查找该id对应节点的上节点
    public EmployeeNode findPreNode(int id, EmployeeNode node) {

        if (id > node.id && node.rightNode != null) {
            if (node.rightNode.id == id) {
                return node;
            } else {
                return findPreNode(id, node.rightNode);
            }
        } else if (id < node.id && node.leftNode != null) {
            if (node.leftNode.id == id) {
                return node;
            } else {
                return findPreNode(id, node.leftNode);
            }
        } else if (id == node.id) {
            if (node == rootNode) {
                return rootNode;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 根据ID按照规则删除对应的节点
    /**
     * @param id 要删除id对应的节点
     * @return true 删除成功 false删除失败（原因id对应的数据没找到）
     *         思路：
     *         1 根据id找到对应该节点的父节点
     *         2 根据返回的上一个节点判断
     *         2-1 如果为空 说明未找到 则删除失败
     *         2-2 如果id等于返回节点的id 那么该节点为父节点
     *         3 反之说明返回节点为父节点 则判断id与父节点的左右节点比较 看看属于父节点的左/右节点
     *         将id对应的节点的右节点指向自己的节点（父节点的左/右节点）
     *         将id对应节点的左节点指向自己的右节点的左节点（如果为空直接指向 如果不为空 则循环左节点为空的节点然后赋值）
     */
    public boolean removeNodeById(int id) {
        // 根据id获取该id的上级节点
        EmployeeNode preNode = findPreNode(id, rootNode);
        // 判断获取的上级节点
        if (preNode == null) {
            return false;
        }
        if (id == preNode.id) {
            if (preNode.leftNode == null) {
                rootNode = rootNode.rightNode;
            } else if (preNode.rightNode == null) {
                rootNode = rootNode.leftNode;
            } else {
                EmployeeNode left = rootNode.leftNode;
                EmployeeNode right = rootNode.rightNode;
                rootNode = right;
                if (right.leftNode == null) {
                    right.leftNode = left;
                } else {
                    EmployeeNode helpNode = right.leftNode;
                    while (helpNode.leftNode != null) {
                        helpNode = helpNode.leftNode;
                    }
                    helpNode.leftNode = left;
                }
            }
        } else { // 为上级节点
            if (id > preNode.id) {
                EmployeeNode left = preNode.rightNode.leftNode;
                EmployeeNode right = preNode.rightNode.rightNode;
                if (left == null) {
                    preNode.rightNode = right;
                } else if (right == null) {
                    preNode.rightNode = left;
                } else {
                    preNode.rightNode = right;
                    if (right.leftNode == null) {
                        right.leftNode = left;
                    } else {
                        EmployeeNode helpNode = right.leftNode;
                        while (helpNode.leftNode != null) {
                            helpNode = helpNode.leftNode;
                        }
                        helpNode.leftNode = left;
                    }
                }
            } else {
                EmployeeNode left = preNode.leftNode.leftNode;
                EmployeeNode right = preNode.leftNode.rightNode;
                if (left == null) {
                    preNode.leftNode = right;
                } else if (right == null) {
                    preNode.leftNode = left;
                } else {
                    preNode.leftNode = right;
                    if (right.leftNode == null) {
                        right.leftNode = left;
                    } else {
                        EmployeeNode helpNode = right.leftNode;
                        while (helpNode.leftNode != null) {
                            helpNode = helpNode.leftNode;
                        }
                        helpNode.leftNode = left;
                    }
                }
            }
        }
        return true;
    }

}
