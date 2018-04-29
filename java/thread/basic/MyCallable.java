package thread.basic;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer>{

    int x = 5;
    int y = 3;

    public MyCallable() {

    }

    public MyCallable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer call() throws Exception {
        return x + y;
    }
}
