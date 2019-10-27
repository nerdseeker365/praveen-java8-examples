package com.praveen.java8.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandling {

	public static void main(String[] args) throws IOException {
		// Listing files		
		try (Stream<Path> stream = Files.list(Paths.get(""))) {
		    String joined = stream
		        .map(String::valueOf)
		        .filter(path -> !path.startsWith("."))
		        .sorted()
		        .collect(Collectors.joining("; "));
		    System.out.println("List: " + joined);
		}
		
		// Finding files
		Path start = Paths.get("src/main/java");
		int maxDepth = 5;
		try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
		        String.valueOf(path).endsWith(".java"))) {
		    String joined = stream
		        .sorted()
		        .map(String::valueOf)
		        .collect(Collectors.joining("; "));
		    System.out.println("Found: " + joined);
		}
		
		// Finding files
		Path start1 = Paths.get("src/main/java");
		int maxDepth1 = 5;
		try (Stream<Path> stream = Files.walk(start1, maxDepth1)) {
		    String joined = stream
		        .map(String::valueOf)
		        .filter(path -> path.endsWith(".java"))
		        .sorted()
		        .collect(Collectors.joining("; "));
		    System.out.println("walk(): " + joined);
		}
		
		// Reading file
		List<String> lines = Files.readAllLines(Paths.get("file.txt"));
		System.out.println(lines.size());
		// Writing file
		lines.add("How are you?");
		Files.write(Paths.get("file.txt"), lines);
		
		// Reading file using streams				
		try (Stream<String> stream = Files.lines(Paths.get("file.txt"))) {
		    stream
		        .filter(line -> line.contains("Praveen"))
		        .map(String::trim)
		        .forEach(System.out::println);
		}
		
		Path path = Paths.get("file1.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
		    writer.write("How are you?");
		}
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    System.out.println(reader.readLine());
		}		
		
		
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    long countPrints = reader
		        .lines()
		        .filter(line -> line.contains("you"))
		        .count();
		    System.out.println(countPrints);
		}
		
	}
}
