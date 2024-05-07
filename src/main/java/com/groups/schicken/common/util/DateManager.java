package com.groups.schicken.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateManager {
    public static String getTodayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }

    public static String getTodayDateTime(String pattern) {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return today.format(formatter);
    }

    public static String getTodayDateTime(){
        return getTodayDateTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String dateParsing(String target, String oldPattern, String newPattern) {
        SimpleDateFormat op = new SimpleDateFormat(oldPattern);
        SimpleDateFormat np = new SimpleDateFormat(newPattern);

        String result = "0000-00-00 00:00";
        try {
            result = np.format(op.parse(target));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        return result;
    }
}
