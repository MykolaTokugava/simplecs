/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.metlife.claims.simplecs.processing;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

import java.util.*;

/**
 *
 * @author NPopov
 */
public class DateTools {

    public static Integer getCurrentYear() {
        int YearNow;
        Calendar cal = new GregorianCalendar();
        YearNow = cal.get(Calendar.YEAR);
        return YearNow;
    }

    public static String getDateNowYmd() {
        String res = "";
        int YearNow, MonthNow, dayNow;
        Calendar cal = new GregorianCalendar();
        YearNow = cal.get(Calendar.YEAR);
        MonthNow = cal.get(Calendar.MONTH) + 1;
        dayNow = cal.get(Calendar.DAY_OF_MONTH);
        res = YearNow + "" + Convert.zerroConver(MonthNow) + "" + Convert.zerroConver(dayNow);
        return res;
    }

    public static String getDateNowYmdF() {
        String res = "";
        int YearNow, MonthNow, dayNow;
        Calendar cal = new GregorianCalendar();
        YearNow = cal.get(Calendar.YEAR);
        MonthNow = cal.get(Calendar.MONTH) + 1;
        dayNow = cal.get(Calendar.DAY_OF_MONTH);
        res = Convert.zerroConver(dayNow) + "." + Convert.zerroConver(MonthNow) + "." + YearNow;
        return res;
    }

    public static String getDateNowYmdHms() {
        String res = "";
        int YearNow, MonthNow, dayNow, h, m, s;
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Kiev"));
        YearNow = cal.get(Calendar.YEAR);
        MonthNow = cal.get(Calendar.MONTH) + 1;
        dayNow = cal.get(Calendar.DAY_OF_MONTH);
        h = cal.get(Calendar.HOUR_OF_DAY);
        m = cal.get(Calendar.MINUTE);
        s = cal.get(Calendar.SECOND);
        res = YearNow + "" + Convert.zerroConver(MonthNow) + "" + Convert.zerroConver(dayNow) + "" + Convert.zerroConver(h) + "" + Convert.zerroConver(m) + "" + Convert.zerroConver(s);
        //S.p("getDateNowYmdHms = " + res);
        return res;
    }

    public static double durationCalc(String dateFrom, String dateTo, String type) {
        double res = 0;
        try {
            DateTime dFrom = getDateTime(dateFrom);
            DateTime dTo = getDateTime(dateTo);
            long diffDays = Days.daysBetween(dFrom, dTo).getDays();
            long diffMonth = Months.monthsBetween(dFrom, dTo).getMonths();
            long diffYears = Years.yearsBetween(dFrom, dTo).getYears();
            if (type.equals("days")) {
                return diffDays;
            } else if (type.equals("month")) {
                return diffMonth;
            } else if (type.equals("year")) {
                return diffYears;
            }
        } catch (Exception e) {
            System.out.println("incorrect date = " + dateFrom + " or " + dateTo);
            res = -1000;
        }
        return res;
    }

    public static Map getDataArrayStringToInt(String data) {
        Map res = new TreeMap();

        int year, month, day;
        data = data.trim();
        if (data.length() != 8) {
            return res;
        }
        try {
            year = Integer.parseInt(String.valueOf(data.substring(0, 4)));
            month = Integer.parseInt(String.valueOf(data.substring(4, 6)));
            day = Integer.parseInt(String.valueOf(data.substring(6, 8)));
            res.put("year", year);
            res.put("month", month);
            res.put("day", day);
        } catch (Exception ex) {
            return res;
        }
        return res;
    }

    //DateTime
    public static DateTime getDateTime(String date) {
        DateTime res = new DateTime();
        Map dateMap = new TreeMap();
        try {
            dateMap = (Map) getDataArrayStringToInt(date);
        } catch (Exception ex) {
        }
        if (dateMap.size() > 0) {
            res = new DateTime(Integer.parseInt(String.valueOf(dateMap.get("year"))), Integer.parseInt(String.valueOf(dateMap.get("month"))), Integer.parseInt(String.valueOf(dateMap.get("day"))), 0, 0, 0, 0);
        }
        return res;
    }

    //определяем дату вычитаением/сложением дней (shift - количество дней, если минус то вычитаем)
    public static String getDateShift(int shift, String date) {
        String res = "";
        int Year = 0, Month = 0, Day = 0;
        try {
            Year = Integer.parseInt(String.valueOf(date.substring(0, 4)));
            Month = Integer.parseInt(String.valueOf(date.substring(4, 6)));
            Day = Integer.parseInt(String.valueOf(date.substring(6, 8)));
            //f.p("shift = " +shift+ " date = " + date + " Year = " + Year + " Month = " + Month + " Day = " +  Day);
            DateTime dateNow = new DateTime(Year, Month, Day, 0, 0, 0, 0);
            Calendar calNew = new GregorianCalendar();
            calNew.setTimeInMillis(dateNow.getMillis());
            calNew.set(calNew.get(Calendar.YEAR), calNew.get(Calendar.MONTH), calNew.get(Calendar.DAY_OF_MONTH) + shift, 0, 0, 0);
            res = calNew.get(Calendar.YEAR) + "" + Convert.zerroConver((calNew.get(Calendar.MONTH) + 1)) + "" + Convert.zerroConver(calNew.get(Calendar.DAY_OF_MONTH));
        } catch (Exception ex) {
        }
        return res;
    }

    //определяем дату вычитаением/сложением дней (shift - количество дней, если минус то вычитаем)
    public static String getDateShift__(int shift, Date date) {
        String res = "";
        try {
            DateTime dateNow = new DateTime(date.getYear(), date.getMonth(), date.getDay(), 0, 0, 0, 0);
            Calendar calNew = new GregorianCalendar();
            calNew.setTimeInMillis(dateNow.getMillis());
            calNew.set(calNew.get(Calendar.YEAR), calNew.get(Calendar.MONTH), calNew.get(Calendar.DAY_OF_MONTH) + shift, 0, 0, 0);
            res = calNew.get(Calendar.YEAR) + "" + Convert.zerroConver((calNew.get(Calendar.MONTH) + 1)) + "" + Convert.zerroConver(calNew.get(Calendar.DAY_OF_MONTH));
        } catch (Exception ex) {
        }
        return res;
    }

    public static String getDateShiftMonth(int shift, String date) {
        String res = "";
        int Year = 0, Month = 0, Day = 0;
        try {
            Year = Integer.parseInt(String.valueOf(date.substring(0, 4)));
            Month = Integer.parseInt(String.valueOf(date.substring(4, 6)));
            Day = Integer.parseInt(String.valueOf(date.substring(6, 8)));
            DateTime dateNow = new DateTime(Year, Month, Day, 0, 0, 0, 0);
            Calendar calNew = Calendar.getInstance(TimeZone.getTimeZone("GMT+02:00"));
            calNew.set(Year, Month - 1, Day);
            calNew.add(Calendar.MONTH, shift);
            calNew.set(calNew.get(Calendar.YEAR), (calNew.get(Calendar.MONTH)), calNew.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            res = calNew.get(Calendar.YEAR) + "" + Convert.zerroConver(calNew.get(Calendar.MONTH) + 1) + "" + Convert.zerroConver(calNew.get(Calendar.DAY_OF_MONTH));
        } catch (Exception ex) {
        }
        return res;
    }

    public static boolean dateFirstDayMonth(String date) {
        int dayPlusPeriodDate = (Integer) DateTools.getDataArrayStringToInt(date).get("day");
        if (dayPlusPeriodDate == 1) {
            return true;
        }
        return false;
    }

    public static Map<String, String> createPeriods(String dateBegin, int counter) {
        Map<String, String> res = new TreeMap();
        String dateStart = dateBegin;
        int period = 12 / counter;
        boolean dayOne = DateTools.dateFirstDayMonth(dateBegin);
        String begin = "";
        String end = "";
        for (int i = 1; i <= counter; i++) {

            int shift = i * period;

            if (dayOne == true) {
                begin = dateStart;
                end = DateTools.getDateShiftMonth(period, begin);
                end = end.substring(0, 6) + "01";
                dateStart = end;
                end = DateTools.getDateShift(-1, end);
            } else {
                if (i == 1) {
                    begin = dateStart;
                    end = DateTools.getDateShift(-1, DateTools.getDateShiftMonth(period, begin));
                    //System.out.println("begin = " + begin +" end = " + end);
                } else {
                    begin = DateTools.getDateShift(1, end);
                    end = DateTools.getDateShift(-1, DateTools.getDateShiftMonth(shift, dateStart));
                    //System.out.println("begin = " + begin +" end = " + end);
                }
            }

            res.put(begin, end);
        }

        return res;
    }

}
