package com.praveen.javautilitiesbypraveen.java8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class Person {
	private final String name;
	private final int age;

	public Person(final String theName, final int theAge) {
		name = theName;
		age = theAge;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int ageDifference(final Person other) {
		return age - other.age;
	}

	public String toString() {
		return String.format("%s - %d", name, age);
	}
}

public class LambdaAllExamples {

	public static void main(String... args) throws IOException {

		String[] s1 = new String[] { "a", "b", "c" };
		String[] s2 = new String[] { "d", "e", "f" };
		String[] s3 = new String[] { "g", "h", "i" };

		// join object type array
		String[] result = Stream.of(s1, s2, s3).flatMap(Stream::of).toArray(String[]::new);
		System.out.println(Arrays.toString(result));

		int[] int1 = new int[] { 1, 2, 3 };
		int[] int2 = new int[] { 4, 5, 6 };
		int[] int3 = new int[] { 7, 8, 9 };

		// join 2 primitive type array
		int[] result2 = IntStream.concat(Arrays.stream(int1), Arrays.stream(int2)).toArray();

		// join 3 primitive type array, any better idea?
		int[] result3 = IntStream
				.concat(Arrays.stream(int1), IntStream.concat(Arrays.stream(int2), Arrays.stream(int3))).toArray();

		System.out.println(Arrays.toString(result2));

		System.out.println(Arrays.toString(result3));

		String password = "password123";

		password.chars() // IntStream
				.mapToObj(x -> (char) x)// Stream<Character>
				.forEach(System.out::println);

		int[] number = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		List<Integer> list = Arrays.stream(number).boxed().collect(Collectors.toList());
		System.out.println("list : " + list);

		// Java 8
		boolean result4 = IntStream.of(number).anyMatch(x -> x == 4);

		if (result4) {
			System.out.println("Hello 4");
		} else {
			System.out.println("Where is number 4?");
		}

		long[] lNumber = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		boolean result5 = LongStream.of(lNumber).anyMatch(x -> x == 10);

		if (result5) {
			System.out.println("Hello 10");
		} else {
			System.out.println("Where is number 10?");
		}

		String[] alphabet = new String[] { "A", "B", "C" };
		boolean result6 = Arrays.stream(alphabet).anyMatch("A"::equals);
		if (result6) {
			System.out.println("Hello A");
		}
		List<String> friends = new ArrayList<String>();
		friends.add("Praveen");
		friends.add("Khaja");
		friends.add("Shiraz");
		friends.add("Asif");
		friends.add("Kiran");

		System.out.println("Example Legacy style of coding");
		for (String name : friends) {
			System.out.println(name);
		}
		System.out.println("Example functional style of coding");
		friends.forEach(System.out::println);

		System.out.println("Example Legacy style of coding");
		final List<String> uppercaseNames = new ArrayList<String>();
		for (String name : friends) {
			uppercaseNames.add(name.toUpperCase());
		}
		System.out.println(uppercaseNames);

		System.out.println("Example functional style of coding");
		final List<String> uppercaseNamesNew = new ArrayList<String>();
		friends.forEach(name -> uppercaseNamesNew.add(name.toUpperCase()));
		System.out.println(uppercaseNamesNew);

		System.out.println("Example Lambda expression");
		friends.stream().map(name -> name.toUpperCase()).forEach(name -> System.out.println(name));
		friends.stream().map(name -> name.length()).forEach(count -> System.out.println(count));

		System.out.println("Example Method references");
		friends.stream().map(String::toUpperCase).forEach(name -> System.out.println(name));

		System.out.println("Example Legacy style of coding");
		final List<String> startsWithK = new ArrayList<String>();
		for (String name : friends) {
			if (name.startsWith("K")) {
				startsWithK.add(name);
			}
		}
		System.out.println(startsWithK);

		System.out.println("Example Lambda expression");
		final List<String> startsWithKNew = friends.stream().filter(name -> name.startsWith("K")).collect(toList());
		System.out.println(startsWithKNew);
		System.out.println(String.format("Found %d names", startsWithKNew.size()));
		System.out.println(friends.stream().filter(name -> name.startsWith("K")).count());
		final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);
		System.out.println(friends.stream().filter(startsWithLetter.apply("K")).count());

		final Optional<String> foundName = friends.stream().filter(name -> name.startsWith("K")).findFirst();
		foundName.ifPresent(name -> System.out.println("Hello " + name));

		System.out.println(
				"Total number of characters in all names: " + friends.stream().mapToInt(name -> name.length()).sum());

		final Optional<String> aLongName = friends.stream()
				.reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
		aLongName.ifPresent(name -> System.out.println(String.format("A longest name: %s", name)));

		System.out.println(String.join(", ", friends));

		System.out.println(friends.stream().map(String::toUpperCase).collect(joining(", ")));

		final String str = "Praveen";
		str.chars().forEach(System.out::println);
		str.chars().mapToObj(ch -> Character.valueOf((char) ch)).forEach(System.out::println);

		System.out.println("Sorting with a Comparator");
		final List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
				new Person("Jane", 21), new Person("Greg", 35));

		List<Person> ascendingAge = people.stream().sorted((person1, person2) -> person1.ageDifference(person2))
				.collect(toList());
		printPeople("Sorted in ascending order of age: ", ascendingAge);

		Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
		Comparator<Person> compareDescending = compareAscending.reversed();
		printPeople("Sorted in ascending order of age: ", people.stream().sorted(compareAscending).collect(toList()));

		printPeople("Sorted in descending order of age: ", people.stream().sorted(compareDescending).collect(toList()));

		printPeople("Sorted in ascending order of name: ", people.stream()
				.sorted((person1, person2) -> person1.getName().compareTo(person2.getName())).collect(toList()));

		people.stream().min(Person::ageDifference).ifPresent(youngest -> System.out.println("Youngest: " + youngest));

		people.stream().max(Person::ageDifference).ifPresent(eldest -> System.out.println("Eldest: " + eldest));

		final Function<Person, Integer> byAge = person -> person.getAge();
		final Function<Person, String> byTheirName = person -> person.getName();
		printPeople("Sorted in ascending order of age and name: ",
				people.stream().sorted(comparing(byAge).thenComparing(byTheirName)).collect(toList()));

		List<Person> olderThan20 = people.stream().filter(person -> person.getAge() > 20).collect(toList());
		System.out.println("People older than 20: " + olderThan20);

		Map<Integer, List<Person>> peopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println("People grouped by age: " + peopleByAge);

		Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
				.collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, toList())));
		System.out.println("People grouped by age: " + nameOfPeopleByAge);

		Comparator<Person> byAgeNew = Comparator.comparing(Person::getAge);
		Map<Character, Optional<Person>> oldestPersonInEachAlphabet = people.stream().collect(Collectors
				.groupingBy(person -> person.getName().charAt(0), Collectors.reducing(BinaryOperator.maxBy(byAgeNew))));
		System.out.println("Oldest person in each alphabet:");
		System.out.println(oldestPersonInEachAlphabet);

		System.out.println("Listing All Files in a Directory");
		Files.list(Paths.get(".")).forEach(System.out::println);

		Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);

		Files.newDirectoryStream(Paths.get("."),
				path -> path.toString().endsWith(".java")).forEach(System.out::println);
		List<File> files = Stream.of(new File(".").listFiles())
				.flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
				.collect(toList());
		System.out.println("Count: " + files.size());
		
		Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");
        System.out.println("\n1. Export Map Key to List...");
        List<Integer> result10 = map.keySet().stream()
                .collect(Collectors.toList());
        result10.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");
        List<String> result20 = map.values().stream()
                .collect(Collectors.toList());
        result20.forEach(System.out::println);
        System.out.println("\n3. Export Map Value to List..., say no to banana");
        List<String> result30 = map.values().stream().filter(x -> !"banana".equalsIgnoreCase(x)).collect(Collectors.toList());
        result30.forEach(System.out::println);
        System.out.println("\n4. split a map into 2 List");
        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                //sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        resultSortedKey.forEach(System.out::println);
        resultValues.forEach(System.out::println);

        
        
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);
        
        System.out.println("Original...");
        System.out.println(unsortMap);
        
     // sort by keys, a,b,c..., and return a new LinkedHashMap
        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.
        Map<String, Integer> resultSortedKeys = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        System.out.println(resultSortedKeys);
        
      //sort by values, and reserve it, 10,9,8,7,6...
        Map<String, Integer> resultSortedValues = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
         System.out.println(resultSortedValues);
         
         // passing behaviours into the methods 
         List<Integer> numbers= Arrays.asList(1,2,6,7,7);
       //sum of all numbers
         sumWithCondition(numbers, n -> true);
         //sum of all even numbers
         sumWithCondition(numbers, i -> i%2==0);
         //sum of all numbers greater than 5
         sumWithCondition(numbers, i -> i>5);

	}

	public static void printPeople(final String message, final List<Person> people) {
		System.out.println(message);
		people.forEach(System.out::println);
	}
	
	public static boolean isPrimeTraditional(int number) {		
		if(number < 2) return false;
		for(int i=2; i<number; i++){
			if(number % i == 0) return false;
		}
		return true;
	}
	
	public static boolean isPrimeDeclarative(int number) {		
		return number > 1
				&& IntStream.range(2, number).noneMatch(
						index -> number % index == 0);
	}
	
	public static boolean isPrimeUsingLambdas(int number) {
		IntPredicate isDivisible = index -> number % index == 0;
		
		return number > 1
				&& IntStream.range(2, number).noneMatch(
						isDivisible);
	}
	
	public static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {
	    return numbers.parallelStream()
	    		.filter(predicate)
	    		.mapToInt(i -> i)
	    		.sum();
	}

}
