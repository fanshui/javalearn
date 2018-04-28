package IO.RandomAccessFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*对文件内容的访问
* r w
* 随机访问
* pointer
* write() 写一个字节
* read() 读一个字节
* */
public class AccessFile {
    public static void main(String[] args) throws IOException {
        File file = new File("demo");
        if (!file.exists()) {
            file.mkdir();
        }
        File t1 = new File(file, "raf.dat");
        if (!t1.exists()) {
            t1.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(t1,"rw");
        System.out.println(raf.getFilePointer());
        raf.write('A');//只写了一个字节
        System.out.println(raf.getFilePointer());

        //写int
        raf.writeInt(58);

        //写字节数组
        byte[] bytes = "中".getBytes("utf-8");
        raf.write(bytes);
        System.out.println(raf.length());

        /*读*/
        raf.seek(0);
        raf.read();

        raf.close();

    }
}
