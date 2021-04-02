/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/7/2 14:30
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.manage.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil extends DateUtils {
    /**
     * yyyy
     */
    public static final String YEAR_FORMAT = "yyyy";

    /**
     * yyyyMMdd
     */
    public static final String SHORT_FORMAT = "yyyyMMdd";

    /**
     * yyyyMMddHH
     */
    public static final String SHORT_FORMATH = "yyyyMMddHH";

    /**
     * yyyyMMddHHmm
     */
    public static final String SHORT_FORMATM = "yyyyMMddHHmm";

    /**
     * yyyyMMddHHmmss
     */
    public static final String LONG_FORMAT = "yyyyMMddHHmmss";

    public static final String LLONG_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String LONG_WEB_T_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * yyyy-MM-dd
     */
    public static final String WEB_FORMAT = "yyyy-MM-dd";

    /**
     * HHmmss
     */
    public static final String TIME_FORMAT = "HHmmss";

    /**
     * yyyyMM
     */
    public static final String MONTH_FORMAT = "yyyyMM";

    /**
     * yyyyMM
     */
    public static final String WEB_MONTH = "yyyy-MM";

    /**
     * yyyy年MM月dd日
     */
    public static final String CHINA_FORMAT = "yyyy年MM月dd日";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String LONG_WEB_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String LONG_WEB_FORMAT_NO_SEC = "yyyy-MM-dd HH:mm";

    private static String[] patterns = {LONG_FORMAT, LONG_WEB_FORMAT, WEB_FORMAT, SHORT_FORMAT};

    public static Date parse(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        for (String pattern : patterns) {
            try {
                return new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE).parse(s);
            } catch (ParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Unparseable date: \"" + s + "\"");
    }

    /**
     * 日期字符串解析成日期对象基础方法，可以在此封装出多种便捷的方法直接使用
     *
     * @param dateStr 日期字符串
     * @param format  输入的格式
     * @return 日期对象
     * @throws ParseException
     */
    public static Date parse(String dateStr, String format) {
        try {
            if (StringUtils.isBlank(format)) {
                throw new ParseException("format can not be null.", 0);
            }

            if (dateStr == null || dateStr.length() < format.length()) {
                throw new ParseException("date string's length is too small.", 0);
            }
            return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("parse excepion, dateStr=" + dateStr + ", format=" + format);
        }
    }

    /**
     * 计算两个日期相隔天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int interval(Date d1, Date d2) {
        return Math.abs(daysBetween(d1, d2));
    }

    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return (int) betweenDays;
    }

    /**
     * 指定日期前一定日期
     */
    public static Date getBeforeDateCompareDate(Date date, int hourfore, int daysBefore, int monthBefore) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        if (hourfore != 0) {
            today.add(Calendar.HOUR, 0 - hourfore);
        }
        if (daysBefore != 0) {
            today.add(Calendar.DATE, 0 - daysBefore);
        }
        if (monthBefore != 0) {
            today.add(Calendar.MONTH, 0 - monthBefore);
        }
        return today.getTime();
    }

    /**
     * 指定日期前一定日期
     */
    public static Date getBeforeMonths(Date date, int monthsBefore) {
        return getBeforeDateCompareDate(date, 0, 0, monthsBefore);
    }

    /**
     * 指定日期前一定日期
     */
    public static Date getBeforeDays(Date date, int daysBefore) {
        return getBeforeDateCompareDate(date, 0, daysBefore, 0);
    }

    /**
     * 指定日期前一定日期
     */
    public static Date getBeforeHours(Date date, int hourBefore) {
        return getBeforeDateCompareDate(date, hourBefore, 0, 0);
    }

    /**
     * 指定日期前一定日期
     */
    public static Date getAfterMinutes(Date date, int minuteAfter) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        if (minuteAfter != 0) {
            today.add(Calendar.MINUTE, minuteAfter);
        }
        return today.getTime();
    }

    public static Date format(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof String) {
            return parse((String) o);
        } else {
            throw new IllegalArgumentException("For input object: \"" + o + "\"");
        }
    }

    /**
     * 日期对象解析成日期字符串基础方法，可以据此封装出多种便捷的方法直接使用
     *
     * @param date   待格式化的日期对象
     * @param format 输出的格式
     * @return 格式化的字符串
     */
    public static String format(Date date, String format) {
        if (date == null || StringUtils.isBlank(format)) {
            return StringUtils.EMPTY;
        }

        return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE).format(date);
    }

    /**
     * 日期字符串格式化基础方法，可以在此封装出多种便捷的方法直接使用
     *
     * @param dateStr   日期字符串
     * @param formatIn  输入的日期字符串的格式
     * @param formatOut 输出日期字符串的格式
     * @return 已经格式化的字符串
     * @throws ParseException
     */
    public static String format(String dateStr, String formatIn, String formatOut) throws ParseException {

        Date date = parse(dateStr, formatIn);
        return format(date, formatOut);
    }

    /**
     * 格式化当前时间
     *
     * @param format 输出的格式
     * @return
     */
    public static String formatCurrent(String format) {
        if (StringUtils.isBlank(format)) {
            return StringUtils.EMPTY;
        }

        return format(new Date(), format);
    }

    /**
     * 把日期对象按照<code>yyyyMMdd</code>格式解析成字符串
     *
     * @param date 待格式化的日期对象
     * @return 格式化的字符串
     */
    public static String formatShort(Date date) {
        return format(date, SHORT_FORMAT);
    }

    /**
     * 把日期字符串按照<code>yyyyMMdd</code>格式，进行格式化
     *
     * @param dateStr  待格式化的日期字符串
     * @param formatIn 输入的日期字符串的格式
     * @return 格式化的字符串
     */
    public static String formatShort(String dateStr, String formatIn) throws ParseException {
        return format(dateStr, formatIn, SHORT_FORMAT);
    }

    /**
     * 把日期对象按照<code>yyyy-MM-dd</code>格式解析成字符串
     *
     * @param date 待格式化的日期对象
     * @return 格式化的字符串
     */
    public static String formatWeb(Date date) {
        return format(date, WEB_FORMAT);
    }

    /**
     * 把日期字符串按照<code>yyyy-MM-dd</code>格式，进行格式化
     *
     * @param dateStr  待格式化的日期字符串
     * @param formatIn 输入的日期字符串的格式
     * @return 格式化的字符串
     * @throws ParseException
     */
    public static String formatWeb(String dateStr, String formatIn) throws ParseException {
        return format(dateStr, formatIn, WEB_FORMAT);
    }

    /**
     * 把日期对象按照<code>yyyyMM</code>格式解析成字符串
     *
     * @param date 待格式化的日期对象
     * @return 格式化的字符串
     */
    public static String formatMonth(Date date) {

        return format(date, MONTH_FORMAT);
    }

    /**
     * 把日期对象按照<code>HHmmss</code>格式解析成字符串
     *
     * @param date 待格式化的日期对象
     * @return 格式化的字符串
     */
    public static String formatTime(Date date) {
        return format(date, TIME_FORMAT);
    }

    /**
     * 获取yyyyMMddHHmmss+n位随机数格式的时间戳
     *
     * @param n 随机数位数
     * @return
     */
    public static String getTimestamp(int n) {
        return formatCurrent(LONG_FORMAT) + RandomStringUtils.randomNumeric(n);
    }

    /**
     * 获取yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatFull() {
        return formatCurrent(LONG_FORMAT);
    }

    /**
     * 根据日期格式返回昨日日期
     *
     * @param format 日期格式
     * @return
     */
    public static String getYesterdayDate(String format) {
        return getDateCompareToday(format, -1, 0);
    }

    /**
     * 把当日日期作为基准，按照格式返回相差一定间隔的日期
     *
     * @param format     日期格式
     * @param daysAfter  和当日比相差几天，例如3代表3天后，-1代表1天前
     * @param monthAfter 和当日比相差几月，例如2代表2月后，-3代表3月前
     * @return
     */
    public static String getDateCompareToday(String format, int daysAfter, int monthAfter) {
        Calendar today = Calendar.getInstance();
        if (daysAfter != 0) {
            today.add(Calendar.DATE, daysAfter);
        }
        if (monthAfter != 0) {
            today.add(Calendar.MONTH, monthAfter);
        }
        return format(today.getTime(), format);
    }

    public static Date getCompareToday(String format, int daysAfter, int monthAfter) {
        Calendar today = Calendar.getInstance();
        if (daysAfter != 0) {
            today.add(Calendar.DATE, daysAfter);
        }
        if (monthAfter != 0) {
            today.add(Calendar.MONTH, monthAfter);
        }
        return today.getTime();
    }

    /**
     * 根据日期格式返回上月的日期
     *
     * @param format
     * @return
     */
    public static String getLastMonth(String format) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -1);
        return format(today.getTime(), format);
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static String getFirstDay(String format) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, 0);
        today.set(Calendar.DAY_OF_MONTH, 1);
        return format(today.getTime(), format);
    }

    /**
     * 指定时间的某一天
     *
     * @return
     */
    public static Date getDateSetDay(Date date, int day) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.set(Calendar.DAY_OF_MONTH, day);
        return today.getTime();
    }

    /**
     * 获取指定日期第一天
     *
     * @return
     */
    public static Date getMonthFirstDay(Date date, int month) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.add(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, today.getActualMinimum(Calendar.DAY_OF_MONTH));
        return today.getTime();
    }

    /**
     * 获取指定日期最后一天
     *
     * @return
     */
    public static Date getMonthLastDay(Date date, int month) {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.add(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, today.getActualMaximum(Calendar.DAY_OF_MONTH));
        return today.getTime();
    }

    /**
     * 获取日期的月份
     *
     * @return
     */
    public static int getDateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取日期的天数
     *
     * @return
     */
    public static int getDateDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定月第一天(以当前时间为准)
     *
     * @return
     */
    public static String getMonthFirstDay(int month, String format) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, 1);
        return format(today.getTime(), format);
    }

    /**
     * 平移当前时间，以分为单元，minutes
     *
     * @param minutes
     * @return
     */
    public static Date addCurMin(long minutes) {
        return DateUtils.addMinutes(new Date(), (int) minutes);
    }

    /**
     * 平移当前时间，以秒为单元，minutes
     *
     * @param secs
     * @return
     */
    public static Date addCurSeconds(long secs) {
        return addSeconds(new Date(), (int) secs);
    }

    /**
     * 平移当前时间，以秒为单元，minutes
     *
     * @param secs
     * @return
     */
    public static Date addCurSeconds(Date date, long secs) {
        return addSeconds(date, (int) secs);
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1 date1
     * @param date2 date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 获得当前时间秒数
     *
     * @return
     */
    public static int getCurrentTimeStamp() {
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
        return Integer.parseInt(timeStamp);
    }

    /**
     * 获得当前时间秒数
     *
     * @return
     */
    public static long getCurrentTimeStampLong() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 规范化日期，规范成yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    public static String timestamp2Datetime(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp * 1000));
    }

    public static Date timestamp2Date(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * yyyy-MM-dd HH:mm:ss转成int型数据
     *
     * @param datestr
     * @return
     */
    public static int date2TimestampInt(String datestr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long timestamp = date.getTime() / 1000;
        return Integer.parseInt(Long.toString(timestamp));
    }

    /**
     * yyyy-MM-dd HH:mm:ss转成int型数据
     *
     * @param datestr
     * @return
     */
    public static long date2TimestampLong(String datestr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long timestamp = date.getTime() / 1000;
        return timestamp;
    }

    public static int date2TimestampInt(String datestr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long timestamp = date.getTime() / 1000;
        return Integer.parseInt(Long.toString(timestamp));
    }

    public static String dateChnFormat(long timeStamp, String format) {
        return new SimpleDateFormat(format).format((new Date((timeStamp * 1000L))));
    }

    /**
     * @param date
     */
    public static long getCurrDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String str = sf.format(date);
        try {
            calendar.setTime(sf.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar.getTime().getTime() / 1000;
    }

    /**
     * @param date
     */
    public static long getCurrDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        String str = sf.format(date);
        try {
            calendar.setTime(sf.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar.getTime().getTime() / 1000;
    }

    public static String getMonthLastDay(int lastMonth) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, lastMonth); // 减一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String str = sf.format(calendar.getTime());
        return str;
    }

    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期的后几天
     *
     * @param day
     * @return
     */
    public static Date plusDay(int day) {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.plusDays(day).atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date getNextMonthFirstDay(int month) {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.plusMonths(month).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date getNextDayOfMonth(int month) {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.plusMonths(month).withDayOfMonth(month).atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date minusDay(int day) {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.minusDays(day).atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 判断当前时间是否在上半月(16号前都是上半月)
     *
     * @return true上半月，false下半月
     */
    public static boolean isHalfOfMonth() {
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth()).withDayOfMonth(16);
        return now.isBefore(start);
    }

    /**
     * 获取最近24个小时的日期，格式：yyyyMMddHH
     */
    public static List<String> getLast24h() {
        List<String> last24h = Lists.newArrayList(formatCurrent(SHORT_FORMATH));
        for (int i = 1; i <= 24; i++) {
            last24h.add(format(getBeforeHours(new Date(), i), SHORT_FORMATH));
        }
        return last24h;
    }

    public static List<String> getBeforeDate(String date) {
        List<String> lists = Lists.newArrayList();
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern(DateUtil.MONTH_FORMAT);
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern(DateUtil.SHORT_FORMAT);
        for (int i = 0; i < 6; i++) {
            String format = YearMonth.parse(date, monthFormat).minusMonths(i).atEndOfMonth().format(shortFormat);
            lists.add(format);
        }
        return lists;
    }

    public static String isBefore(String shortDate, String monthDate) {
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern(DateUtil.MONTH_FORMAT);
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern(DateUtil.SHORT_FORMAT);

        LocalDate monthLocalDate = YearMonth.parse(monthDate, monthFormat).atEndOfMonth();
        LocalDate shortLocalDate = LocalDate.parse(shortDate, shortFormat);

        if (monthLocalDate.isBefore(shortLocalDate)) {
            return monthLocalDate.format(monthFormat);
        } else {
            return shortLocalDate.format(monthFormat);
        }
    }

    public static List<String> getBeforeDate(String shortDate, String monthDate) {
        String before = isBefore(shortDate, monthDate);
        return getBeforeDate(before);
    }

    public static String getMonthFormat(String shortDate) {
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern(DateUtil.MONTH_FORMAT);
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern(DateUtil.SHORT_FORMAT);
        LocalDate shortLocalDate = LocalDate.parse(shortDate, shortFormat);
        return shortLocalDate.format(monthFormat);
    }

    public static String isBeforeShortdate(String shortDate, String monthDate) {
        DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern(DateUtil.SHORT_FORMAT);
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern(DateUtil.MONTH_FORMAT);

        LocalDate monthLocalDate = YearMonth.parse(monthDate, monthFormat).atEndOfMonth();
        LocalDate shortLocalDate = LocalDate.parse(shortDate, shortFormat);

        if (monthLocalDate.isBefore(shortLocalDate)) {
            return monthLocalDate.format(shortFormat);
        } else {
            return shortLocalDate.format(shortFormat);
        }
    }

    public static String dateBeginDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String formatDate = sdf.format(date);
        return formatDate;
    }

    public static String getMonthLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        // cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        // 获取某月最大天数
        //int lastDay = cal.getActualMaximum(Calendar.DATE);
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        // 设置日历中月份的最大天数
        //cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
//        int a = 10;
//        Date date = new Date();
//        Date lastDay = DateUtil.getMonthLastDay(date, a - a * 2);
//        System.out.println(DateUtil.format(lastDay));


        Date date = DateUtil.parse("20180409", DateUtil.SHORT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month0 = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String last = DateUtil.getMonthLastDay(year, month0);
        System.out.println(last);
        /*try {
            DateUtil.getMonthLastDay(new Date(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(getNextMonthFirstDay(1));
        System.out.println(getNextDayOfMonth(2));

        // LocalDate now2 = LocalDate.now();
        LocalDate now2 = LocalDate.of(2018, 5, 17);
        LocalDate now = LocalDate.of(2018, 5, 7);
        LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth()).withDayOfMonth(16);
        if (now2.isBefore(start)) {
            System.out.println("上半月");
        } else {
            System.out.println("下半月");
        }
        System.out.println(start);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.SHORT_FORMAT);
        System.out.println(now2.format(formatter));
        System.out.println(JSON.toJSONString(getLast24h()));*/

        //System.out.println(getBeforeDate("201807"));


        //DateTimeFormatter monthFormat=DateTimeFormatter.ofPattern(DateUtil.MONTH_FORMAT);
        //LocalDate.parse()
        //LocalDate parse = LocalDate.parse("20180701", monthFormat);
        //String format = LocalDate.now().format(monthFormat);
        // System.out.println(format);
        //DateTimeFormatter  dateTimeFormatterBuilder = new DateTimeFormatterBuilder().append(monthFormat).parseDefaulting(ChronoField., 0).toFormatter();
        //DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMM");
        //LocalDate dt = LocalDate.parse("201402", formatter1);
        // LocalDate dt = YearMonth.parse("201402", formatter1).atEndOfMonth();
        //System.out.println(dt);
        //System.out.println(dt.format(formatter1));


       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate localDate = LocalDate.parse("201807", formatter);

        System.out.println(localDate);*/


        System.out.println(getBeforeDate("201807"));

    }

    /**
     * 获取上个月的最后一天
     *
     * @param
     * @return
     */
    public static Date getBeforeMonthLastDay(Date date) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MONTH, -1);
        return DateUtil.getMonthLastDay(today.getTime(), 0);
    }


    /**
     * 获取当前时间是星期几
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取当前时间小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) - 1;
    }

    /**
     * 获取指定时间的周一
     *
     * @param date
     * @return
     */
    public static Date getLastSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 获取任意时间的周六
     *
     * @param date
     * @return
     */
    public static Date getSaturday(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        return calendar.getTime();
    }

    /**
     * 比较两个日期是否为最新
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isNewDate(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long timeGap = time1 - time2;
        if (timeGap >= 0) {
            return true;
        }
        return false;
    }
}
