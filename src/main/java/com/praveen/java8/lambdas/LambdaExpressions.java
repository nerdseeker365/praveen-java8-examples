package com.praveen.java8.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Lambda Expression basically express the instance of functional interface 
   Syntax:
	Parameter  Expression    Body
 		()         ->         System.out.println(“Praveen”);
 */
public class LambdaExpressions {
	public static void main(String[] args) {
		Runnable r= () -> System.out.println("This is in run method") ;
		Thread t1 = new Thread(r);
		t1.start();
		Thread t = new Thread(new CodeToRun());
		t.start();		
		/* The above one can be written in Lambda */
		new Thread(() -> System.out.println("printing from the runnable")).start();		
		/* if we have multiple statements related to lambda, we need to include a brace with return */
		Calculator ci1= (int num1,int num2) -> {
			int sum=num1+num2;
			System.out.println(sum);
			return sum;
		};		
		System.out.println(ci1.add(5, 6));
		
		/* Usage of Comparator for sorting  Starts*/
		
		Employee e1= new Employee("Praveen Oruganti",35);
		Employee e2= new Employee("Durgaprasad Oruganti",36);
		Employee e3 = new Employee("Varma Anjaneya",32);
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
		
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return (int) e1.getAge() - e2.getAge();
			}

		});
		
		System.out.println("Employee Sorting based on Age:\n"+ empList);
		
		Collections.sort(empList,new Comparator<Employee>() {
			@Override
			public int compare(Employee e1,Employee e2) {				
				return e1.getEmpName() .compareTo(e2.getEmpName());				
			}
		});
		
		System.out.println("Employee Sorting based on Name:\n"+ empList);	
		
		/* Usage of Comparator for sorting  Ends*/
		
		DisplayInterface di= (name) -> System.out.println(name) ;
		di.display("Praveen") ;

	}
}

interface DisplayInterface{
void display(String name);
}


class CodeToRun implements Runnable {
	public void run() {
		System.out.println("printing from the runnable");
	}
}

interface Calculator {
int add(int num1, int num2);
}

class Employee{	
	private String empName;
	private int Age;
	public Employee(String empName, int age) {
		super();
		this.empName = empName;
		Age = age;
	}
	public Employee() {
	}
	public String getEmpName() {
		return empName;
	}
	public int getAge() {
		return Age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Age;
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (Age != other.Age)
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", Age=" + Age + "]";
	}
	
}