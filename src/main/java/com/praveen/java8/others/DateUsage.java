package com.praveen.java8.others;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*In Java 8, a new series of date and time APIs (JSR310 and inspired by Joda-time) are created in the new java.time package.

java.time.LocalDate – date without time, no time-zone.
java.time.LocalTime – time without date, no time-zone.
java.time.LocalDateTime – date and time, no time-zone.
java.time.ZonedDateTime – date and time, with time-zone.
java.time.DateTimeFormatter – formatting (date -> text), parsing (text -> date) for java.time
java.time.Instant – date and time for machine, seconds passed since the Unix epoch time (midnight of January 1, 1970 UTC)
java.time.Duration – Measures time in seconds and nanoseconds.
java.time.Period – Measures time in years, months and days.
java.time.TemporalAdjuster – Adjust date.*/

public class DateUsage {
	public static void main(String args[]) {
		// Get current date time
		/*
		 * LocalDateTime now = LocalDateTime.now();
		 * 
		 * System.out.println("Before : " + now);
		 * 
		 * DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 * 
		 * String formatDateTime = now.format(formatter);
		 * 
		 * System.out.println("After : " + formatDateTime);
		 */

		// How to convert String to LocalDate
		String now = "2018-03-10 12:17";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

		System.out.println("Before : " + now);

		System.out.println("After : " + formatDateTime);

		System.out.println("After : " + formatDateTime.format(formatter));
		
		// comparing dates
		
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.of(2009, 12, 31);
        LocalDate date2 = LocalDate.of(2010, 01, 31);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        System.out.println("Is...");
        if (date1.isAfter(date2)) {
            System.out.println("Date1 is after Date2");
        }

        if (date1.isBefore(date2)) {
            System.out.println("Date1 is before Date2");
        }

        if (date1.isEqual(date2)) {
            System.out.println("Date1 is equal Date2");
        }

        System.out.println("CompareTo...");
        if (date1.compareTo(date2) > 0) {

            System.out.println("Date1 is after Date2");

        } else if (date1.compareTo(date2) < 0) {

            System.out.println("Date1 is before Date2");

        } else if (date1.compareTo(date2) == 0) {

            System.out.println("Date1 is equal to Date2");

        } else {

            System.out.println("How to get here?");

        }
        
     // Creating Durations
        System.out.println("--- Examples --- ");

        Duration oneHours = Duration.ofHours(1);
        System.out.println(oneHours.getSeconds() + " seconds");

        Duration oneHours2 = Duration.of(1, ChronoUnit.HOURS);
        System.out.println(oneHours2.getSeconds() + " seconds");

		// Test Duration.between
        System.out.println("\n--- Duration.between --- ");

        LocalDateTime oldDate = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime newDate = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56);

        System.out.println(oldDate);
        System.out.println(newDate);

        //count seconds between dates
        Duration duration = Duration.between(oldDate, newDate);

        System.out.println(duration.getSeconds() + " seconds");
        
        // Period Example
        
        System.out.println("--- Examples --- ");

        Period tenDays = Period.ofDays(10);
        System.out.println(tenDays.getDays()); //10

        Period oneYearTwoMonthsThreeDays = Period.of(1, 2, 3);
        System.out.println(oneYearTwoMonthsThreeDays.getYears());   //1
        System.out.println(oneYearTwoMonthsThreeDays.getMonths());  //2
        System.out.println(oneYearTwoMonthsThreeDays.getDays());    //3

        System.out.println("\n--- Period.between --- ");
        LocalDate oldDate1 = LocalDate.of(1982, Month.AUGUST, 31);
        LocalDate newDate1 = LocalDate.of(2016, Month.NOVEMBER, 9);

        System.out.println(oldDate);
        System.out.println(newDate);

        // check period between dates
        Period period = Period.between(oldDate1, newDate1);

        System.out.print(period.getYears() + " years,");
        System.out.print(period.getMonths() + " months,");
        System.out.print(period.getDays() + " days");
        
        
        // ChronoUnit Example
        LocalDateTime oldDate2 = LocalDateTime.of(1982, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime newDate2 = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10, 21, 56);

        System.out.println(oldDate2);
        System.out.println(newDate2);

        // count between dates
        long years = ChronoUnit.YEARS.between(oldDate2, newDate2);
        long months = ChronoUnit.MONTHS.between(oldDate2, newDate2);
        long weeks = ChronoUnit.WEEKS.between(oldDate2, newDate2);
        long days = ChronoUnit.DAYS.between(oldDate2, newDate2);
        long hours = ChronoUnit.HOURS.between(oldDate2, newDate2);
        long minutes = ChronoUnit.MINUTES.between(oldDate2, newDate2);
        long seconds = ChronoUnit.SECONDS.between(oldDate2, newDate2);
        long milis = ChronoUnit.MILLIS.between(oldDate2, newDate2);
        long nano = ChronoUnit.NANOS.between(oldDate2, newDate2);

        System.out.println("\n--- Total --- ");
        System.out.println(years + " years");
        System.out.println(months + " months");
        System.out.println(weeks + " weeks");
        System.out.println(days + " days");
        System.out.println(hours + " hours");
        System.out.println(minutes + " minutes");
        System.out.println(seconds + " seconds");
        System.out.println(milis + " milis");
        System.out.println(nano + " nano");
	}
}
