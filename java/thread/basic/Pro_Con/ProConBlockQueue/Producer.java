package thread.basic.Pro_Con.ProConBlockQueue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
//            queue.put();

//            queue.put(endflag);
//            stop();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
