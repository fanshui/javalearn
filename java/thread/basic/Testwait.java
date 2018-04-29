package thread.basic;

/*
Java中 线程只有： 等待状态   可运行状态
wait() : 使####当前线程######进入等待状态:
                    让当前线程释放所持有的锁，直到其它线程调用此对象的notify(),才进入就绪；
wait(long timeout) : 直到notify 或 超过指定的时间
notify() ：唤醒当前对象上的等待线程
notifyAll() :

 */

class Thread_A extends Thread{
    int num;
    public Thread_A(String name,int num){
        super(name);
        this.num = num;
    }
    @Override
    public void run() {

        synchronized (this) {
            while(num <= 20){
                this.notify();
                num ++;
                System.out.println(Thread.currentThread().getName() + " : " + num);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }





            }
        }
    }



    }




public class Testwait {

    public static void main(String[] args) {
        Thread_A t1 = new Thread_A("t1",0);
        t1.start();
//        System.out.println(Thread.currentThread().getName());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (t1){

            while (t1.num <= 20){

                t1.num ++;
                System.out.println(Thread.currentThread().getName() + " : " + t1.num);
                t1.notify();
                try {
                    t1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}










































