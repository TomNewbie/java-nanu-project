package ws2022.Client.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalculateAge {
    public static int getAge(LocalDate birthday) {

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) - birthday.getYear();
    }

}
