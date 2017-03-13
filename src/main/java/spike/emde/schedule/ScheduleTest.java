package spike.emde.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {
    @Scheduled(fixedDelay = 5000)
    public void doSomethingAfter5000() {
        System.out.println("5000ms interval");
    }

    @Scheduled(cron = "0 11/1 * * * ?")
    public void doSomethingEveryMinute() {
        System.out.println("every minutes");
    }
}
