package ua.metlife.claims.simplecs.processing;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;

@Slf4j
public class Convert {

    private Convert() {

    }
    public static final String DATE_FORMAT_UA = "dd.MM.yyyy";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT_BASE = "yyyyMMdd";
    public static final String DATE_FORMAT_TABLE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_EXCEL = "yyyy.MM.dd";
    public static final String _BD_FORMAT = "%,.2f";

    /**
     * Converts, if possible, string of format yyyyMMdd into a Date value.
     */
    public static Date stringToDate(String value) {
        if (value == null) {
            return null;
        }

        String toParse = value.trim();
        if (toParse.isEmpty()) {
            return null;
        }

        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            return df.parse(toParse.trim());
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    String formatDate(String format, Date visited) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(visited);
    }

    //--------------------------------------------------------------------------
    /**
     * Converts string to a Date value heuristically. In essence, this means
     * that this function accepts string "dd.MM.yyyy" and "dd/MM/yyyy".
     */
    public static Date stringToDateHeur(String value) {

        String format1 = "dd.MM.yyyy";
        String format2 = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
        sdf1.setLenient(false);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
        sdf2.setLenient(false);

        try {
            return sdf1.parse(value);
        } catch (ParseException ex) {
            try {
                return sdf2.parse(value);
            } catch (ParseException exx) {
                exx.printStackTrace();
                return null;
            }
        }
    }

    //--------------------------------------------------------------------------
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }

        return (new SimpleDateFormat(DATE_FORMAT)).format(date);
    }

    public static String dateToStringFormat(Date date, String format) {
        if (date == null) {
            return "";
        }

        return (new SimpleDateFormat(format)).format(date);
    }

    //--------------------------------------------------------------------------
    private static final SimpleDateFormat EXCEL_DATE_FMT
        = new SimpleDateFormat("yyyy-MM-dd");

    public static String dateToExcelFriendlyString(Date date) {
        if (date == null) {
            return null;
        }

        return EXCEL_DATE_FMT.format(date);
    }

    public static String dateToExcelFriendlyStringFormat(Date date, String formatString) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat _DATE_FMT
            = new SimpleDateFormat(formatString);

        return _DATE_FMT.format(date);
    }

    //--------------------------------------------------------------------------
    public static BigDecimal stringToBigDecimal(String value) {
        return stringToBigDecimal(value, true);
    }

    //--------------------------------------------------------------------------
    private static final DecimalFormat moneyFormat;

    static {
        moneyFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        moneyFormat.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setDecimalSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        moneyFormat.setDecimalFormatSymbols(dfs);
        moneyFormat.setParseBigDecimal(true);
    }

    public static BigDecimal stringToBigDecimal(String value,
        boolean zeroOnFailure) {
        if (value == null || value.isEmpty()) {
            return zeroOnFailure ? BigDecimal.ZERO : null;
        }

        String s = cleanupMoneyString(value);

        try {
            BigDecimal bd = (BigDecimal) moneyFormat.parse(s);
            return bd;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return zeroOnFailure ? BigDecimal.ZERO : null;
        }
    }

    //--------------------------------------------------------------------------
    public static String bigDecimalToString(BigDecimal value) {
        return value != null ? String.format("%.4f", value) : null;
    }

    //--------------------------------------------------------------------------
    private static String cleanupMoneyString(String val) {
        // implementation is ugly, but that's all we can do
        char dot = '.';
        char comma = ',';
        char minus = '-';
        char plus = '+';

        // deleteing whitespaces, and replacing commans with dots
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < val.length(); i++) {
            char ch = val.charAt(i);
            if (Character.isDigit(ch) || ch == dot || ch == comma || ch == minus || ch == plus) {
                sb.append(ch != comma ? ch : dot);
            }
        }

        return sb.toString();
    }

    //--------------------------------------------------------------------------
    public static String checkString(String value, int length) {
        if (value == null) {
            value = "";
        }
        String res = value.trim();
        if (res.length() > length) {
            res = res.substring(0, length);
        }

        return res;
    }

    public static String uaString(String replaceFrom, String replaceTo, String value) {
        if (value == null) {
            value = "";
        }

        return value.replaceAll(replaceFrom, replaceTo);
    }

    public static String getUaString(String value) {

        value = uaString("#Г#", "Ґ", value);
        value = uaString("#г#", "ґ", value);

        return value;
    }

    //--------------------------------------------------------------------------
    public static String checkObjectToString(Object value, int length) {
        String res = (String) value.toString().trim();
        if (value == null) {
            res = "";
        } else {
            res = checkString((String) value.toString().trim(), length);
        }
        res = res.replaceAll("’", "'");
        //res = res.replaceAll("'", "");
        res = res.replaceAll("'", "''");
        return res;
    }


    public static String fromBaseToString(String value) {
        String res = "";
        if (value == null) {
            res = "";
        } else {
            res = value;
        }
        res = res.replaceAll("''", "'");
        return res;
    }

    public static double stringToDouble(String value) {
        double res = 0;
        try {
            if (value == null || value.trim().length() == 0) {
                return 0;
            } else {
                return Double.parseDouble(String.valueOf(value));
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    //--------------------------------------------------------------------------
    // Проверить допустимость введенной даты
    public static boolean isValidDate(String inputValue) {
        Calendar cal = new GregorianCalendar();
        cal.setLenient(false);
        cal.clear();
        // Разобрать строку на три составляющие (день, месяц, год)
        try {
            int y = Integer.parseInt(inputValue.substring(0, 4));
            int m = Integer.parseInt(inputValue.substring(4, 6));
            int d = Integer.parseInt(inputValue.substring(6, 8));
            System.out.println("inputValue = " + inputValue + " y = " + y + " m = " + m + " d = " + d);
            cal.set(y, m - 1, d);
            Date dt = cal.getTime();
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    //--------------------------------------------------------------------------
    public static String stringToDateFormatString(String formatBase, String formatOut, String value) {
        if (value == null) {
            return "";
        }

        SimpleDateFormat STRING_TO_DATE = new SimpleDateFormat(formatBase);
        SimpleDateFormat DATE_TO_STRING = new SimpleDateFormat(formatOut);
        try {
            Date stringToDate = STRING_TO_DATE.parse(value);
            return DATE_TO_STRING.format(stringToDate);

        } catch (ParseException ex) {
            log.error("Failed to parse date in Convert.stringToDateFormatString(String formatBase, String formatOut, String value)" + ex.getMessage());
            return "";
        }
    }

    //------------------------------------------------------------------------
    public static Date stringToDateFormat(String formatBase, String value) {
        SimpleDateFormat STRING_TO_DATE = new SimpleDateFormat(formatBase);
        try {
            Date stringToDate = STRING_TO_DATE.parse(value);
            return stringToDate;

        } catch (Exception ex) {
            return null;
        }
    }

    public static Date stringToDateFormat2(String formatBase, String value) {
        SimpleDateFormat STRING_TO_DATE = new SimpleDateFormat(formatBase);

        try {
            Date stringToDate = STRING_TO_DATE.parse(value);
            return stringToDate;

        } catch (Exception ex) {
            return null;
        }
    }

    public static void getOpenExcFile(String filePathName) {
        try {
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + filePathName);
        } catch (Exception ex) {
        }
    }

    public static boolean isNullEmpty(Object var) {
        if (var == null) {
            return true;
        } else {
            String res = (String) String.valueOf(var).trim();
            if (res.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static BigDecimal getBigDecimal(Object value, Boolean returnZero, Boolean returnErrorMsg) {
        BigDecimal ret = null;
        //System.out.print("class = " + value.getClass().getName());
        if (value == null) {
            return BigDecimal.ZERO;
        }
        try {

            if (value != null) {
                if (value instanceof BigDecimal) {
                    ret = ((BigDecimal) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof String) {
                    ret = new BigDecimal((String) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof BigInteger) {
                    ret = new BigDecimal((BigInteger) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof Number) {
                    ret = new BigDecimal(((Number) value).doubleValue()).setScale(6, RoundingMode.HALF_UP);
                } else {
                    ret = BigDecimal.ZERO.setScale(6);
                    //throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
                }
            } else {
                if (returnZero == true) {
                    ret = BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP);
                }
            }
        } catch (Exception ex) {
            if (returnErrorMsg == true) {
                JOptionPane.showMessageDialog(null, "Incorrect value");
            }
            return BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP);
        }
        return ret;
    }

    public static BigDecimal isBigDecimal(Object value) {
        BigDecimal decValue = new BigDecimal(0);
        try {

            if (value != null) {
                if (value instanceof BigDecimal) {
                    decValue = ((BigDecimal) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof String) {
                    decValue = new BigDecimal((String) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof BigInteger) {
                    decValue = new BigDecimal((BigInteger) value).setScale(6, RoundingMode.HALF_UP);
                } else if (value instanceof Number) {
                    decValue = new BigDecimal(((Number) value).doubleValue()).setScale(6, RoundingMode.HALF_UP);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }

        return decValue;

    }

    public static BigDecimal stringToBigDecimalConvert(String value, int sc) {
        BigDecimal ret = null;

        try {
            ret = new BigDecimal((String) value).setScale(sc, RoundingMode.HALF_UP);
        } catch (Exception ex) {
            return BigDecimal.ZERO.setScale(sc, RoundingMode.HALF_UP);
        }

        return ret;
    }

    public static Integer getInteger(Object value, Boolean returnZero) {
        Integer ret = null;
        if (value != null) {
            if (value instanceof Integer) {
                ret = (Integer) value;
            } else if (value instanceof String) {
                ret = new Integer((String) value);
            } else if (value instanceof BigInteger) {
                ret = new Integer((Integer) value);
            } else if (value instanceof Number) {
                ret = new Integer(((Number) value).intValue());
            } else {
                ret = 0;
            }
        } else {
            if (returnZero == true) {
                ret = 0;
            }
        }
        return ret;
    }

    public static int isInteger(Object value) {
        int result = 0;
        if (value != null) {
            if (value instanceof Integer) {
                result = (Integer) value;
            } else if (value instanceof String) {
                result = new Integer((String) value);
            } else if (value instanceof BigInteger) {
                result = new Integer((Integer) value);
            } else if (value instanceof Number) {
                result = new Integer(((Number) value).intValue());
            }
        } else {
            result = 0;
        }

        return result;
    }

    public static boolean isIntegerTrue(Object value) {
        boolean result = true;
        try {
            if (value != null) {
                if (value instanceof Integer) {
                    return true;
                } else if (value instanceof String) {
                    new Integer((String) value);
                } else if (value instanceof BigInteger) {
                    new Integer((Integer) value);
                } else if (value instanceof Number) {
                    new Integer(((Number) value).intValue());
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

        return result;
    }

    public static String zerroConver(int val) {
        String val2 = null;
        if (val < 10) {
            val2 = "0" + String.valueOf(val);
        } else {
            val2 = String.valueOf(val);
        }
        return val2;
    }

    public static double DoFormat(double dbl, int pos) {
        double mul = Math.pow(10, pos);
        dbl = Math.round(dbl * mul) / mul;
        return dbl;
    }

    public static Object nullToEmpty(Object value) {
        Object res;
        if (value == null) {
            res = "";
        } else {
            res = value;
        }
        return res;
    }

    public static String prepareCheckValue(String value) {
        return value.trim().replaceAll(" ", "-");
    }

    public static String dateStringToStringFormat(String value) {
        if (value == null) {
            value = "";
        }
        value = value.trim();
        if (value.length() != 8) {
            return "";
        }
        if (getDateFormat().toLowerCase().equals("yyyy.mm.dd")) {
            return value.substring(0, 4) + "." + value.substring(4, 6) + "." + value.substring(6, 8);
        } else if (getDateFormat().toLowerCase().equals("yyyy-mm-dd")) {
            return value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
        } else if (getDateFormat().toLowerCase().equals("dd-mm-yyyy")) {
            return value.substring(6, 8) + "-" + value.substring(4, 6) + "-" + value.substring(0, 4);
        } else if (getDateFormat().toLowerCase().equals("dd.mm.yyyy")) {
            return value.substring(6, 8) + "." + value.substring(4, 6) + "." + value.substring(0, 4);
        }
        return value;
    }

    public static String dateStringToStringFormatFull(String value) {

        if (value == null) {
            value = "";
        }
        value = value.trim();
        if (value.length() < 8) {
            return "";
        }
        String addData = "";
        if (value.length() == 14) {
            addData = value.substring(8, 14);
            addData = " " + value.substring(8, 10) + ":" + value.substring(10, 12) + ":" + value.substring(12, 14);
        }
        if (getDateFormat().toLowerCase().equals("yyyy.mm.dd")) {
            return value.substring(0, 4) + "." + value.substring(4, 6) + "." + value.substring(6, 8) + addData;
        } else if (getDateFormat().toLowerCase().equals("yyyy-mm-dd")) {
            return value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8) + addData;
        } else if (getDateFormat().toLowerCase().equals("dd-mm-yyyy")) {
            return value.substring(6, 8) + "-" + value.substring(4, 6) + "-" + value.substring(0, 4) + addData;
        } else if (getDateFormat().toLowerCase().equals("dd.mm.yyyy")) {
            return value.substring(6, 8) + "." + value.substring(4, 6) + "." + value.substring(0, 4) + addData;
        }

        return value;
    }

    public static Object substrWithcheck(Object value, int From, int To) {
        String res = (String) value.toString().trim();
        if (value == null) {
            res = "";
        } else {
            try {
                res = res.substring(From, To);
            } catch (Exception e) {

            }
        }

        return res;
    }

    //--------------------------------------------------------------------------
    public static Formatter formatterDo__(String format, Object item) {
        Formatter res = new Formatter();
        res.format(format, item);
        return res;
    }

    public static String prepForBigDecimal(String item) {
        //String res = item.replaceAll(" ", "").replaceAll(",", "");
        String res = item.replaceAll(" ", "");
        res = item.replaceAll(",", ".");
        return res;
    }

    public static BigDecimal toBigDecimal(Object value, int round, BigDecimal def) {
        BigDecimal decValue = BigDecimal.ZERO;
        try {

            if (value != null) {
                if (value instanceof BigDecimal) {
                    decValue = ((BigDecimal) value).setScale(round, RoundingMode.HALF_UP);
                } else if (value instanceof String) {
                    decValue = new BigDecimal((String) value).setScale(round, RoundingMode.HALF_UP);
                } else if (value instanceof BigInteger) {
                    decValue = new BigDecimal((BigInteger) value).setScale(round, RoundingMode.HALF_UP);
                } else if (value instanceof Number) {
                    decValue = new BigDecimal(((Number) value).doubleValue()).setScale(round, RoundingMode.HALF_UP);
                } else {
                    return def;
                }
            } else {
                return def;
            }
        } catch (Exception ex) {
            return null;
        }

        return decValue;

    }

    //--------------------------------------------------------------------------
    public static String vectorToSql(Vector data) {
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            if (i != (data.size() - 1)) {
                res += "'" + data.get(i) + "'" + ", ";
            } else {
                res += "'" + data.get(i) + "'";
            }
        }
        return res;
    }
    //--------------------------------------------------------------------------



    public static String decryptCharDo(String fieldName, String fieldNameTo, String ccSid, Integer lenght) {

        String addAs = "";

        if (lenght == null) {
            lenght = 0;
        }
        if (fieldName == null) {
            fieldName = "";
        }
        if (lenght == 0 || fieldName == "") {
            return "";
        }

        if (fieldNameTo.length() > 0) {
            addAs = " as " + fieldNameTo;
        }

        String res = "(CAST(decrypt_char(" + fieldName + ") as varchar(" + lenght + ") CCSID " + ccSid + " ) ) " + addAs;

        return res;

    }

    //--------------------------------------------------------------------------
    public static String forSpecialSymbol(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        int len = input.length();
        // сделаем небольшой запас, чтобы не выделять память потом
        final StringBuilder result = new StringBuilder(len + len / 4);
        final StringCharacterIterator iterator = new StringCharacterIterator(input);
        char ch = iterator.current();
        while (ch != CharacterIterator.DONE) {
            if (ch == '\n') {
                result.append("\\n");
            } else if (ch == '\r') {
                result.append("\\r");
            } else if (ch == '\'') {
                result.append("\\\'");
            } else if (ch == '"') {
                result.append("\\\"");
            } else {
                result.append(ch);
            }
            ch = iterator.next();
        }
        return result.toString();
    }

    public static String getLocalSurnameAndIni(String name) {

        String res = "";
        if (name != null) {
            if (name.length() > 0) {
                String[] data = name.split(" ");
                res += data[0] + " ";
                for (int i = 1; i < data.length; i++) {
                    String part = data[i].trim();
                    if (part.length() > 0) {
                        res += part.substring(0, 1) + ".";
                    }
                }
            }
        }

        return res;
    }

    public static String dateStringFromBaseToStringFormat(String value, String format) {
        if (value == null) {
            value = "";
        }
        value = value.trim();
        if (value.length() != 8) {
            return "";
        }
        if (format.toLowerCase().equals("yyyy.mm.dd")) {
            return value.substring(0, 4) + "." + value.substring(4, 6) + "." + value.substring(6, 8);
        } else if (format.toLowerCase().equals("yyyy-mm-dd")) {
            return value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
        } else if (format.toLowerCase().equals("dd-mm-yyyy")) {
            return value.substring(6, 8) + "-" + value.substring(4, 6) + "-" + value.substring(0, 4);
        } else if (format.toLowerCase().equals("dd.mm.yyyy")) {
            return value.substring(6, 8) + "." + value.substring(4, 6) + "." + value.substring(0, 4);
        }
        return value;
    }

    //создаем метод для удаления дубликатов с помощью хешсет
    public static ArrayList<String> DelDublString(ArrayList<String> array) {
        ArrayList<String> result = new ArrayList<String>(new HashSet<String>(array));
        Collections.sort(result);
        //System.out.println("" + result);
        return result;
    }

    public static ArrayList<Integer> DelDublInt(ArrayList<Integer> array) {
        ArrayList<Integer> result = new ArrayList<Integer>(new HashSet<Integer>(array));
        Collections.sort(result);
        //System.out.println("no dubd result = " + result);
        return result;
    }

    public static String getDateFormat() {
        return "dd.MM.yyyy";
    }


    public static String dateToStringWithFormat(Date date, String format) {
        if (date == null) {
            return "";
        }

        return (new SimpleDateFormat(format)).format(date);
    }
}
