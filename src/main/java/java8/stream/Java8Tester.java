package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Java8Tester {
    public static void main(String[] args) {

        System.out.println("---------map-------");

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//get list of unique squares
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        //this 'forEach' is the method of the interface Iterable.
        squaresList.forEach(System.out::println);

        System.out.println("------forEach---filter-------");

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        //this 'forEach' is the method of the interface Stream.
        System.out.println("Empty string numbers : " + strings.stream().filter(s -> s.isEmpty()).count());
        filtered.stream().limit(2).forEach(System.out::println);

        System.out.println("---------limit-------");

        Random random = new Random();
        random.ints().limit(5).forEach(System.out::println);

        System.out.println("-------sort---------");
        random.ints().limit(5).sorted().forEach(System.out::println);
    }
}
