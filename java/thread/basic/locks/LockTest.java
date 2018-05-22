package thread.basic.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        LockTest l = new LockTest();
        new Mythread12(l,"t1").start();
        new Mythread12(l,"t2").start();


    }

    public void testMethod() {
        try {
            lock.lock();
            for(int i = 0; i < 5; i++) {
                System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i = " + i);
            }
        } finally {
            lock.unlock();
        }
    }

}

class Mythread12 extends Thread {
    LockTest lockTest;

    public Mythread12(LockTest lockTest,String name) {
        super(name);
        this.lockTest = lockTest;
    }

    @Override
    public void run() {
        lockTest.testMethod();
    }
}
