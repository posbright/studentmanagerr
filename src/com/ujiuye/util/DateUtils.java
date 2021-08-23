package com.ujiuye.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期解析工具类
 */
public class DateUtils {

    /**
     * 将字符串类型的日期转成日期类型
     * @return 日期对象
     */
    public static Date StringTransferDate(String date,String pattern){

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("日期转换异常 = " + e);
            e.printStackTrace();
        }
        return date1;
    }
}
