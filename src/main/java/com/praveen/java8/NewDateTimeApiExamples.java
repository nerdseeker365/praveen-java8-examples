package com.praveen.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class NewDateTimeApiExamples {

	public static void main(String args[]) {

		// current date and time
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Current DateTime: " + currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();

		System.out.println("Month: " + month + " day: " + day + " seconds: " + seconds);

		// current date and time
		ZonedDateTime date = ZonedDateTime.parse("2019-07-28T10:14:20+06:30[Asia/Kolkata]");
		System.out.println("date: " + date);

		// Get the current date
		LocalDate currentDate = LocalDate.now();
		System.out.println("Current date: " + currentDate);

		// add 1 week to the current date
		LocalDate nextWeek = currentDate.plus(1, ChronoUnit.WEEKS);
		System.out.println("Next week: " + nextWeek);

		// add 2 month to the current date
		LocalDate nextMonth = currentDate.plus(2, ChronoUnit.MONTHS);
		System.out.println("Next month: " + nextMonth);

		// add 3 year to the current date
		LocalDate nextYear = currentDate.plus(3, ChronoUnit.YEARS);
		System.out.println("Next year: " + nextYear);

		// add 10 years to the current date
		LocalDate nextDecade = currentDate.plus(1, ChronoUnit.DECADES);
		System.out.println("Next ten year: " + nextDecade);

		// comparing dates
		LocalDate date2 = LocalDate.of(2014, 1, 15);
		LocalDate date3 = LocalDate.of(2019, 7, 28);

		if (date2.isAfter(date3)) {
			System.out.println("date2 comes after date3");
		} else {
			System.out.println("date2 comes before date3");
		}

		// check Leap year
		if (date1.isLeapYear()) {
			System.out.println("This year is Leap year");
		} else {
			System.out.println(date1.getYear() + " is not a Leap year");
		}

		//How many days, month between two dates
		LocalDate newDate = LocalDate.of(2019, Month.DECEMBER, 14);
		Period periodTonewDate = Period.between(date1, newDate);
		System.out.println("Months left between today and newDate : " + periodTonewDate.getMonths());

	}

}
