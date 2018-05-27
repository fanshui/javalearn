package filewatchdog.jnio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;

public class ResourceListener {

    /**
     *  WatchService 类的对象就是操作系统原生的文件系统监控器！
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "C:\\fh\\javalearn\\resource";

        // 获取文件系统的WatchService对象 获取当前OS平台下的文件系统监控器 可以用同样的代码同时获得多个监控器
        //监控器其实就是一个后台线程，在后台监控文件变化所发出的信号，这里通过上述代码获得的监控器还只是一个刚刚初始化的线程，
        // 连就绪状态都没有进入，只是初始化而已；
        WatchService watchService = FileSystems.getDefault().newWatchService();

        //将监控器注册给指定的文件节点——开启监控线程来监控指定的节点： Path对象的register方法
        Path path1 = Paths.get(path);
        //WatchKey register(WatchService watcher,WatchEvent.Kind<?>... events)
        // 该方法同时也会让监控器线程就绪并运行，该方法调用完后监控器就彻底开始监控了！
        path1.register(watchService
                , StandardWatchEventKinds.ENTRY_CREATE
                , StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.ENTRY_DELETE);

        // 如要监控子文件
//        File file = new File(path);
//        LinkedList<File> fList = new LinkedList<File>();
//        fList.addLast(file);
//        while (fList.size() > 0) {
//            File f = fList.removeFirst();
//            if (f.listFiles() == null)
//                continue;
//            for (File file2 : f.listFiles()) {
//                if (file2.isDirectory()) {//下一级目录
//                    fList.addLast(file2);
//                    //依次注册子目录
//                    Paths.get(file2.getAbsolutePath()).register(watchService
//                            , StandardWatchEventKinds.ENTRY_CREATE
//                            , StandardWatchEventKinds.ENTRY_MODIFY
//                            , StandardWatchEventKinds.ENTRY_DELETE);
//                }
//            }
//        }

        while (true) {
            // 获取下一个文件改动事件
            //WatchKey 文件节点的监控池，简称监控池 监控池是静态的！！！
            // 像register方法，刚注册完是返回的监控池是一个空的监控池，因为刚刚开启线程，什么都还没有发生，
            // 即使后面发生了文件修改，那么该监控池对象的内容还是保持不变，仍然是空的；
            // 需要主动去获取新的监控池时才会将更新的内容放入获取到的监控池中
            //a. WatchKey WatchService.poll(); // 尝试获取下一个变化信息的监控池，如果没有变化则返回null
            //b. WatchKey WatchService.take(); // // 尝试获取下一个变化信息的监控池，如果没有变化则一直等待
            // 如果需要长时间一直监控要用take，而如果只是在某个指定的时间监控则用poll；
            WatchKey key = watchService.take();
            // 获得WatchKey（监控池）中的具体监控信息 WatchKey中保存着一个事件列表List<WatchEvent<?>> list
            for (WatchEvent<?> event : key.pollEvents()) {
                //WatchEvent<?>表示监控时间对象
                //Path WatchEvent<?>.context() 返回触发该事件的那个文件或目录的路径
                //Kind<StandardWatchEventKinds> WatchEvent<?>kind() 返回事件类型（ENTRY_CREATE、ENTRY_DELETE、ENTRY_MODIFY之一）
                System.out.println( event.context() + " --> " + event.kind());
            }
            // 重设WatchKey 完成一次监控就需要重置监控器一次
            // 因为当你使用poll或take时监控器线程就被阻塞了，因为你处理文件变化的操作可能需要挺长时间的，
            // 为了防止在这段时间内又要处理其他类似的事件，因此需要阻塞监控器线程，而调用reset表示重启该线程；
            boolean valid = key.reset();
            // 如果重设失败，退出监听
            if (!valid) {
                break;
            }
        }

    }

}
