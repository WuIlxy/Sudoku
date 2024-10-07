package org.cis1200.Sudoku;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GameTimer {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static Date time;

    static void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        time = calendar.getTime();
    }

    static String getTime() {
        return DATE_FORMAT.format(time.getTime());
    }

    static void updateTime() {
        time.setTime(time.getTime() + 1000);
    }
}