package thread.basic.Pro_Con;

/**
 * Created by fanghui on 2017/11/6.
 */
public class Product_Consumer {
    public static void main(String[] args) {
        Container con = new Container(5);//箱子最大容量

        Producer producer = new Producer(con);
        Consumer consumer = new Consumer(con);
        new Thread(producer,"producer").start();
        new Thread(consumer,"consumer").start();
    }
}

//容器类 最大容量 当前容量
class Container{
    private int max; //最大容量
    private int currentNum; //当前容量
    public Container(int max){
        this.max = max;
    }

    public synchronized void put() throws InterruptedException {
        while (currentNum == max) {
            wait();
        }
        currentNum ++;
        System.out.println("生产者生产...." + currentNum);
        notify();
    }

    public synchronized void get() throws InterruptedException {
        while (currentNum == 0) {
            wait();
        }
        currentNum --;
        System.out.println("消费者消费...." + currentNum);
        notify();
    }


}

class Producer implements Runnable{
    private Container con;
    public Producer(Container con){
        this.con = con;
    }
    @Override
    public void run() {
        while (true) {

            try {
                con.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    public Container con;
    public Consumer(Container con){
        this.con = con;
    }

    @Override
    public void run() {
        while (true) {
            try {
                con.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



































