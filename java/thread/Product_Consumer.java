package thread;

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
    public int max; //最大容量
    public int currentNum; //当前容量
    public Container(int max){
        this.max = max;
        currentNum = 0;
    }
}

class Producer implements Runnable{
    Container con;
    public Producer(Container con){
        this.con = con;
    }
    @Override
    public void run() {
        while(true){   //有无数苹果
            synchronized (con){
                if(con.currentNum < con.max){  //当前容器不满
                    con.notify(); //生产完释放锁并通知 syn块执行完才释放
                    con.currentNum++;
                    System.out.println("生产者正在生产 ... + 1 ,当前产品数： " + con.currentNum);
                }else if(con.currentNum == con.max){
                    System.out.println("箱子已饱和，停止生产，正在等待消费...");
                    try {
                        con.wait();   //当前线程：Producer生产者线程等待，并不是con等待，直到消费者消费
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } //执行完同步块 释放锁
            try {
                Thread.sleep(1000);
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
        while (true){
            synchronized (con){
                if(con.currentNum > 0){
                    con.notify(); //唤醒其它线程的con等待锁
                    con.currentNum--;
                    System.out.println("消费者正在消费...-1,当前产品数： " + con.currentNum);
                }else if(con.currentNum == 0){
                    System.out.println("箱子已经空了，消费者停止消费，正在等待生产... ");
                    try {
                        con.wait();//当前消费者线程等待，直到生产者生产完了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



































