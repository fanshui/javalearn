package IO.File;

import java.io.File;
import java.io.IOException;

/*File 只用于文件的信息 不用于文件内容的访问*/
public class FileTest {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\fh\\test\\src\\FileIO\\File\\test");
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println(file.isAbsolute());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

        File file1 = new File("C:\\fh\\test\\src\\FileIO\\File\\test\\1.txt");
        if (!file1.exists()) {
            file1.createNewFile();

        }
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getName());


    }
}
