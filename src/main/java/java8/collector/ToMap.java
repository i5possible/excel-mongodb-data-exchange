package java8.collector;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ToMap {
    public static void main(String[] args) {
        Map<String, Object> collect = Arrays.asList(null, "1", null,  "2", "3").stream()
                .collect(Collectors.toMap(getKey(), Function.identity()));
        System.out.println(collect);
    }

    public static Function<String, String> getKey () {
        return s -> new Random().toString();
    }
}
