package com.praveen.java8.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	}
}
