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

        if (!canCopy(fromFile)) return;

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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     *单线程文件复制
     * @param source 被复制的文件
     * @param target 复制到
     *
     * <p>使用nio channel transferTo方法</p>
     * <p>不考虑多线程优化，单线程文件复制最快的方法是(文件越大该方法越有优势，一般比常用方法快30+%</p>
     */
    public static void nioTransferCopy(File source, File target)  {
        if (!canCopy(source)) return;
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
            close(in,out,inStream,outStream);
        }
    }


    /**
     * 文件复制  可通过buffer监测复制进度
     * @param source 被复制的文件
     * @param target 目标文件
     * @param capacity buffer大小(4096)
     *
     *<p>如果需要监测复制进度，可以用第二快的方法(留意buffer的大小，对速度有很大影响)</p>
     *  <p>使用nio channel buffer</p>
     *
     */
    public static void nioBufferCopy(File source, File target, int capacity)  {
        if (!canCopy(source)) return;

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
            buffer.clear();
//            int count = 0;
            while (in.read(buffer) >= 0 || buffer.position() != 0) {
                buffer.flip();
                out.write(buffer);
//                System.out.println(buffer.toString());
//                System.out.println("This is " + count++ + "th " + "byte[" + capacity + "]");
                buffer.compact();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(in, out, inStream, outStream);

        }
    }


    /**
     * 多线程复制单个文件
     * @param source 源文件
     * @param target  目标文件
     * @param numOfThread 线程数量
     */
    public static void mtTransferCopySingleFile(File source, File target, int numOfThread) {

        if(!canCopy(source))
            return;
        long len = source.length();
        long lenOfEveryTh = len / numOfThread;
        for(int i = 0; i < numOfThread - 1; i++) {
            Thread t = new Thread(new MultThreadCopySingleFileRunnable(source,target,lenOfEveryTh * i, lenOfEveryTh * (i + 1)),
                    i + "th CopyThread");
            t.start();
        }
        Thread ct = new Thread(new MultThreadCopySingleFileRunnable(source,target,lenOfEveryTh * (numOfThread - 1),len));
        ct.start();
    }


    /**
     * 多线程复制多个文件 ，其中每个线程复制一个文件
     * @param sources 源文件数组
     * @param targetDir 目标目录
     */
    public static void mtTransferCopyMultiFiles(File[] sources,File targetDir) {

        for (File source : sources) {
            if(!canCopy(source))
                return;
        }
        if (!targetDir.exists() || !targetDir.isDirectory())
            return;

        for(int i = 0; i < sources.length; i++) {
            Thread t = new Thread(new SimpleFileCopyRunnable(sources[i],new File(targetDir,sources[i].getName())),
                    sources[i].getName() + " copy thread");
            t.start();
        }

    }




    public static void close(FileChannel in, FileChannel out, FileInputStream inStream, FileOutputStream outStream) {
        try {
            if (out != null) out.close();
            if (in != null)in.close();
            if (outStream != null)outStream.close();
            if (inStream != null)inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(FileChannel in, FileChannel out, RandomAccessFile inStream, RandomAccessFile outStream) {
        try {
            if (out != null) out.close();
            if (in != null)in.close();
            if (outStream != null)outStream.close();
            if (inStream != null)inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean canCopy(File fromFile) {
        if (!fromFile.exists()) {
            return false;
        }
        if (!fromFile.isFile()) {
            return false;
        }
        if (!fromFile.canRead()) {
            return false;
        }
        return true;
    }

}
