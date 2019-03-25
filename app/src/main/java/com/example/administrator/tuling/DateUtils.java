package com.example.administrator.tuling;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 * 格式化日期时间的工具类,用于显示时间
 */

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");//从传入的date格式转换成yyyy-MM-dd  HH:mm:ss"格式
        return df.format(date);
    }
}
