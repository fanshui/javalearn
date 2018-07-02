package thread.basic;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConcurrentQueueD {

//    private ConcurrentLinkedQueue<Integer> clqueue = new ConcurrentLinkedQueue<>();
    private LinkedBlockingQueue<Integer> clqueue = new LinkedBlockingQueue<>();
    private AtomicBoolean aBoolean = new AtomicBoolean(true);

    @Test
    public void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;;i++){
                    try {
                        clqueue.put(i);
                        if(i == 10){
                            aBoolean.set(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("add : " + i);
                }
            }
        },"put-Thread -- ").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    Integer temp = null;
                    try {
                        temp = clqueue.take();
                        System.out.println("poll : " + temp);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for (;;){
            if(!aBoolean.get()){
                break;
            }
        }

    }

}
