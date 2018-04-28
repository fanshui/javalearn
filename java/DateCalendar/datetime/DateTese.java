package DateCalendar.datetime;

import java.util.Date;

/**
 * Created by fanghui on 2017/10/20.
 */
public class DateTese {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("date :" + date);
        System.out.println("date.getTime() :" + date.getTime());
        long timestamp = 1508557655000L;
        date.setTime(timestamp);
        System.out.println("date.setTime(1508557655000) date :" + date);
        System.out.println(System.currentTimeMillis());
        System.out.println(date.before(new Date()));
    }
}
