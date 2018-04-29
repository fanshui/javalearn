package fileIO.fileCopy;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileCopyUtils {


    /**
     *
     * @param fromFile 被复制的文件
     * @param toFile 复制的文件目录
     * @param rewrite 是否重新创建文件
     *
     * <p>文件复制 把文件复制到指定目录</p>
     */
    public static void copyfile(File fromFile,File toFile,Boolean rewrite) {

        if (!fromFile.exists()) {
            return;
        }
        if (!fromFile.isFile()) {
            return;
        }
        if (!fromFile.canRead()) {
            return;
        }
        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }
        if (toFile.exists() && rewrite) {
            toFile.delete();
        }

        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);
            byte[] bt = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt,0,c);
            }

            fosfrom.close();
            fosto.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**
     *
     * @param source 被复制的文件
     * @param target 复制到
     *
     * <p>使用nio channel transferTo方法</p>
     * <p>不考虑多线程优化，单线程文件复制最快的方法是(文件越大该方法越有优势，一般比常用方法快30+%</p>
     */
    public static void nioTransferCopy(File source, File target)  {
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
            try {
                inStream.close();
                in.close();
                outStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     *
     * @param source 被复制的文件
     * @param target 目标文件
     * @param capacity buffer大小(4096)
     *
     *  <p>使用nio channel buffer</p>
     *  <p>如果需要监测复制进度，可以用第二快的方法(留意buffer的大小，对速度有很大影响)</p>
     */
    public static void nioBufferCopy(File source, File target, int capacity)  {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(capacity);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                outStream.close();
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }













    public static void main(String[] args) {

        File from = new File("C:\\fh\\javalearn\\testfile\\a.txt");
        File to = new File("C:\\fh\\javalearn\\testfile\\d.txt");
        nioBufferCopy(from, to, 4096);

    }




}
