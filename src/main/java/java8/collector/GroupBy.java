package java8.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupBy {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Integer, Map<Integer, List<Integer>>> collect = intList.stream()
                .collect(Collectors.groupingBy(GroupBy::isEven,
                        Collectors.groupingBy(GroupBy::dividedBy4)));
        System.out.println(collect);
    }

    public static int isEven (int i ) {
       return (i % 2);
    }

    public static int dividedBy4 (int i) {
        return (i % 4);
    }
}
