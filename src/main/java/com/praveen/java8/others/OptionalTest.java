package com.praveen.java8.others;

import java.util.Optional;

public class OptionalTest {

	public static void main(String args[]) {
		Integer[] numArray= new Integer[5];
		// System.out.println(numArray[3].toString()); // It throws java.lang.NullPointerException
		Optional <Integer> optional = Optional.ofNullable(numArray[3]);
		if(optional.isPresent()) {
			System.out.println(numArray[3].toString());
		}
	}
}
