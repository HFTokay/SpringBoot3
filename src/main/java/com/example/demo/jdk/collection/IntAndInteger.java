package com.example.demo.jdk.collection;

/**
 * 基本类型和包装类型的区别？
 * 用途：除了定义一些常量和局部变量之外，
 * 我们在其他地方比如方法参数、对象属性中很少会使用基本类型来定义变量。
 * 并且，包装类型可用于泛型，而基本类型不可以。
 * 存储方式：基本数据类型的局部变量存放在 Java 虚拟机栈中的局部变量表中，
 * 基本数据类型的成员变量（未被 static 修饰 ）存放在 Java 虚拟机的堆中。
 * 包装类型属于对象类型，我们知道几乎所有对象实例都存在于堆中。
 * 占用空间：相比于包装类型（对象类型）， 基本数据类型占用的空间往往非常小。
 * 默认值：成员变量包装类型不赋值就是 null ，而基本类型有默认值且不是 null。
 * 比较方式：对于基本数据类型来说，== 比较的是值。对于包装数据类型来说，
 * == 比较的是对象的内存地址。所有整型包装类对象之间值的比较，全部使用 equals() 方法。
 * ------
 * 著作权归JavaGuide(javaguide.cn)所有
 * 基于MIT协议
 * 原文链接：https://javaguide.cn/java/basis/java-basic-questions-01.html
 */
public class IntAndInteger {
    public static void main(String[] args) {

        int a = 0;
        Integer b = 0;
        String c = "";

        b.intValue();
        b.intValue();

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

}
