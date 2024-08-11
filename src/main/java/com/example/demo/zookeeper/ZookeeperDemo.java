package com.example.demo.zookeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperDemo {

	ZooKeeper zooKeeper;

	//@Before
	public void connect() throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		zooKeeper = new ZooKeeper("192.168.34.7:2181", 5000, new Watcher() {
			public void process(WatchedEvent event) {
				if (event.getState() == KeeperState.SyncConnected) {
					System.out.println("链接成功");
					countDownLatch.countDown();
				} else {
					System.out.println("链接失败");
					countDownLatch.countDown();
				}

			}
		});

		countDownLatch.await();
		System.out.println(" test 监控线程完成");

	}

	// 创建节点
	//@Test
	public void createNode() throws Exception {
		// path -路径
		// data - 数据
		// acl - acl策略 Ids枚举类
		// createMode - 创建模式-节点类型 4中 临时-持久 顺序-不顺序
		// 临时顺序 临时不顺序 持久顺序 持久不顺序

		String create = zooKeeper.create("/log", "log server".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        //返回值  节点的路径   为了持久顺序
	}
	
	
	
	//@Test
	public void setDate() throws Exception {
		//path  -路径
		//data  -数据
		//version  -数据版本
		//修改数据的时候  会自动检验version是否一致  一致才会修改
		//如果强制修改  那version = -1  强制修改
		Stat setData = zooKeeper.setData("/log", "log---".getBytes(), 0);
		
		//Stat zk 数据事务对象 
	}
	
	
	//@Test
	public void getData() throws Exception {
		//path - 路径
		//watch, 
		//stat  -节点信息
		
		//Stat stat = new Stat();
		//stat.setAversion(1); .stat..
		
		byte[] data = zooKeeper.getData("/log", null, null);
		System.out.println(new String(data));
		
	}
	
	
	//@Test
	public void deleteNode() throws Exception {
		zooKeeper.delete("/news00000000001", -1);
	}
	
	
	//@Test
	public void getChildren() throws Exception {
		List<String> children = zooKeeper.getChildren("/", null);
		//获取到所有子节点  遍历获取
	}
	
	//@Test
	public void exists() throws Exception {
		Stat exists = zooKeeper.exists("/text", null);
		//节点存在   不存在返回null
		
		if(exists == null) {
			System.out.println("节点不存在");
		}else {
			System.out.println("节点存在");
		}
	}

} 
