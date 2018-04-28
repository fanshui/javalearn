package DateCalendar.datetime;

import java.time.LocalTime;

/**
 * Created by fanghui on 2017/10/21.
 */
public class LocalTimeTeat {
    public static void main(String[] args) {

        //LocalTime只包含时间

        LocalTime now = LocalTime.now();
        System.out.println(now);//15:44:57.168 包含毫秒
        now.withNano(0);//清除毫秒数
        //构造时间
        LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
        LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00


    }
}
