package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Setting;

public class MyDateUtils {
    public int getAgeForBirthday(String birthday,String dateFormat) throws ParseException{
        int differyear = getDaysSinceNow(birthday,dateFormat)/365;
        return differyear;
    }
    public int getDaysSinceNow(String birthday,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        long now = new Date().getTime();
        long birthtime = sdf.parse(birthday).getTime();
        int secondPerDay = 86400000;
        int differDays = (int) (now/secondPerDay - birthtime/secondPerDay);
        return differDays;
    }
    public int getDifferDays(String date1,String date2,String dateFormat) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        long d1 = sdf.parse(date1).getTime();
        long d2 = sdf.parse(date2).getTime();
        int secondPerDay = 86400000;
        int differDays = (int)((d1/secondPerDay - d2/secondPerDay)>0?(d1/secondPerDay - d2/secondPerDay):(d2/secondPerDay - d1/secondPerDay));
        return differDays;
    }
    public String getClassStatus(String startTime,String endTime,String dateFormat) throws ParseException{
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
}
