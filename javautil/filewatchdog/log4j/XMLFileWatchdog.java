package filewatchdog.log4j;

import java.text.SimpleDateFormat;

/**
 * 监控XML文件的修改
 */
public class XMLFileWatchdog extends FileWatchdog {

    public XMLFileWatchdog(String filename) {
        super(filename);
    }

    @Override
    public void doOnChange() {
        //操作
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("load completed from file: [" + filename + "] changed At : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(lastModif));

    }

    public static void main(String[] args) {

        XMLFileWatchdog xmlFileWatchdog = new XMLFileWatchdog("C:\\fh\\javalearn\\resource\\config.xml");
        xmlFileWatchdog.setDelay(1000);
        xmlFileWatchdog.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0, j = 50; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
