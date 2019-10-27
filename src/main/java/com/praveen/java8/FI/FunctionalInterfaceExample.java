package com.praveen.java8.FI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* A FunctionalInterface is an interface which has only one abstract method and can allow static and default methods 
   1. Optional -> generally used for handling NullPointerException
   
   2. Predicate -> which accepts an object and returns boolean i.e. either true or false
    interface Predicate<T> {
	public boolean test(T t);
	}
   3. function -> function is  same as Predicate but only difference is it can return any type of result.
   interface function(T,R) { 
   public R apply(T t); 
   ) } 
   4. consumer -> which consumes an object but doesn't return anything
   interface Consumer<T> { 
   public void accept(T t); 
   }
   5. supplier -> which returns an object without any input
   interface Supplier<T> { 
    T get(); 
   }
 */
public class FunctionalInterfaceExample {

	public static void main(String[] args) {		
		Test test =() -> System.out.println("Test Display");
		test.display();
		/* Optional */
		String[] words = new String[10];   
        Optional<String> checkNull =  
                      Optional.ofNullable(words[5]);   
        
        if (checkNull.isPresent()) {   
            String word = words[5].toLowerCase();   
            System.out.print(word);   
        } else {
            System.out.println("word is null");   
        }
        
        words[2] = "This is Praveen Oruganti";
        Optional<String> value = Optional.of(words[2]);
        System.out.println(value.get());
        
        System.out.println("Empty Optional: " + Optional.empty());
        
        value.ifPresent(word -> {
            System.out.println("word name = " + word);    
        });
        
       // User finalUser = optionalUser.orElse(new User("0", "Unknown User"));
        
//        User finalUser = optionalUser.orElseGet(() -> {
//            return new User("0", "Unknown User");
//        });
        
//        @GetMapping("/users/{userId}")
//        public User getUser(@PathVariable("userId") String userId) {
//            return userRepository.findByUserId(userId).orElseThrow(
//        	    () -> new ResourceNotFoundException("User not found with userId " + userId);
//            );
//        }
        
		/* Predicate predefined functional interface */		
		Predicate<Integer> pi1= i -> (i > 10);
		Predicate<Integer> pi2= i -> (i < 100);
	    System.out.println("pi1.test(100) "+ pi1.test(100));
	    System.out.println("pi1.test(7) " + pi1.test(7));
	    System.out.println("pi2.test(100) " +pi2.test(100));
	    System.out.println("pi2.test(99) " +pi2.test(99));
	    
	    Predicate<String> ps = s -> (s.length() > 3);
	    System.out.println("ps.test(\"Praveen\") " + ps.test("Praveen"));
	    System.out.println("ps.test(\"op\") "+ ps.test("op"));
		
	    Predicate<Collection> pc = c ->  c.isEmpty();
	    List al= new ArrayList();
	    System.out.println("pc.test(al) " +pc.test(al));
	    al.add("Praveen");
	    System.out.println("pc.test(al) " +pc.test(al));
	    
	   /*predicate joining is also possible with and(), or(), negate() */
	    
	    Predicate<Integer> pi1pi2 = pi1.and(pi2);
	    System.out.println("pi1pi2.test(101) " + pi1pi2.test(101));
	    System.out.println("pi1pi2.test(9) " + pi1pi2.test(9));
	    System.out.println("pi1pi2.test(11) " + pi1pi2.test(11));
	    System.out.println("pi1pi2.test(99) " + pi1pi2.test(99));
	    
	    Predicate<Integer> pi1pi2D= i -> (i > 10) && (i < 100) ;
	    
	    System.out.println("pi1pi2D.test(101) " + pi1pi2D.test(101));
	    System.out.println("pi1pi2D.test(9) " + pi1pi2D.test(9));
	    System.out.println("pi1pi2D.test(11) " + pi1pi2D.test(11));
	    System.out.println("pi1pi2D.test(99) " + pi1pi2D.test(99));
	    
	    /*Program to display names starts with 'P' by using Predicate*/
	    
	    String[] names={"Praveen","Prasad","Varma","Phani","Kiran"};
	    
	    Predicate<String> startsWithP= s -> s.charAt(0)=='P';
	    
	    for(String name: names) {
	    	if(startsWithP.test(name)) {
	    		System.out.println(name);
	    	}
	    }
	    
	    /* Function */
	    Function<String, Integer> f = s ->s.length();
	    System.out.println(f.apply("Praveen"));
	    System.out.println(f.apply("Prasad"));
	    Function<String,String> fr= s1->s1.replaceAll(" ","");
	    System.out.println(fr.apply("Praveen Oruganti"));
	    
	    /* Consumer */
	    Consumer<String> c=s->System.out.println(s);
	    c.accept("Praveen");
	    c.accept("Prasad");
	    
	    for(String name: names) {
	    	if(startsWithP.test(name)) {
	    		c.accept(name);
	    	}
	    }
	    
	    /*consumer joining is also possible with andThen() */
	    
	    
	    /*Supplier */
	    Supplier<String> supplier= () -> "This is Praveen Oruganti";
	    System.out.println(supplier.get());
	    Supplier<Double> randomValue = () -> Math.random();
	    System.out.println(randomValue.get()); 

	    
	    
	    
	}

}

@FunctionalInterface
interface Test {
	void display();
}
