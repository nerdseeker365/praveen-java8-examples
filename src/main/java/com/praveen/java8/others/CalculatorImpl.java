package com.praveen.java8.others;

interface Calculator {
	int add(int num1, int num2);
}

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	public static void main(String[] args) {
		
		/* Without Lambdas */
		CalculatorImpl ci = new CalculatorImpl();
		System.out.println(ci.add(5, 6));
		
		/* With Lambdas */
		Calculator ci1= (int num1,int num2) -> (num1+num2);
		System.out.println(ci1.add(5, 6));

	}

}
