package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",9999));
        while (true) {
            SocketChannel socketChannel =
                    serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.wrap("你好 ！ go to school!".getBytes());
            socketChannel.write(buffer);
//            break;
        }
    }

}
