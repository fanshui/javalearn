package socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("ret.data").getChannel();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(
                new InetSocketAddress("127.0.0.1", 9999));

        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytes;
        buf.clear();
        while ((bytes = socketChannel.read(buf)) != -1) {
            buf.flip();
            fc.write(buf);
            buf.compact();
            System.out.print(Arrays.toString(buf.array()));
        }

        socketChannel.close();

    }

}
