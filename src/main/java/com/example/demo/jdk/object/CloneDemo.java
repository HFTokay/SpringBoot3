package com.example.demo.jdk.object;

import java.util.concurrent.Callable;

public class CloneDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		
		PersonClone personClone = new PersonClone("AAA", 12);
		
		Object clone2 =personClone.clone();
		
		//浅拷贝只复制对象的引用而不复制引用指向的对象，
		//深拷贝不仅复制对象的引用，而且复制引用指向的所有对象
		
		//使用场景： 浅拷贝  修改后 全部修改     深拷贝自己维护自己的单独
		
		System.out.println(personClone); //data.structure.PersonClone@15db9742
		System.out.println(clone2);//data.structure.PersonClone@6d06d69c
	
		
	}

}



class PersonClone implements Cloneable {
	private String name;
	private Integer age;
	public PersonClone(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	
	//默认 浅拷贝
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	//深拷贝  new
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return new PersonClone(name,age) ;
//	}

}
