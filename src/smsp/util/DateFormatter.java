package smsp.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
    private static SimpleDateFormat sf;
    
    public static String dateToString(Date date, String format) {
	sf = new SimpleDateFormat(format);
	return sf.format(date);
    }
    
    public static Date stringToDate(String str, String format) throws ParseException {
	sf = new SimpleDateFormat(format);
	return (Date) sf.parse(str);
    }
    
    public static java.sql.Date utilDateToSqlDate(Date date) {
	sf = new SimpleDateFormat("yyyy-MM-dd");
	return java.sql.Date.valueOf(sf.format(date));
    }
    
    public static Date sqlDateToUtilDate(java.sql.Date date) throws ParseException {
	sf = new SimpleDateFormat("dd-MM-yyyy");
	return sf.parse(sf.format(date));
    }
}
