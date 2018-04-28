package socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.
        io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by fang on 2017/11/21.
 */
public class DateClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",67123);
            InputStream in = socket.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = bin.readLine()) != null){
                System.out.println(line);

                socket.close()
                ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
