package com.praveen.java8;

@FunctionalInterface
interface MethodReference {
	void display();
}

public class StaticMethodReferenceDemo {

	static void display() {
		System.out.println("display");
	}

	public static void main(String args[]) {

		/* With Methodreference */
		MethodReference methodReference = StaticMethodReferenceDemo::display;
		methodReference.display();

		/* With Lambda */
		MethodReference methodReferenceLambda = () -> StaticMethodReferenceDemo.display();
		methodReferenceLambda.display();
	}

}
