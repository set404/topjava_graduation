package ru.javaops.topjava2.util;

import java.time.LocalTime;

public class DateTimeUtil {
    private static final LocalTime expiredTime = LocalTime.of(11, 0, 0);

    public static LocalTime getExpiredTime() {
        return expiredTime;
    }
}
