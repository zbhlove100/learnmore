package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.Setting;

public class MyDateUtils {
    public static final long secondPerHour = 3600000l;
    public static final long secondPerDay = 86400000l;
    public static final long secondPerMonth = 2678400000l;
    public static final long secondPerYear = 31536000000l;
    public static int getAgeForBirthday(String birthday,String dateFormat) throws ParseException{
        int differyear = getDaysSinceNow(birthday,dateFormat)/365;
        return differyear;
    }
    public static int getDaysSinceNow(String birthday,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        long now = new Date().getTime();
        long birthtime = sdf.parse(birthday).getTime();
        int secondPerDay = 86400000;
        int differDays = (int) (now/secondPerDay - birthtime/secondPerDay);
        return differDays;
    }
    public static int getDifferDays(String date1,String date2,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        long d1 = sdf.parse(date1).getTime();
        long d2 = sdf.parse(date2).getTime();
        int secondPerDay = 86400000;
        int differDays = (int)((d1/secondPerDay - d2/secondPerDay)>0?(d1/secondPerDay - d2/secondPerDay):(d2/secondPerDay - d1/secondPerDay));
        return differDays;
    }
    public static String getClassStatus(String startTime,String endTime,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        long d1 = sdf.parse(startTime).getTime();
        long d2 = sdf.parse(endTime).getTime();
        long now = new Date().getTime();
        String result = Setting.value("LESSON_FINISH", "结束");
        if(now-d1<0){
            result = Setting.value("LESSON_UNSTART", "未开始");
        }else if((now-d1>0)&&(now-d2<0)){
            result = Setting.value("LESSON_FINISH", "进行中");
        }
        return result;
    }
    public static Map getYearAndMonthSinceNow(Date date,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return getYearAndMonthSinceNow(sdf.format(date),dateFormat);
    }
    public static Map getYearAndMonthSinceNow(String date,String dateFormat) throws ParseException{
        Map result = new HashMap<String, Object>();
        int days = getDaysSinceNow(date,dateFormat);
        int years = days/365;
        int months =  (days%365)/30;
        result.put("year", years);
        result.put("month", months);
        return result;
    }
}
