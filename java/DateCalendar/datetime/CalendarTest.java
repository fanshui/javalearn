package DateCalendar.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by fanghui on 2017/10/20.
 */
public class CalendarTest {
    public static void main(String[] args) {
        GregorianCalendar gcalendar = new GregorianCalendar();
        System.out.println(gcalendar);
        GregorianCalendar selfde = new GregorianCalendar(1999,11,31);
        selfde = new GregorianCalendar(1999, Calendar.AUGUST,20);
        selfde = new GregorianCalendar(2000,11,5,23,59,59);

        int month = selfde.get(Calendar.MONTH);
        int weekday = selfde.get(Calendar.DAY_OF_WEEK);

        selfde.set(Calendar.YEAR,2005);
        selfde.add(Calendar.MONTH,3);

        Date time = selfde.getTime();

        selfde.setTime(time);
    }
}
