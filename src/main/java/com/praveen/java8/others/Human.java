package com.praveen.java8.others;

public class Human {

	private String name;
	private int age;

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + "]";
	}

	

}
