package com.praveen.java8.others;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

class Game{
    String name;
    int ranking;

    public Game(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}

public class StringJoinExample {

	public static void main(String args[]) {
		// Join String by a delimiter
		StringJoiner sj = new StringJoiner(",");
		sj.add("aaa");
		sj.add("bbb");
		sj.add("ccc");
		System.out.println(sj);
		// Join String by a delimiter and starting with a supplied prefix and ending
		// with a supplied suffix.
		StringJoiner sj1 = new StringJoiner("/", "[", "]");
		sj1.add("2016");
		sj1.add("02");
		sj1.add("26");
		System.out.println(sj1);
		// StringJoiner is used internally by static String.join().
		// Join String by a delimiter.
		System.out.println(String.join("-", "2015", "10", "31"));
		// Join a List by a delimiter.

		List<String> list = Arrays.asList("java", "python", "nodejs", "ruby");
		// java, python, nodejs, ruby
		System.out.println(String.join(": ", list));

		// Collectors.joining
		//Join List<String> example.
		
		//java | python | nodejs | ruby
		System.out.println(list.stream().map(x -> x).collect(Collectors.joining(" | ")));
		
		//Join List<Object> example
		
		List<Game> listGame = Arrays.asList(
                new Game("Dragon Blaze", 5),
                new Game("Angry Bird", 5),
                new Game("Candy Crush", 5)
        );

        //{Dragon Blaze, Angry Bird, Candy Crush}
       System.out.println(listGame.stream().map(x -> x.getName())
		.collect(Collectors.joining(", ", "{", "}"))); 
		
	}

}
