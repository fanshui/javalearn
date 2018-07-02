package test.concurrent;

public class TesytRunnable implements Runnable {

    int i;
    public TesytRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i+"hello world");
    }
}
