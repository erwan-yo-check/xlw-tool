package org.xlw.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/3 22:09
 */
public class DateUtils {

    /**
     * 根据格式获取当前格式化时间
     * @param format 格式化方式，基础格式为yyyy-MM-dd HH:mm:ss
     * @return 当前时间
     */
    public static String getCurrentTimeByFormat(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 格式化时间
     * @param format 格式化格式，基础格式为yyyy-MM-dd HH:mm:ss
     * @param time
     * @return
     */
    public static String formatTime(String format, long time)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }

    /**
     * @desc 间隔时间
     *
     * @date 2023/6/3 22:58
     * @param time
     * @return java.lang.String
     * @throws
     * @since
    */
    public static String formatDuring(long time)
    {
        long hour = time /1000/60/60;
        long min = (time - hour*1000*60*60)/1000/60;
        long second = (time - hour*1000*60*60 - min*1000*60)/1000;
        return hour + "小时 " + min + "分 " + second + "秒";
    }

    /**
     * @desc String转化为Date
     *
     * @date 2023/6/5 14:48
     * @param date string
     * @param format 格式 "yyyy-MM-dd HH:mm:ss"
     * @return java.util.Date
     * @throws
     * @since
    */
    public static Date formatDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @desc 格式"yyyy-MM-dd HH:mm:ss"
     *
     * @date 2023/6/5 14:50
     * @param date
     * @return java.util.Date
     * @throws
     * @since
    */
    public static Date formatDate(String date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
}

