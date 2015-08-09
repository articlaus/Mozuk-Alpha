package com.model.util;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tseegii on 8/10/14.
 */
public class DataTypeUtils {

    private static final DecimalFormat decimalFormatDouble = new DecimalFormat("#,###,###.00");
    private static final DecimalFormat decimalFormatInt = new DecimalFormat("#,###,###");

    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    private static final SimpleDateFormat dateFormatDate = new SimpleDateFormat("YYYY-MM");

    public static String doubleToString(Double d) {
        if (d == null) {
            return "0.00";
        }
        return decimalFormatDouble.format(d);
    }

    public static String intToString(Integer i) {
        if (i == null) {
            return "0";
        }
        return decimalFormatInt.format(i);
    }

    public static Date convertToDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DataTypeUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Date convertToDateTime(String dateTime) {
        try {
            return dateTimeFormat.parse(dateTime);
        } catch (ParseException ex) {
            Logger.getLogger(DataTypeUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String convertToDateString(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }

    public static String convertToDateMonthString(Date date) {
        if (date == null) {
            return null;
        }
        return dateFormatDate.format(date);
    }

    public static String convertToDateTimeString(Date date) {
        if (date == null) {
            return null;
        }
        return dateTimeFormat.format(date);
    }

    public static int daysBetween(Date start, Date end) {
        int days = Days.daysBetween(new LocalDate(start), new LocalDate(end)).getDays();
        return days;
    }

    public static double calculateHours(String startTimeStr, String endTimeStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        DateTime startTime = formatter.parseDateTime(startTimeStr);
        DateTime endTime = formatter.parseDateTime(endTimeStr);
        int startHour = startTime.getHourOfDay();
        int startMinute = startTime.getMinuteOfHour();
        int endHour = endTime.getHourOfDay();
        int endMinute = endTime.getMinuteOfHour();
        int hoursBetween = endHour - startHour;
        int minuteBetween = endMinute - startMinute;
        int minute = hoursBetween * 60 + minuteBetween;
        DecimalFormat df = new DecimalFormat("###.##");
        return Double.valueOf(df.format(minute / 60d));
    }

    /**
     * Өгсөн огноон дээр жил нэмээд буцаана
     *
     * @param date  java.util.Date Огноо
     * @param years параметер хасах утга бол огнооноос жил хасна
     * @return өөчлөгдсөн огноо
     * @see org.joda.time.DateTime plusYears(int years)
     */
    public static Date addYearsToDate(Date date, int years) {
        if (years == 0) {
            return date;
        }
        DateTime jodaDateTime = new DateTime(date);
        return jodaDateTime.plusYears(years).toDate();
    }

    /**
     * Өгсөн огноон дээр сар нэмээд буцаана
     *
     * @param date   java.util.Date Огноо
     * @param months параметер хасах утга бол огнооноос сар хасна
     * @return сар нэмэгдсэн өдөр
     * @see org.joda.time.DateTime plusMonths(int months)
     */
    public static Date addMonthsToDate(Date date, int months) {
        DateTime jodaDateTime = new DateTime(date);
        return jodaDateTime.plusMonths(months).toDate();
    }

    /**
     * Өгсөн огноон дээр өдөр нэмээд буцаана
     *
     * @param date java.util.Date Огноо
     * @param days параметер хасах утга бол огнооноос өдөр хасна
     * @return
     * @see org.joda.time.DateTime plusMonths(int days)
     */
    public static Date addDaysToDate(Date date, int days) {
        DateTime jodaDateTime = new DateTime(date);
        return jodaDateTime.plusDays(days).toDate();
    }

    public static int extractYear(Date date) {
        return new DateTime(date).getYear();
    }

    public static int extractMonth(Date date) {
        return new DateTime(date).getMonthOfYear();
    }

    /**
     * Өгсөн огноонуудын хооронд хэдэн сар байгааг олно.
     * Зөвхөн сарын зөрүүгээр, өдөр цаг минут сек хамаарахгүй Жн:
     * 2012.12.31-2013.01.01 гэж өгвөл 1-ийг буцаана.
     *
     * @param date1 огноо1
     * @param date2 огноо2
     * @return сарын зөрүү
     */
    public static int monthDiffByMonths(Date date1, Date date2) {
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);

        Months m = Months.monthsBetween(d1.withDayOfMonth(1).withTimeAtStartOfDay()
                , d2.withDayOfMonth(1).withTimeAtStartOfDay());

        return m.getMonths();
    }

    /**
     * Өгсөн огноонуудын хооронд хэдэн сар байгааг олно.
     * Хэрэв diffByMonth-н утга true бол зөвхөн сарын зөрүүгээр,
     * өдөр цаг минут сек хамаарахгүй Жн: 2012.12.31, 2013.01.01 гэж өгвөл 1-ийг
     * буцаана.
     *
     * @param date1 огноо1
     * @param date2 огноо2
     * @param diffByMonth зөвхөн сарын зөрүүг олох эсэх
     * @return сарын зөрүү
     */
    public static int monthDiff(Date date1, Date date2, boolean diffByMonth) {
        DateTime d1 = new DateTime(date1);
        DateTime d2 = new DateTime(date2);

        if (diffByMonth) {
            d1 = d1.withDayOfMonth(1).withTimeAtStartOfDay();
            d2 = d2.withDayOfMonth(1).withTimeAtStartOfDay();
        }

        Months m = Months.monthsBetween(d1, d2);

        return m.getMonths();
    }

    private static final Pattern EMAIL_ADDRESS_REGEX_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean checkEmail(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        Matcher matcher = EMAIL_ADDRESS_REGEX_PATTERN.matcher(value);
        boolean valid = matcher.find();
        if (valid) {
            return true;
        } else {
            return false;
        }
    }

}
