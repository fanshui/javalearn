package thread.basic.callable;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable1 = new SumTwo(2,5);
        Callable callable2 = new SumTwo(3,9);
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = es.submit(callable1);
        Future<Integer> f2 = es.submit(callable2);
        FutureTask<Integer> ft = new FutureTask<Integer>(callable1);
        new Thread(ft).start();
        System.out.println(ft.get());
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
        } finally {

            es.shutdown();
        }
    }
}
