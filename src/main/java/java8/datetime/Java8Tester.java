package java8.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Java8Tester {
    public static void main(String args[]) {
        Java8Tester java8tester = new Java8Tester();
//        java8tester.testLocalDateTime();
//        java8tester.testZonedDateTime();
//        java8tester.testChromoUnits();
//        java8tester.testPeriod();
//        java8tester.testDuration();
//        java8tester.testBackwardCompatability();

        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));

        System.out.println("testZonedDateTime");
        // Get the current date and time
        ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("date1: " + date1);
        ZoneId zone = date1.getZone();
        System.out.println(zone);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        ZonedDateTime zonedDateTime = date1.withZoneSameInstant(id);
        System.out.println("ParisTime :" + zonedDateTime);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);
        ZonedDateTime currentZoneDateTime = date1.withZoneSameInstant(currentZone);
        System.out.println("CurrentTime : " + currentZoneDateTime);
    }

    public void testLocalDateTime() {
        System.out.println("testLocalDateTime");
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("Month: " + month + "\nday: " + day + "\nseconds: " + seconds);

        LocalDateTime date2 = currentTime.withMonth(5).withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        //12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    public void testZonedDateTime() {
        System.out.println();
        System.out.println("testZonedDateTime");
        // Get the current date and time
        ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);
    }

    public void testChromoUnits() {
        System.out.println();
        System.out.println("testChromoUnits");
        //Get the current date
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        //add 1 week to the current date
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);

        //add 1 month to the current date
        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);

        //add 1 year to the current date
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);

        //add 10 years to the current date
        LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);
    }

    public void testPeriod() {
        System.out.println();
        System.out.println("testPeriod");
        //Get the current date
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " + date1);

        //add 1 month to the current date
        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + date2);

        Period period = Period.between(date2, date1);
        System.out.println("Period: " + period);
    }

    public void testDuration() {
        System.out.println();
        System.out.println("testDuration");
        LocalTime time1 = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        LocalTime time2 = time1.plus(twoHours);
        Duration duration = Duration.between(time1, time2);

        System.out.println("Duration: " + duration);
    }

    public void testBackwardCompatability() {
        System.out.println();
        System.out.println("testBackwardCompatability");
        //Get the current date
        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        //Get the instant of current date in terms of milliseconds
        Instant now = currentDate.toInstant();
        ZoneId currentZone = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
        System.out.println("Local date: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZone);
        System.out.println("Zoned date: " + zonedDateTime);
    }

    public LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.of("zoneId")).toLocalDate();
    }

    public ZonedDateTime convertDateToZoneDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(""));
    }
}