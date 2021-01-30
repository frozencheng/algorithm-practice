package com.fanchengxin.hashTable;

import java.util.Scanner;

/**
 * 模拟哈希表 (数组+链表 的实现)
 * 可以将员工信息（id，name....）添加到哈希表中
 */
public class HashTableDemo {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("添加员工信息      add");
            System.out.println("根据id查询员工信息 find");
            System.out.println("查询所有员工信息   list");
            System.out.println("删除员工信息      remove");
            System.out.println("退出 exit");

            String choice = scanner.next();
            switch (choice) {
                case "add":

                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "remove":
                    System.out.println("输入员工id");
                    int rid = scanner.nextInt();
                    hashTable.removeById(rid);
                    break;
                case "exit":
                    flag = false;
                    break;
                case "find":
                    System.out.println("输入id");
                    hashTable.findById(scanner.nextInt());
                default:
                    break;
            }
        }
    }

}

// 哈希表 存放多链表的数组
class HashTable {

    private final int size;

    private final EmployeeLinked[] employeeLinkedArrays;
    public HashTable(int size) {

        this.size = size;
        this.employeeLinkedArrays = new EmployeeLinked[size];
        for (int i = 0; i < size; i++) {
            employeeLinkedArrays[i] = new EmployeeLinked();
        }
    }
    // 添加方法
    public void add(Employee employee) {

        int id = employee.id;
        int hash = hash(id);
        employeeLinkedArrays[hash].add(employee);
    }

    // 删除方法
    public void removeById(int id) {

        int hash = hash(id);
        employeeLinkedArrays[hash].removeById(id);
    }

    // 查询所有
    public void list() {

        for (int i = 0; i < employeeLinkedArrays.length; i++) {
            employeeLinkedArrays[i].list(i);
            System.out.println();
        }
    }

    // 根据id查询
    public void findById(int id) {

        int hash = hash(id);
        employeeLinkedArrays[hash].findById(id);
    }

    // 散列函数方法
    public int hash(int id) {

        return id % size;
    }

}

// 链表(存放员工的链表）
class EmployeeLinked {

    private Employee head;// 指向下一个员工的节点
    // 添加方法
    public void add(Employee employee) {
        // 找到链表的最后一个节点 然后添加

        if (head == null) {// 头节点为空 直接添加
            head = employee;
            System.out.println("添加成功");
        } else {
            // 辅助指针
            Employee cur = head;
            while (true) {
                if (cur.id == employee.id) {
                    System.out.println("添加失败此id已经存在");
                    break;
                } else if (cur.next == null) {
                    cur.next = employee;
                    System.out.println("添加成功");
                    break;
                }
                cur = cur.next;
            }
        }
    }
    // 删除方法 根据id 删除员工信息
    public void removeById(int id) {

        Employee cur = head; // 辅助指针
        if (cur == null) {
            System.out.println("链表为空 删除失败");
        } else if (cur.id == id) {
            head = cur.next;
            System.out.println("删除成功");
        } else {
            while (true) {
                if (cur.next == null) {
                    System.out.println("删除失败，无此员工信息");
                    break;
                } else if (cur.next.id == id) {
                    cur.next = cur.next.next;
                    System.out.println("删除成功");
                    break;
                } else {
                    cur = cur.next;
                }
            }
        }
    }

    // 查询该链表的所有员工信息
    public void list(int i) {

        System.out.println("链表" + (i + 1) + "：");
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Employee cur = head;
        while (cur != null) {
            System.out.printf("id: %d name: %s \n", cur.id, cur.name);
            cur = cur.next;
        }
    }

    // 查询方法（根据员工id）
    public void findById(int id) {

        Employee cur = head;
        if (cur != null && cur.id == id) {
            System.out.printf("id: %d name: %s \n", cur.id, cur.name);
        } else {
            while (true) {
                if (cur == null) {
                    System.out.println("查询失败,表中没有id为" + id + "的数据");
                    break;
                } else if (cur.id == id) {
                    System.out.printf("id: %d name: %s \n", cur.id, cur.name);
                    break;
                }
                cur = cur.next;
            }
        }
    }

}

// 员工类
class Employee {

    public int id;

    public String name;

    public Employee next;
    public Employee(int id, String name) {

        super();
        this.id = id;
        this.name = name;
    }

}
