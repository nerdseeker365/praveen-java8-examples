package com.praveen.java8.others;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 
 * @author Praveen Oruganti Life of Stream Element 1. Born(at a spliterator) 2.
 *         Transformed(by intermediate operation) 3. Collected(by terminal
 *         operation) other terminal operations a) Search operations like
 *         allMatch,anyMatch,findAny,findFirst b) Side-effecting operations like
 *         forEach,forEachOrdered c) Reductions
 *
 */

class Person {
	private String name;
	private int age;
	private String city;

	public Person(String name, int age, String city) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getCity() {
		return city;
	}

}

public class StreamExamples {
	public static void main(String args[]) {
		List<String> stringArrayList = Arrays.asList("Lakshmi","Praveen", "Oruganti", "Nani", "Anusha", "Narayana");
		List<String> stringList= new ArrayList<String>(stringArrayList);
	/*	stringList.replaceAll(s->s.toUpperCase());
		stringList.removeIf(s->s.length()>4);
		stringList.forEach(System.out::println);
		stringList.stream()
		          .forEach(System.out::println);
		stringList.parallelStream()
		          .forEach(System.out::println);*/
		System.out.println(
						stringList.stream()
						          .filter(s->s.length()>5)
						          .peek(s->System.out.println("****"+ s))  
						          //.sorted(Collections.reverseOrder())
						          .sorted(Comparator.comparingInt((String s)->s.length())
						                            .thenComparing(Collections.reverseOrder()))
						          .map(s->s.toUpperCase())						          
						          .collect(Collectors.toList()));
		
		// Using an accumulator
		int[] vals = new int[100];
		Arrays.setAll(vals, i -> 1);
		int sum = 0;
		for (int i = 0; i < vals.length; i++) {
			sum += vals[i];
		}
		System.out.println(sum);
		// Avoiding accumulator: using reductions
		OptionalInt sum1 = Arrays.stream(vals)
				                 .reduce((a, b) -> a + b);
		System.out.println(sum1.getAsInt());
		BigDecimal[] bigVals = new BigDecimal[100];
		Arrays.setAll(bigVals, i -> new BigDecimal(i));
		BigDecimal bigDecimalSum = Arrays.stream(bigVals)
				                          .reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(bigDecimalSum);

		// Collectors- supplier,accumulator and combiner
		List<Person> people = new ArrayList<>();
		people.add(new Person("Praveen", 32, "Hyderabad"));
		people.add(new Person("Nani", 32, "Hyderabad"));
		people.add(new Person("OLN", 63, "Tirupati"));
		people.add(new Person("Anusha", 26, "Guntur"));
		people.add(new Person("Lakshmi", 58, "Tirupati"));
		System.out.println(people.stream()
				                 .collect(Collectors.toMap(Person::getCity, Person::getName, (a, b) -> a.concat(b), TreeMap::new)));
		NavigableSet<String> sortedNames = people.stream()
				                                 .map(Person::getName)
				                                 .collect(Collectors.toCollection(TreeSet::new));
		System.out.println(sortedNames);
		// Classification Maps - groupingBy,partitioningBy
		Map<String,List<Person>> peopleByCity=people.stream()
				                                    .collect(Collectors.groupingBy(Person::getCity));
		System.out.println(peopleByCity);		
		Set<String> inhabited=people.stream()
				                    .collect(Collectors.mapping(Person::getCity, Collectors.toSet()));		
		System.out.println(inhabited);		
		Map<String,String> namesByCity=people.stream()
				                             .collect(Collectors.groupingBy(Person::getCity,Collectors.mapping(Person::getName,Collectors.joining("&"))));
		System.out.println(namesByCity);
		
		// Set of people Ages
		
		Set<Integer> ageSet=people.stream().map(Person::getAge).collect(Collectors.toSet());
		System.out.println(ageSet);
		
		//People by Age
		Map<Integer, List<Person>> peoplebyAge=people.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println(peoplebyAge);
		
		// names by Age
		
		Map<Integer,List<String>> namesbyAge=people.stream().collect(Collectors.groupingBy(Person::getAge,Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println(namesbyAge);
		
		// population by Age
		
		Map<Integer,Long> populationbyAge=people.stream().collect(Collectors.groupingBy(Person::getAge,Collectors.counting()));
		System.out.println(populationbyAge);
		
		//cities with More than one occupant
		Set<String> citiesWithMoreThanOneOccuptant=people.stream()
				                                         .collect(Collectors.groupingBy(Person::getCity,Collectors.counting()))
				                                         .entrySet().stream()
				                                         .filter(e->e.getValue()>1)
				                                         .map(e->e.getKey())
				                                         .collect(Collectors.toSet());
		System.out.println(citiesWithMoreThanOneOccuptant);
		// most popular Age
		//Set<Integer> mostPopularAge= people.stream().map(person->person.getAge());
		
	}

}
