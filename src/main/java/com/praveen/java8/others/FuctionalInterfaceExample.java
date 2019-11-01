package com.praveen.java8.others;

@FunctionalInterface
public interface FuctionalInterfaceExample {

	void m1();

	default void m2() {
		System.out.println("Default method1");
	}

	default void m3() {
		System.out.println("Default method2");
	}

	static void m4() {
		System.out.println("Static method1");
	}

}
