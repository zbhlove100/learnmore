package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
