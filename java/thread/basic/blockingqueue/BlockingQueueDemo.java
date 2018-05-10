package thread.basic.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        for(int i = 0; i < 30; i++) {
            blockingQueue.put(i);
            System.out.println("add " + i);
        }
        System.out.println("end");
    }

}
