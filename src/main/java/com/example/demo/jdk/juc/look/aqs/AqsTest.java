package com.example.demo.jdk.juc.look.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class AqsTest extends AbstractQueuedSynchronizer {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(false);
        reentrantLock.lock();
        reentrantLock.unlock();
    }


/**
 * 可重入锁加锁流程 new ReentrantLock(false);
 * Reentrantlock.lock()--> NonfairSync,lock()-->Aqs.acquire(1)--> Nonfairsync.tryAcquire(1)-->Sync.nonfairTry
 * 1.CAS获取锁，如果没有线程占用锁(state==0)，加锁成功并记录当前线程是有锁线程(两次操作)
 * 2.如果 state不为0 说明占用了锁  判断当前线程是否有锁线程  有锁就重入 state = state + 1
 * 3 否则加锁失败   入队等待
 *
 * 解锁流程
 * Reentrantlock.unlock() --> AQS.release(1) -->  Sync.tryRelease(1) -->
 * 1. 判断当前线程是否是有锁线程   不是 就抛出异常  throw new IllegalMonitorStateException();
 *    if(getExclusiveOwnerThread() != Thread.currentThread())
 * 2. 对 state -1 的值进行判断 ，判单是否为0，为0则解锁成功 返回true
 * 3. 如果 state-1 的值不为0  返回false
 *
 *
 *
 *
 *
 * 实现独占锁
 * tryAcquire(int arg)	尝试以独占模式获取资源（如锁），成功返回 true，失败返回 false。
 * tryRelease(int arg)	尝试释放独占模式的资源，释放成功返回 true。
 *
 * 实现排他锁
 * tryAcquireShared(int arg)	尝试以共享模式获取资源（如信号量），返回值 >=0 表示成功，<0 表示失败。
 * tryReleaseShared(int arg)	尝试释放共享模式的资源，释放后可能需要唤醒后续线程，返回 true 表示需要唤醒。
 *
 * isHeldExclusively()	判断当前线程是否独占资源（用于条件变量的实现）
 *
 *
 * AQS 的核心逻辑  它的核心思想是通过一个 共享的 volatile 状态变量（state） 和一个 FIFO 线程等待队列 来实现线程的同步与排队机制。
 * 1. 同步状态（state）
 * 用途：表示资源的可用数量或状态（例如锁的持有计数、信号量的许可数等）。
 *
 * 操作：通过 getState(), setState(), compareAndSetState() 方法原子性地修改状态。
 *
 * 2. 等待队列（CLH 队列变种）
 * 结构：一个双向链表，每个节点（Node）代表一个等待线程。
 *
 * 节点状态：
 * CANCELLED（1）：线程已取消等待（如超时或中断）。
 * SIGNAL（-1）：当前节点的后继节点需要被唤醒。
 * CONDITION（-2）：线程在条件队列中等待。
 * PROPAGATE（-3）：共享模式下传播唤醒信号。
 *
 */


}
