package com.praveen.java8.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class LambdasSorting {

	public static void main(String args[]) {
		List<Human> humanList= new ArrayList<>();
		humanList.add(new Human("Anusha",26));
		humanList.add(new Human("Praveen",32));
		humanList.add(new Human("Nani",33));
		humanList.add(new Human("Vipin",34));
		humanList.add(new Human("Praveen",34));
		
		humanList.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
		System.out.println(humanList);
		
		
		
	}
}
