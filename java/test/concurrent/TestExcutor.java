package test.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

public class TestExcutor {

    @Test
    public void test1(){
        Thread.currentThread().setDaemon(true);
        int size = 100;
//        CountDownLatch latch = new CountDownLatch(size);
        ExecutorService executors = Executors.newFixedThreadPool(1);
        for (int i = 0;i < size;i++){
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello world");
                }
            });
        }

    }


    @Test
    public void test2() throws InterruptedException {
        int size = 100;
        CountDownLatch latch = new CountDownLatch(size);
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);
        for(int i=0;i<size;i++){
            final int t = i;
            blockingQueue.add(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                    }
                    System.out.println(t+"hello world");
                }
            });
        }
        ExecutorService executorService = new ThreadPoolExecutor(1,
                    1,
                60, TimeUnit.SECONDS ,
                blockingQueue);
        executorService.execute(new Thread());
        latch.await();
    }


}
