package project.dal.inventory.time.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public final class DateUtil {

    private static final String YYYYMMDD= "yyyy-MM-dd";

    private static final String YYYYMMDD_HHMMSS= "yyyy-MM-dd HH:mm:ss";

    private static final long DAY_OF_MILLIS = 24*60*60*1000L;

    private static final long MINUTES_OF_MILLIS = 60*1000L;

    /**
     * @description: //零点
     *
     * @Param [datetime]
     * @return java.util.Date
     **/
    public static Date toDate(Date datetime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    /**
     * @description: //前 previous 天零点
     *
     * @Param [datetime, previous]
     * @return java.util.Date
     **/
    public static Date preDate(Date datetime, int previous) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, -previous);
        return cal.getTime();
    }
    /**
     * @description: //后 following 天零点
     *
     * @Param [datetime, following]
     * @return java.util.Date
     **/
    public static Date nextDate(Date datetime, int following) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetime);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, following);
        return cal.getTime();
    }

    /**
     * 获取几个小时后的时间
     * @param startDate
     * @param hour
     * @return
     */
    public static Date getNextHourDate(Date startDate, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }



    public static Date parseYYYYMMDD(String date) {
        return parseDateTime(date, YYYYMMDD);
    }

    public static String formatYYYYMMDD(Date date) {
        return formatDateTime(date, YYYYMMDD);
    }

    public static Date parseYYYMMDDHHMMSS(String date) {
        return parseDateTime(date, YYYYMMDD_HHMMSS);
    }

    public static String formatDateTime(Date date) {
        return formatDateTime(date, YYYYMMDD_HHMMSS);
    }

    public static Date parseDateTime(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateTime(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static int getIntervalDay(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long interval = to - from;
        return (int) (interval / DAY_OF_MILLIS);
    }

    public static int getIntervalMinutes(Date fromDate, Date toDate) {
        long from = fromDate.getTime();
        long to = toDate.getTime();
        long interval = to - from;
        return (int) (interval / MINUTES_OF_MILLIS);
    }

    /**
     * @description: //从startDate到endDate时间级
     *
     * @Param [startDate, endDate]
     * @return java.util.List<java.util.Date>
     **/
    public static List<Date> getDates(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        while (cal.getTime().getTime() < endDate.getTime()) {
            dates.add(cal.getTime());
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }


    /**
     * 通过颗粒度获取时间在当天的位置
     * @param granularity
     * @param date
     * @return
     */
    public static int getDateSubscript(int granularity, Date date) {
        Date d = DateUtil.toDate(date);
        long dateInt = date.getTime();
        long dInt = d.getTime();
        long l = (dateInt - dInt) / 1000 / 60;
        return (int)(l / granularity);
    }

    /**
     * 通过时间颗粒度获取一天的所有时间点
     * @param granularity
     * @return
     */
    public static List<Date> getAllDate(int granularity, Date date){
        List<Date> result = new ArrayList<Date>();
        Date startDate = DateUtil.toDate(date);
        Date endDate = DateUtil.nextDate(date, 1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        while(cal.getTime().before(endDate)){
            result.add(cal.getTime());
            cal.add(Calendar.MINUTE, granularity);
        }
        return result;
    }


    public static void main(String[] args) {
//        Date startDate = DateUtil.parseDateTime("2019-10-23 10:00:00");
//        int dateSubscript = getDateSubscript(30, startDate);
//        System.out.println(dateSubscript);
    }


}
