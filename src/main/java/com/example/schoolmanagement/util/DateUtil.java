package com.example.schoolmanagement.util;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
    private static Pattern DATE_PATTERN_First = Pattern.compile(
        "^\\d{4}-\\d{2}-\\d{2}$");

    private static Pattern DATE_PATTERN_Second = Pattern.compile(
        "^\\d{2}-\\d{2}-\\d{4}$");

    private static Pattern DATE_PATTERN_Third = Pattern.compile(
        "^\\d{4}\\/\\d{2}\\/\\d{2}$");

    private static Pattern DATE_PATTERN_Fourth = Pattern.compile(
        "^\\d{2}\\/\\d{2}\\/\\d{4}$");

    private static final Pattern RANGE_DATE_PATTERN = Pattern.compile(
        "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");


    public static boolean format(String date) {
        if (DATE_PATTERN_First.matcher(date).matches() || DATE_PATTERN_Second.matcher(date).matches() || DATE_PATTERN_Third.matcher(date).matches() ||
            DATE_PATTERN_Fourth.matcher(date).matches() && RANGE_DATE_PATTERN.matcher(date).matches()) {
            return true;
        }
        return false;
    }
}
