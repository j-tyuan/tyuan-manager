package org.springmg.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by guiqijiang on 2/24/20.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final String DARE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String[] DATE_FORMATS = {"yyyy", "yyyy-MM", "yyyy-MM-dd",
            "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", DARE_FORMAT,
            "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'+'08:00", "yyyy-MM-dd'T'HH:mm:ss'+'00:00"};

    /**
     * 获取两个日期之间的所有日期(字符串格式, 按天计算)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenDate(String start, String end, String type) {

        try {
            if ("day".equalsIgnoreCase(type)) {
                return getBetweenDays(parseDate(start, DATE_FORMATS), parseDate(end, DATE_FORMATS));
            } else if ("month".equalsIgnoreCase(type)) {
                return getBetweenMonths(parseDate(start, DATE_FORMATS), parseDate(end, DATE_FORMATS));
            } else {
                return getBetweenYears(parseDate(start, DATE_FORMATS), parseDate(end, DATE_FORMATS));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 获取两个日期之间的所有日期(字符串格式, 按天计算)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenYears(Date start, Date end) {
        List<String> result = new ArrayList<>();

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        tempStart.add(Calendar.YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(sdf.format(start));
        while (tempStart.before(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.YEAR, 1);
        }
        return result;
    }


    /**
     * 获取两个日期之间的所有日期(字符串格式, 按天计算)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenMonths(Date start, Date end) {
        List<String> result = new ArrayList<>();

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        tempStart.add(Calendar.MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(sdf.format(start));
        while (tempStart.before(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.MONTH, 1);
        }
        return result;
    }


    /**
     * 获取两个日期之间的所有日期(字符串格式, 按天计算)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getBetweenDays(Date start, Date end) {
        List<String> result = new ArrayList<>();

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(sdf.format(start));
        while (tempStart.before(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }


    /**
     * 比较两个时间字符串是否一样
     *
     * @param dateStr1
     * @param dateStr2
     * @param type
     * @return
     */
    public static boolean equalsDateStr(String dateStr1, String dateStr2, String type) {
        int len = "day".equals(type) ? 10 : "month".equals(type) ? 7 : 4;
        byte[] b1 = dateStr1.getBytes();
        byte[] b2 = dateStr2.getBytes();
        for (int i = 0; i < len; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 限定时间不允许超过当前时间
     *
     * @param dateStr
     * @return
     */
    public static String limitDate(String dateStr) {
        Date toDay = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DARE_FORMAT);
        try {
            Date d = parseDate(dateStr, DATE_FORMATS);
            if (d.getTime() > toDay.getTime()) {
                return simpleDateFormat.format(toDay);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return simpleDateFormat.format(toDay);
        }
        return dateStr;
    }


    public static void main(String[] a) throws ParseException {
        System.out.print(equalsDateStr("2012-10-10", "2012-10-10", "day"));
    }

}
