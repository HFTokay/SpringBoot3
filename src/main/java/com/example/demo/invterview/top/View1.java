package com.example.demo.invterview.top;

public class View1 {
}

/**
 * 货拉拉社招一面一小时拷打面经
 * 1.简单讲一下JVM，元空间的默认大小是多少20.75M
 * 2.JVM有什么垃圾回收算法  6中貌似
 * 3.垃圾回收是根据什么判断的
 * 4.线程池用过吗，讲一下项目实际怎么用的
 * 5.核心线程数怎么设置，除了CPU核数还有什么因素决定？
 * 6.CorrentHashMap用过吗，底层原理是什么
 * 7.你说了synchronized，说一下还有什么常见的锁
 * 8.这些锁的底层原理是什么
 * 9.Spring的加载机制
 * 10.Bean是怎么注入到容器中的
 * 11.@Configuration和@Component有什么区别
 * 12.mybatis怎么防止sql注入的，底层原理是什么
 * 13.建立一个索引b_a，where a = 1，where b=2 and a=1, where a >1 and b=2这些那些会走索引。为什么不走
 * 14.mysql用的版本是什么，默认的事务隔离机制是什么，能防止什么问题（回答的mysql8，从这里开始已经有点面晕了，默认说的RC，说的幻读和不可重复读）
 * 15.不可重复读，幻读是什么，怎么操作可以防止出现这些操作（说加锁）
 * 16.加锁，加什么锁（说加for update这种）
 * 17.这些锁的底层原理了解吗，通过什么实现（回答MVCC和临建锁）
 * 18.讲一下MVCC
 * 19.用过什么微服务？feign怎么用的（接口声明客户端 降级熔断）
 * 20.负载均衡用过吗（loundbanced 负载均衡）
 *
 */


/**
 * 第一题 答案：
 * 使用Java 8以后，关于元空间的JVM参数有两个：-XX:MetaspaceSize=N和 -XX:MaxMetaspaceSize=N，
 * 对于64位JVM来说，元空间的默认初始大小是20.75MB，
 * 默认的元空间的最大值是无限。MaxMetaspaceSize用于设置metaspace区域的最大值，
 * 这个值可以通过mxbean中的MemoryPoolBean获取到，
 * 如果这个参数没有设置，那么就是通过mxbean拿到的最大值是-1，表示无穷大。
 */