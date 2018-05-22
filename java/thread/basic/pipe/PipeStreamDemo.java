package thread.basic.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
一个线程可以持有一个PipedInputStream对象，这个PipedInputStream对象在内部维护了一个字节数组，
默认大小为1024字节。它并不能单独使用，需要与另一个线程持有的一个PipedOutputStream建立关联，
PipedOutputStream往该字节数组中写数据，PipedInputStream从该字节数组中读数据，从而实现两个线程的通信。
 */
public class PipeStreamDemo {

    public static void main(String[] args) throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();

        in.connect(out);

        new Thread(new PipedRunnable(out)).start();
        int i = 0;
        while ((i = in.read()) != -1) {
            System.out.println(i);
        }
        in.close();
    }

}
