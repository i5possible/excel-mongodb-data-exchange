package java8.datetime;

import java.time.ZoneId;

public class ZoneOffset {
    public static void main(String[] args) {
        ZoneId zoneId = ZoneId.of("-01:00");
        System.out.println(zoneId);
    }
}
