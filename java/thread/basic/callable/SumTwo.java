package thread.basic.callable;

import java.util.concurrent.Callable;

public class SumTwo implements Callable<Integer> {

    int a;
    int b;

    public SumTwo(int a,int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        return a + b;
    }
}
