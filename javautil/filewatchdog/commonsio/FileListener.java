package filewatchdog.commonsio;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

/**
 * Title:文件监听器
 *  * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 *  * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 *  * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 */
public class FileListener  extends FileAlterationListenerAdaptor {

    public FileListener() {
        super();
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onDirectoryCreate(File directory) {
        super.onDirectoryCreate(directory);
    }

    @Override
    public void onDirectoryChange(File directory) {
        super.onDirectoryChange(directory);
    }

    @Override
    public void onDirectoryDelete(File directory) {
        super.onDirectoryDelete(directory);
    }

    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
    }

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    /**
     * 文件监控
     * @throws Exception
     */
    private static void FileListenterClassTest() throws Exception{
        //监控文件
        String filePath="E:/test/file/";
        //过滤要监控的文件
        FileFilter filter = FileFilterUtils.and(new MyFileFilter());
        //文件观察器
        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(filePath, filter);
        //设置文件变化观察者
        fileAlterationObserver.addListener(new FileListener());

        // 监控目录
        String rootDir = "D:\\test";
        // 创建一个文件观察器用于处理文件的格式
//        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        FileAlterationObserver observer = new FileAlterationObserver(
                rootDir,
                FileFilterUtils.and(
                        FileFilterUtils.fileFileFilter(),
                        FileFilterUtils.suffixFileFilter(".txt")),  //过滤文件格式
                null);
        //设置文件变化观察者
        observer.addListener(new FileListener());


        // 轮询间隔 1 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        monitor.addObserver(fileAlterationObserver);
        // 开始监控
        monitor.start();
    }

    public static void main (String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("-----init file-----");
                    FileListenterClassTest();
                } catch (Exception e){
                    System.err.print(e);
                }
            }
        }).start();
    }

}

class MyFileFilter implements IOFileFilter {

    @Override
    public boolean accept(File file) {
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        if (extension != null && extension.equals("txt")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}
