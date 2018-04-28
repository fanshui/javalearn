package propertyConf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by fang on 2017/12/19.
 */
public class PropertiesTest {
    public static void main(String[] args) {

        //二级映射表 提供默认
        Properties defaultSettings = new Properties();
        defaultSettings.put("key_0","Default 0");
        defaultSettings.put("key_-1","default -1");
        //如果未设置该键，就用默认值中的
        Properties settings = new Properties(defaultSettings);


        //从文件加载
        try {
            FileInputStream in = new FileInputStream("Myprog.properties");
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(settings.getProperty("key_4"));

        settings.put("key_1","value_1");
        settings.put("key_2","value_2");
        settings.put("key_3","value_3");

        //store() 保存到文件
        try {
            FileOutputStream out = new FileOutputStream("Myprog.properties");
            settings.store(out,"comments");
        } catch (Exception e) {
            e.printStackTrace();
        }



        //提供默认
        String s = settings.getProperty("title","Default title");
        System.out.println(s);
        String s1 = settings.getProperty("key_0");
        System.out.println(s1);



        //查看系统信息
        Properties sysProperties = System.getProperties();

        System.out.println(sysProperties);

        String userDir = System.getProperty("user.home");

    }
}
