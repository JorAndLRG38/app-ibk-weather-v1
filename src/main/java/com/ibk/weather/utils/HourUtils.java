package com.ibk.weather.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class HourUtils {

    private HourUtils() {}

    public static int currentSlot() {
        int hour = LocalDateTime.now().getHour();
        return hour / 3;
    }

    public static long secondsUntilHourSlotEnd() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();

        int currentSlot = hour / 3;
        int slotEndHour = (currentSlot + 1) * 3;
        if (slotEndHour >= 24) slotEndHour = 24;

        LocalDateTime end = now.withHour(slotEndHour).withMinute(59).withSecond(59).withNano(999);
        long seconds = Duration.between(now, end).getSeconds();
        return Math.max(seconds, 1L);
    }

    public static long nowEpochSeconds() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

}