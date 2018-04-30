package fileIO.fileCopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

public class Main {
    public  static void main(String args[]) throws FileNotFoundException {

        File from1 = new File("C:\\fh\\javalearn\\testfile\\a.txt");
        File from2 = new File("C:\\fh\\javalearn\\testfile\\b.txt");
        File from3 = new File("C:\\fh\\javalearn\\testfile\\c.txt");
        File from4 = new File("C:\\fh\\javalearn\\testfile\\d.txt");
        File[] fromarr = {from1,from2,from3,from4};
        File to = new File("C:\\fh\\javalearn\\testfile\\dir");
//      FileCopyUtils.nioBufferCopy(from, to, 2);
//        FileCopyUtils.mtTransferCopySingleFile(from,to,6);
        FileCopyUtils.mtTransferCopyMultiFiles(fromarr,to);

    }

}
