package com.praveen.java8;

@FunctionalInterface
interface MethodReferenceNS {
	void display();
}

public class NonStaticMethodReferenceDemo {

	void display() {
		System.out.println("display");
	}

	public static void main(String args[]) {

		NonStaticMethodReferenceDemo obj = new NonStaticMethodReferenceDemo();

		/* With Methodreference */
		MethodReferenceNS methodReference = obj::display;
		methodReference.display();

		/* With Lambda */
		MethodReferenceNS methodReferenceLambda = () -> obj.display();
		methodReferenceLambda.display();
	}

}
