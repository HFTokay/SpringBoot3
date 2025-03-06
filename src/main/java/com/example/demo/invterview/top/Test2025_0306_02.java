package com.example.demo.invterview.top;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Test2025_0306_02 implements Comparable {

    int row;
    int col;

    @Override
    public int compareTo(Object o) {
        return this.row - ((Test2025_0306_02) o).row;
    }

    public int compareTo(Test2025_0306_02 o) {
        return this.row - o.row;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public static void main(String[] args) {
        System.out.println("compareTo 使用方法");

        //ArrayList
        //TreeMap

//HashSet 和普通 Set‌：默认无序（元素顺序由哈希算法决定）
//TreeSet 的排序依赖‌：必须通过 Comparable 或 Comparator 定义排序规则，否则抛出 ClassCastException
/**
 一、有序接口‌
 List 接口‌
 ‌特性‌：元素按插入顺序存储，允许重复，可通过索引访问。
 ‌实现类‌：ArrayList、LinkedList、Vector
 NavigableSet 接口‌
 ‌特性‌：继承自 SortedSet，支持基于排序的导航方法（如 ceiling()、floor()），元素按自然顺序或自定义顺序排列。
 ‌实现类‌：TreeSet

 ‌二、有序类‌
 TreeSet 类‌
 ‌底层实现‌：基于 TreeMap（红黑树结构），元素按自然顺序或构造时指定的 Comparator 排序‌12。
 ‌要求‌：元素需实现 Comparable 接口，或在构造时提供 Comparator

 LinkedHashSet 类‌
 ‌底层实现‌：基于链表和哈希表，按元素插入顺序维护顺序，同时保证唯一性‌ ArrayList、LinkedList、Vector 类‌
 */
    }

}
