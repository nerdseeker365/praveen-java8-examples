package com.praveen.java8;

import java.util.Arrays;

public class StreamExample {
	
	public static void main(String[] args) {
	System.out.println(Arrays.asList(1, 2, 3).stream() 			
		.map(s -> String.valueOf(s).charAt(0)).findFirst());
	}

}
