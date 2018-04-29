package thread.basic;

public class Main {

    public static void main(String[] args) {

         new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running " );
                try {
                    for(int i = 4; i > 0; i--) {
                        System.out.println("Thread: " + ", " + i);
                        // 让线程睡眠一会
                        Thread.sleep(50);
                    }
                }catch (InterruptedException e) {
                    System.out.println("Thread " + " interrupted.");
                }
                System.out.println("Thread "  + " exiting.");
            }
        }).start();

    }


}
