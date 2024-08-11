package com.example.demo.jdk.object;



public class ReferenceDemo {
	public static void main(String[] args) throws Exception {

		
		SoftReference();
	}
	
	
	//强引用 不会被回收
	public static void StrongReference() throws InterruptedException {
		Object object = new Object();
		System.gc();
		Thread.sleep(2000);
		System.out.println("强引用:"+object);
		
	}
	
	//
	public static void WeakReference() throws Exception {
		Object object = new Object();
		java.lang.ref.WeakReference<Object> weakReference = new java.lang.ref.WeakReference<>(object);
		System.gc();
		Thread.sleep(2000);
		System.out.println("弱引用:"+object);
		
	}
	
	
	public static void SoftReference() throws Exception {
		Object object = new Object();
		java.lang.ref.SoftReference<Object> softReference = new java.lang.ref.SoftReference<>(object);
		
		System.gc();
		Thread.sleep(2000);
		System.out.println("软引用:"+object);
		
	}

}
