package thread.basic.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Thread t1 = new Thread(new MyThread(),"t1");
        Thread t2 = new Thread(new MyThread(),"t2");
        Thread t3 = new Thread(new MyThread(),"t3");
        Thread t4 = new Thread(new MyThread(),"t4");
        Thread t5 = new Thread(new MyThread(),"t5");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        pool.shutdown();

    }

}
