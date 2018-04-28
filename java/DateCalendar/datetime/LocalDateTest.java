package DateCalendar.datetime;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by fanghui on 2017/10/18.
 */

//java.time.LocalDate月份和星期都改成了enum
// LocalDate和LocalTime和最基本的String一样，是不变类型，不但线程安全，而且不能修改
// 在新的Java 8中，日期和时间被明确划分为LocalDate和LocalTime，LocalDate无法包含时间，LocalTime无法包含日期。
// 当然，LocalDateTime才能同时包含日期和时间。
public class LocalDateTest {
    public static void main(String[] args) {

        //取当前日期
        LocalDate localdate = LocalDate.now();
        System.out.println("LocalDate.now() : " + localdate);

        LocalDate localdateClock = LocalDate.now(Clock.systemDefaultZone());
        System.out.println("LocalDate.now(Clock) : " + localdate);


        // 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2017, 10, 21);
        LocalDate c = LocalDate.of(2017, Month.JANUARY,1);
        c = LocalDate.ofYearDay(2017,50);
        LocalDate d = LocalDate.ofEpochDay(980L);//EpochDay(long 1970年天数)
        System.out.println(crischristmas);
        System.out.println(c);


        // 根据字符串取：
        LocalDate endOfFeb = LocalDate.parse("2017-02-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate.parse("2014-02-28"); // 无效日期无法通过：DateTimeParseException: Invalid date


        //日期操作
        LocalDate today = LocalDate.now();
        today.getEra();
        today.getYear();
        today.getMonth();//return Month
        today.getMonthValue();
        today.getDayOfMonth();
        today.getDayOfWeek();
        today.getDayOfWeek();
        today.getDayOfYear();
        today.isLeapYear();//return boolean 闰年
        int a = today.lengthOfMonth();//月天数
        int b = today.lengthOfYear();//年天数
        System.out.println("月的天数： " + a);

        //日期转换
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = localdate.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = localdate.withDayOfMonth(2);
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = localdate.with(TemporalAdjusters.lastDayOfMonth());
        // 取下一天：
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1);
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
    }

}
