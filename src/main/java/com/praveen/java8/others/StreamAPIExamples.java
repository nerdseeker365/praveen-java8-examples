package com.praveen.java8.others;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPIExamples {
	public static void main(String[] args) throws IOException {
		// 1. Integer Stream
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println();

		// 2. Integer Stream with skip
		IntStream.range(1, 10).skip(5).forEach(x -> System.out.println(x));
		System.out.println();

		// 3. Integer Stream with sum
		System.out.println(IntStream.range(1, 5).sum());
		System.out.println();

		// 4. Stream.of, sorted and findFirst
		Stream.of("Praveen", "Ashish", "Rahul").sorted().findFirst().ifPresent(System.out::println);

		// 5. Stream from Array, sort, filter and print
		String[] names = { "Praveen", "Ashish", "Kaushal", "Rahul", "Calvin", "Raju", "Venkat", "Krishna", "Chandra" };
		Arrays.stream(names) // same as Stream.of(names)
				.filter(x -> x.startsWith("S")).sorted().forEach(System.out::println);

		// 6. average of squares of an int array
		Arrays.stream(new int[] { 2, 4, 6, 8, 10 }).map(x -> x * x).average().ifPresent(System.out::println);

		// 7. Stream from List, filter and print
		List<String> people = Arrays.asList("Praveen", "Kaushal", "Ashish", "Calvin", "Venkat", "Satya", "Nageshwar", "Dasta");
		people.stream().map(String::toLowerCase).filter(x -> x.startsWith("a")).forEach(System.out::println);

		// 8. Stream rows from text file, sort, filter, and print
		Stream<String> bands = Files.lines(Paths.get("bands.txt"));
		bands.sorted().filter(x -> x.length() > 13).forEach(System.out::println);
		bands.close();

		// 9. Stream rows from text file and save to List
		List<String> bands2 = Files.lines(Paths.get("bands.txt")).filter(x -> x.contains("jit"))
				.collect(Collectors.toList());
		bands2.forEach(x -> System.out.println(x));

		// 10. Stream rows from CSV file and count
		Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
		int rowCount = (int) rows1.map(x -> x.split(",")).filter(x -> x.length == 3).count();
		System.out.println(rowCount + " rows.");
		rows1.close();

		// 11. Stream rows from  file, parse data from rows
		Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
		rows2.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[1]) > 15)
				.forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
		rows2.close();

		// 12. Stream rows from CSV file, store fields in HashMap
		Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
		Map<String, Integer> map = new HashMap<>();
		map = rows3.map(x -> x.split(",")).filter(x -> x.length == 3).filter(x -> Integer.parseInt(x[1]) > 15)
				.collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
		rows3.close();
		for (String key : map.keySet()) {
			System.out.println(key + "  " + map.get(key));
		}

		// 13. Reduction - sum
		double total = Stream.of(7.3, 1.5, 4.8).reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println("Total = " + total);

		// 14. Reduction - summary statistics
		IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10).summaryStatistics();
		System.out.println(summary);
		
		// 15. Print only even numbers 
		List<Integer> al1= new ArrayList<Integer>();
		al1.add(5);
		al1.add(29);
		al1.add(10);
		al1.add(49);
		al1.add(18);
		al1.add(2);
		System.out.println(al1);
		al1.stream().filter(num->num%2==0).sorted().forEach(num->System.out.println(num));
		List<Integer> al2=al1.stream().filter(num->num%2==0).sorted().collect(Collectors.toList());
		System.out.println(al2);
		al2.stream().map(num->num*5).forEach(num->System.out.println(num));
		
		//16. find students whose score >= 70
		
		List<Student> listStudents = new ArrayList<>();
		 
		listStudents.add(new Student("Praveen", 82));
		listStudents.add(new Student("Prasad", 90));
		listStudents.add(new Student("Rahul", 67));
		listStudents.add(new Student("Kaushal", 80));
		listStudents.add(new Student("Kalki", 55));
		listStudents.add(new Student("Madhu", 49));
		listStudents.add(new Student("Ravi", 88));
		listStudents.add(new Student("Pradeep", 98));
		listStudents.add(new Student("Kumar", 66));
		listStudents.add(new Student("John", 52));
		
		List<Student> listGoodStudents = listStudents.stream()
                .filter(s -> s.getScore() >= 70)
                .collect(Collectors.toList());
		listGoodStudents.stream().forEach(System.out::println);
		
		// 17.calculate average score of all students
		
	    double average = listStudents.stream()
		            .mapToInt(s -> s.getScore())
		            .average().getAsDouble();
		 
		System.out.println("Average score: " + average);
		
		// 18.top 3 Students by Score
		listStudents.stream()
                .filter(s -> s.getScore() >= 70)
                .sorted()
                .limit(3)
                .forEach(System.out::println);
		
		// 19. get all student names into another list
		
		List<String> ls=listStudents.stream().map(s->s.getName()).sorted().collect(Collectors.toList());
		System.out.println(ls);
		
		// 20. sum of all student scores
		System.out.println(listStudents.stream().mapToInt(s->s.getScore()).sum());
		System.out.println(listStudents.stream().map(s->s.getScore()).reduce(0, (x,y)->(x+y)).intValue());
		
		// 21. check whether an element exist in the given stream
		
		boolean elementCheck =
				listStudents
			        .stream()
			        .anyMatch((s) -> s.getName().equals("Praveen"));
	   
		System.out.println(elementCheck);     
	    
	    boolean allStartsWithP =
	    		listStudents
	    	        .stream()
	    	        .allMatch((s) -> s.getName().startsWith("P"));
	    
	    System.out.println(allStartsWithP);   
	    
	    boolean noneStartsWithZ =
	    		listStudents
	    	        .stream()
	    	        .noneMatch((s) -> s.getName().startsWith("Z"));
	   
	    System.out.println(noneStartsWithZ); 
	    
	    // 22. Number of elements in stream
	    
	    long startsWithP =
	    		listStudents
	    	        .stream()
	    	        .filter((s) -> s.getName().startsWith("P"))
	    	        .count();
	    
	    System.out.println(startsWithP);
	    
	}
}
