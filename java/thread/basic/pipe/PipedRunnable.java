package thread.basic.pipe;

import java.io.IOException;
import java.io.PipedOutputStream;

public class PipedRunnable implements Runnable {

    private PipedOutputStream pipedOutputStream;

    PipedRunnable(PipedOutputStream pout) {
        this.pipedOutputStream = pout;
    }

    @Override
    public void run() {
        byte[] bytes = {1,2,3,4,5,6,7,8,9};
        try {
            pipedOutputStream.write(bytes);
            pipedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                pipedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
