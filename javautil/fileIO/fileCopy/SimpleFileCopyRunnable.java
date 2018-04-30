package fileIO.fileCopy;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SimpleFileCopyRunnable implements Runnable {

    private File source;
    private File target;

    /**
     * 简单复制文件
     * @param source 源文件
     * @param target  目标文件
     */
    public SimpleFileCopyRunnable(File source, File target) {
        this.source = source;
        this.target = target;
    }



    @Override
    public void run() {

        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileCopyUtils.close(in,out,inStream,outStream);
        }

    }

}
