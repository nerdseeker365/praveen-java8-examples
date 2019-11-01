package com.praveen.java8.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Java8LambdaExamples {

	public static void main(String args[]) {

		Employee emp1 = new Employee(1, "Praveen", 34, 1000000);
		Employee emp2 = new Employee(2, "Rahul", 29, 500000);
		Employee emp3 = new Employee(3, "Prasad", 35, 900000);

		ArrayList<Employee> empList = new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);

		/* Without Lambdas */
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return (int) (e1.empSal - e2.empSal);
			}
		});
		System.out.println(empList);

		/* With Lambdas */
		Collections.sort(empList, (Employee e1, Employee e2) -> Double.compare(e1.empSal, e2.empSal));
		System.out.println(empList);
	}

}

class Employee {
	int empId;
	String empName;
	int empAge;
	double empSal;

	public Employee(int empId, String empName, int empAge, double empSal) {
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empSal = empSal;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empSal=" + empSal + "]";
	}
}