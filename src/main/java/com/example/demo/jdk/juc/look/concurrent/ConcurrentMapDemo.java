package com.example.demo.jdk.juc.look.concurrent;


/**



ConcurrentMap  map的键值对  并发安全的方式读写数据

ConcurrentHashMap（HashTable  枷锁synchroized 对外提供同步方法）
	a.底层数组加链表  默认初始容量16  默认加载因子0.75  2N扩容
	b.异步线程安全的映射 ,引入了分段锁 concurrencyLevel 参数（并发度）进行设置，默认值为 16 ，
	在分段锁基础上又引入了读写锁来提高效率
		1.读锁：允许多个线程读，不允许线程写
		2.写锁：只允许一个线程写，在写的过程中不允许线程读  
	c.jdk 1.8种 ConcurrentHashMap引入CAS算法  保证异步线程安全
	  cas算法 比较和交换  内核硬件支持
	d.jdk1.8中为了提高效率  ConcurrentHashMap 引入红黑树
	  当桶中元素超过8 变成红黑树 不足7  红黑树会扭转成链表  树化的条件（桶的个数>=64 ）
	e.红黑树
		1.红黑树本职 平衡二叉查找树
		2.二叉查找树的特点:左子树小于根节点，右子树大于根节点
		3.红黑树的特点：
			1.所有的节点颜色非红既黑
            2.根节点必须是黑节点
            3.红节点的
            4.
            5.
            6.
            
ConcurrentNavigableMap 
	a.
	b.
	c.


 */



