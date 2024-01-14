package cn.langya.sun.utils.misc;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class TimeUtil {

    private long time = -1L;

    public boolean hasTimePassed(final long MS) {
        return System.currentTimeMillis() >= time + MS;
    }

    public long hasTimeLeft(final long MS) {
        return (MS + time) - System.currentTimeMillis();
    }

    public void reset() {
        time = System.currentTimeMillis();
    }

    public static String getTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        return formatter.format(time);
    }
}
