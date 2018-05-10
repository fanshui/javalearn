package thread.basic.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("==================");
            }
        },1000,5000, TimeUnit.MILLISECONDS);
    }

}
