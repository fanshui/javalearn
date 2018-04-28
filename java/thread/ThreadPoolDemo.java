package thread;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        MyCallable c = new MyCallable(100,200);
        MyCallable c2 = new MyCallable(10, 20);

        Future<Integer> result = threadPool.submit(c);
        Integer sum = result.get();
        System.out.println("c sum =  " + sum);

        result = threadPool.submit(c2);
        sum = result.get();
        System.out.println("c2 sum = " + sum );

    }

}
