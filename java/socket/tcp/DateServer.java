package socket.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by fang on 2017/11/21.
 */
public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(67123);
            //监听
            while (true){
                Socket client = socket.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(),true);
                pout.println(new Date());
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
