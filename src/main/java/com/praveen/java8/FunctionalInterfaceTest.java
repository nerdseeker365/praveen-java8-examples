package com.praveen.java8;

public class FunctionalInterfaceTest implements Interface1, Interface2 {

	@Override
	public void print() {
		System.out.println("Print");
	}

	@Override
	public void show() {
		System.out.println("Show");
	}

	@Override
	public void display() {
		System.out.println("Class Display");
	}

	public static void main(String[] args) {
		Interface1.display1();
		Interface2.display1();
	}

}

@FunctionalInterface
interface Interface1 {
	void show();

	default void display() {
		System.out.println("Interface1 Display");
	}

	static void display1() {
		System.out.println("Interface1 Display1");
	}
}

@FunctionalInterface
interface Interface2 {
	void print();

	default void display() {
		System.out.println("Interface2 Display");
	}

	static void display1() {
		System.out.println("Interface2 Display1");
	}
}