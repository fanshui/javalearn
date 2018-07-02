package thread.basic.Pro_Con.ProConBlockQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
//            queue.take();
            //endflag?stop();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
