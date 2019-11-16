package com.praveen.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {

		String sentence = "mango apple mango banana apple mango apple banana mango";
		String[] words = sentence.split(" ");
		System.out.println(Arrays.asList(words).stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

		List<String> fruitList = Arrays.asList("banana", "apple", "mango", "grapes");
		System.out.println(fruitList.stream().flatMap(s -> s.chars().mapToObj(i -> (char)i)).collect(Collectors.toList()));
		//[b, a, n, a, n, a, a, p, p, l, e, m, a, n, g, o, g, r, a, p, e, s]
		
		System.out.println(fruitList.stream().map(String:: toUpperCase).collect(Collectors.toList())); // [BANANA, APPLE, MANGO, GRAPES]
		System.out.println(fruitList.stream().mapToInt(String:: length).boxed().collect(Collectors.toList())); // [6, 5, 5, 6]
		System.out.println(fruitList.stream().map(Function.identity()).collect(Collectors.joining(" | ")));	
		
		

		List<Integer> numList = Arrays.asList(1, 9, 8, 5);
		System.out.println(numList.stream().mapToInt(num->num*5).collect(ArrayList::new,
		ArrayList::add, ArrayList::addAll)); // [5, 45, 40, 25]
		System.out.println(numList.stream().mapToInt(num -> num * 5).boxed().collect(Collectors.toList())); // [5, 45, 40, 25]

		Employee1 e1 = new Employee1("Praveen", 149903L, 34, "Hyderabad");
		Employee1 e2 = new Employee1("Khaja", 250005L, 35, "Bangalore");
		Employee1 e3 = new Employee1("Varma", 26767L, 36, "Singapore");
		Employee1 e4 = new Employee1("Hari", 89778L, 43, "Hyderabad");
		Employee1 e5 = new Employee1("Krishna", 22203L, 38, "SouthAfrica");
		Employee1 e6 = new Employee1("Praveen", 149903L, 34, "UK");

		ArrayList<Employee1> eList = new ArrayList<Employee1>();
		eList.add(e1);
		eList.add(e2);
		eList.add(e3);
		eList.add(e4);
		eList.add(e5);
		eList.add(e6);
		
		System.out.println("Praveen Demo");
		System.out.println(eList.stream()
                .collect(Collectors.groupingBy(Employee1::getEmpName)));
		System.out.println(eList.stream()
                                .collect(Collectors.groupingBy( Employee1::getEmpName  ,
                                		 Collectors.mapping(Employee1::getEmpLocation, Collectors.toList()))));
		
		

		Employee1 result = eList.stream().reduce(new Employee1("", 0L, 0, ""), (emp1, emp2) -> {
			emp1.empAge += emp2.empAge;
			emp1.empName += emp2.empName;
			return emp1;
		});
		System.out.format("name=%s; age=%s\n", result.empName, result.empAge);

		eList.stream().filter(e -> e.getEmpLocation().equalsIgnoreCase("Hyderabad")).forEach(System.out::println);

		eList.stream().filter(new Predicate<Employee1>() {
			public boolean test(Employee1 e) {
				return "Hyderabad".equalsIgnoreCase(e.getEmpLocation());
			}
		}).forEach(System.out::println);

		System.out.println(eList.stream().filter(e -> e.getEmpLocation().equalsIgnoreCase("Hyderabad"))
				.sorted((Employee1 emp1, Employee1 emp2) -> emp1.getEmpName().compareTo(emp2.getEmpName())).findFirst()
				.get());

		System.out.println(eList.stream().filter(e -> e.getEmpName().equalsIgnoreCase("praveen"))
				.map(Employee1::getEmpAge).findAny().orElse(0));

		eList.stream().reduce((emp1, emp2) -> emp1.getEmpAge() > emp2.getEmpAge() ? e1 : e2)
				.ifPresent(System.out::println);

		System.out.println(eList.stream().mapToInt(e -> e.getEmpAge()).sum());
		Integer ageSum = eList.stream().reduce(0, (sum, emp) -> sum += emp.getEmpAge(), (sum1, sum2) -> sum1 + sum2);

		System.out.println(ageSum);

		List<List<Integer>> listOfListOfNumber = new ArrayList<>();
		listOfListOfNumber.add(Arrays.asList(2, 4));
		listOfListOfNumber.add(Arrays.asList(3, 9));
		listOfListOfNumber.add(Arrays.asList(4, 16));
		System.out.println(listOfListOfNumber);
		System.out.println(listOfListOfNumber.stream().flatMap(list -> list.stream()).collect(Collectors.toList()));

		System.out.println(eList.stream().map(Employee1::getEmpName).collect(Collectors.joining("|")));

		IntSummaryStatistics statistics = eList.stream().mapToInt(Employee1::getEmpAge).summaryStatistics();

		System.out.println(statistics.getCount());
		System.out.println(statistics.getSum());
		System.out.println(statistics.getMin());
		System.out.println(statistics.getMax());
		System.out.println(statistics.getAverage());

		Map<Boolean, List<Employee1>> partition = eList.stream()
				.collect(Collectors.partitioningBy(e -> e.getEmpLocation().equals("Hyderabad")));
		System.out.println("Employee1s working in Hyderabad Location " + partition.get(true));
		System.out.println("Employee1s working in other Location " + partition.get(false));

		Map<String, List<Employee1>> groupBy = eList.stream().collect(Collectors.groupingBy(Employee1::getEmpLocation));
		System.out.println(groupBy);

		Map<String, Set<String>> mappingBy = eList.stream().collect(Collectors.groupingBy(Employee1::getEmpLocation,
				Collectors.mapping(Employee1::getEmpName, Collectors.toSet())));
		System.out.println(mappingBy);

//		
//		Arrays.asList("a1", "a2", "b1", "c2", "c1")
//	    .parallelStream()
//	    .filter(s -> {
//	        System.out.format("filter: %s [%s]\n",
//	            s, Thread.currentThread().getName());
//	        return true;
//	    })
//	    .map(s -> {
//	        System.out.format("map: %s [%s]\n",
//	            s, Thread.currentThread().getName());
//	        return s.toUpperCase();
//	    })
//	    .forEach(s -> System.out.format("forEach: %s [%s]\n",
//	        s, Thread.currentThread().getName()));

		Arrays.asList("a1", "a2", "b1", "c2", "c1").parallelStream().filter(s -> {
			System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		}).sorted((s1, s2) -> {
			System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
			return s1.compareTo(s2);
		}).forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));

		Map<String, Employee1> empMap = new HashMap<>();
		empMap.put("1", new Employee1("Praveen", 149903L, 34, "Hyderabad"));
		empMap.put("2", new Employee1("Prasad", 149904L, 35, "Hyderabad"));
		empMap.put("3", new Employee1("Varma", 149905L, 36, "Bangalore"));

		System.out.println(empMap.entrySet().stream().map(emp -> emp.getValue().empAge)
				.collect(Collectors.minBy(Comparator.naturalOrder())));

		System.out.println(empMap.entrySet().stream().map(emp -> emp.getValue().empAge)
				.collect(Collectors.maxBy(Comparator.naturalOrder())));

		List<String> empNames = Arrays.asList("Sharma", "Praveen", "Varma", "Krishna");

		List<String> filteredList = empNames.stream()
				.filter(emp -> empMap.entrySet().stream().allMatch(entry -> !entry.getValue().getEmpName().equals(emp)))
				.collect(Collectors.toList());
		System.out.println(filteredList);

		// For example, i have an array list which has duplicate elements.
		List<Integer> intList = Arrays.asList(1, 2, 3, 2, 6, 7, 5, 3, 1);
		System.out.println(intList); // [1, 2, 3, 2, 6, 7, 5, 3, 1]
		// WAP to Remove the duplicates and print the list using stream api
		System.out.println(intList.stream().distinct().collect(Collectors.toList())); // [1, 2, 3, 6, 7, 5]
		// WAP to Print the duplicated list using stream api.
		System.out.println(
				intList.stream().filter(i -> Collections.frequency(intList, i) > 1).collect(Collectors.toSet())); // [1,
																													// 2,
																													// 3]
		// WAP to Print the non duplicated list using stream api.
		System.out.println(
				intList.stream().filter(i -> Collections.frequency(intList, i) == 1).collect(Collectors.toList())); // [6,
																													// 7,
																													// 5]
		// WAP to Print the frequency of elements present in the given list using stream
		// api.
		System.out.println(intList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))); // {1=2,
																															// 2=2,
																															// 3=2,
																															// 5=1,
																															// 6=1,
																															// 7=1}

		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());

		Stream.of(1, 3, 5, 4, 2).sorted().forEach(System.out::println);
		Stream.of(1, 3, 5, 4, 2).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		Stream.of("A", "C", "E", "B", "D").sorted().forEach(System.out::println);
		Stream.of("A", "C", "E", "B", "D").sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println(eList.stream().filter(e -> e.getEmpLocation().equalsIgnoreCase("Hyderabad"))
				.sorted((emp1, emp2) -> emp1.getEmpName().compareTo(emp2.getEmpName())).collect(Collectors.toList()));

		Stream.of(1, 3, 5, 4, 2).limit(3).forEach(System.out::println);
		Stream.of(1, 3, 5, 4, 2).skip(2).forEach(System.out::println);

		String str = "Praveen Oruganti";
		System.out.println("****forEach without using parallel****");
		str.chars().forEach(s -> System.out.print((char) s)); // Praveen Oruganti
		System.out.println("\n****forEach with using parallel****");
		str.chars().parallel().forEach(s -> System.out.print((char) s)); // ugrOtinaee navrP
		System.out.println("\n****forEachOrdered with using parallel****");
		str.chars().parallel().forEachOrdered(s -> System.out.print((char) s)); // Praveen Oruganti
		System.out.println("\n");
		
		Stream.of(10, 20, 22, 12, 14).reduce((x, y) -> x + y).ifPresent(System.out::println); // 78
		Stream.of(10, 20, 22, 12, 14).reduce(Integer::sum).ifPresent(System.out::println);// 78
		Stream.of("java", "c", "c#", "python").reduce((x, y) -> x + " | " + y).ifPresent(System.out::println); // java | c | c# | python
		
		
		System.out.println(Stream.of(10,20,22,12,14).reduce(1000, Integer::sum)); // 1078
        System.out.println(Stream.of(10,20,22,12,14).reduce(1000, (x,y)->x+y)); // 1078
        System.out.println( Stream.of("java", "c", "c#", "python").reduce("Languages:", (x,y)->x+" | "+y)); // Languages: | java | c | c# | python
        
        
        Integer arrSum = Stream.of(10,20,22,12,14).parallel().reduce(1000, (x,y)->x+y, (p,q)->{
            System.out.println("combiner called");
            return p+q;
        });
        System.out.println(arrSum);/* combiner called
       								combiner called
       								combiner called
       								combiner called
       								5078 */
        System.out.println(IntStream.of(1, 2, 3, 4).anyMatch(i->i>=3)); // true
        System.out.println(IntStream.of(1, 0, 1, 2).noneMatch(i->i>=3)); // true
        System.out.println(IntStream.of(3, 4, 5, 6).allMatch(i->i>=3)); // true
        
        System.out.println(IntStream.of(1, 2, 3, 4).count()); //4
        
        System.out.println(IntStream.of(1, 2, 3, 4).sum()); // 10
        
        System.out.println(IntStream.of(1, 2, 3, 4).average().getAsDouble()); // 2.5
        
        System.out.println(IntStream.of(1, 2, 3, 4).min().getAsInt()); // 1
        System.out.println(IntStream.of(1, 2, 3, 4).max().getAsInt()); // 4
        
        
        
        
        
	}
}

class Employee1 {
	public String empName;
	public Long empId;
	public String empLocation;
	public int empAge;

	public Employee1(String empName, Long empId, int empAge, String empLocation) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.empLocation = empLocation;
		this.empAge = empAge;
	}

	public String getEmpName() {
		return empName;
	}

	public Long getEmpId() {
		return empId;
	}

	public String getEmpLocation() {
		return empLocation;
	}

	public int getEmpAge() {
		return empAge;
	}

	@Override
	public String toString() {
		return "Employee1 [empName=" + empName + ", empId=" + empId + ", empAge=" + empAge + ", empLocation="
				+ empLocation + "]";
	}

}
