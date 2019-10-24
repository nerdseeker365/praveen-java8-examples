package com.praveen.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {
	
	public static void main(String[] args) {
		
		String sentence = "mango apple mango banana apple mango apple banana mango";
		String[] words= sentence.split(" ");
		System.out.println(Arrays.asList(words).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
		
		List<String> fruitList = Arrays.asList("banana", "apple", "mango", "grapes");
		System.out.println(fruitList.stream().map(Function.identity()).collect(Collectors.joining(" | ")));
		
		List<Integer> numList = Arrays.asList(1, 9, 8, 5);
		//System.out.println(numList.stream().mapToInt(num->num*5).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		System.out.println(numList.stream().mapToInt(num->num*5).boxed().collect(Collectors.toList()));
		
		
	}

}
