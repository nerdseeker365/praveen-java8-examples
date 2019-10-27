package com.praveen.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {

	public static void main(String[] args) {

		String sentence = "mango apple mango banana apple mango apple banana mango";
		String[] words = sentence.split(" ");
		System.out.println(Arrays.asList(words).stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

		List<String> fruitList = Arrays.asList("banana", "apple", "mango", "grapes");
		System.out.println(fruitList.stream().map(Function.identity()).collect(Collectors.joining(" | ")));

		List<Integer> numList = Arrays.asList(1, 9, 8, 5);
		// System.out.println(numList.stream().mapToInt(num->num*5).collect(ArrayList::new,
		// ArrayList::add, ArrayList::addAll));
		System.out.println(numList.stream().mapToInt(num -> num * 5).boxed().collect(Collectors.toList()));

		Employee1 e1 = new Employee1("Praveen", 149903L, 34, "Hyderabad");
		Employee1 e2 = new Employee1("Khaja", 250005L, 35, "Bangalore");
		Employee1 e3 = new Employee1("Varma", 26767L, 36, "Singapore");
		Employee1 e4 = new Employee1("Hari", 89778L, 43, "Hyderabad");
		Employee1 e5 = new Employee1("Krishna", 22203L, 38, "SouthAfrica");

		ArrayList<Employee1> eList = new ArrayList<Employee1>();
		eList.add(e1);
		eList.add(e2);
		eList.add(e3);
		eList.add(e4);
		eList.add(e5);

		eList.stream().filter(e -> e.getEmpLocation().equalsIgnoreCase("Hyderabad")).forEach(System.out::println);

		eList.stream().filter(new Predicate<Employee1>() {
			public boolean test(Employee1 e) {
				return "Hyderabad".equalsIgnoreCase(e.getEmpLocation());
			}
		}).forEach(System.out::println);

		System.out.println(eList.stream().filter(e -> e.getEmpLocation().equalsIgnoreCase("Hyderabad"))
				.sorted(new Comparator<Employee1>() {
					public int compare(Employee1 e1, Employee1 e2) {
						return e1.getEmpName().compareToIgnoreCase(e2.getEmpName());
					}
				}).findFirst().get());

		System.out.println(eList.stream().filter(e -> e.getEmpName().equalsIgnoreCase("praveen"))
				.map(Employee1::getEmpAge).findAny().orElse(0));

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

	}
}

class Employee1 {
	private String empName;
	private Long empId;
	private String empLocation;
	private int empAge;

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
