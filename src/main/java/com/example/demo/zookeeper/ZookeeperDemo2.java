package com.example.demo.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZookeeperDemo2 {

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
	
	
	//监控这个节点的数据  是否被改动
	//@Test
	public void NodeChanged() throws KeeperException, InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		zooKeeper.getData("/log", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getType() == EventType.NodeDataChanged) {
					System.out.println("当前节点节点数据改变");
					countDownLatch.countDown();
				}else if (event.getType() == EventType.NodeDataChanged) {
					System.out.println("节点数据删除");
					countDownLatch.countDown();
				}else if (event.getType() == EventType.NodeCreated) {
					System.out.println("节点数据创建");
					countDownLatch.countDown();
				}else if (event.getType() == EventType.NodeChildrenChanged) {
					System.out.println("子节点数据改变");
					countDownLatch.countDown();
				}

			}
		}, null);

		countDownLatch.await();
	}

}
