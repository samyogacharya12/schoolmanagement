package com.example.schoolmanagement.util;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
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

    public static boolean format(String date) {
        if(Objects.isNull(date) || StringUtils.isBlank(date)){
            return true;
        }
        if(DATE_PATTERN_First.matcher(date).matches() || DATE_PATTERN_Second.matcher(date).matches() || DATE_PATTERN_Third.matcher(date).matches() ||
            DATE_PATTERN_Fourth.matcher(date).matches()) {
            return true;
        }
        return false;
    }
}
