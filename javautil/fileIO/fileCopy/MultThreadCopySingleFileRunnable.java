package fileIO.fileCopy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class MultThreadCopySingleFileRunnable implements Runnable {

    private File source;
    private File target;
    private long start, end;//start指定起始位置，end指定结束位置

    /**
     * 多线程复制单个文件任务
     * @param source
     * @param target
     * @param start 复制起始位置
     * @param end  复制结束为止
     */
    public MultThreadCopySingleFileRunnable(File source, File target, long start, long end) {
        this.source = source;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        RandomAccessFile in = null;
        RandomAccessFile out = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            in = new RandomAccessFile(source,"r");
            out = new RandomAccessFile(target, "rw");
            in.seek(start);// 将输入跳转到指定位置
            out.seek(start);// 从指定位置开始写
            inChannel = in.getChannel();
            outChannel = out.getChannel();
            FileLock lock = outChannel.lock(start, (end - start), false);//对操作区域加锁
            inChannel.transferTo(start, (end - start), outChannel);
            lock.release();//释放锁

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileCopyUtils.close(inChannel,outChannel,in,out);
        }


    }
}
