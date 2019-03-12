package comp3095.amtrix2.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class StringHelper {
	private static final SimpleDateFormat friendlyFormat = new SimpleDateFormat("dd/MM/YYYY");
	private static final SimpleDateFormat dateControlFormat = new SimpleDateFormat("YYYY-MM-dd");
	private static final SimpleDateFormat creditCardControlFormat = new SimpleDateFormat("YYYY-MM");
	private StringHelper() { }
	
	
	public static String sqlDateToString(java.sql.Date date) {
		return friendlyFormat.format(date);
	}
	public static String dateToControlString(java.sql.Date date) {
		return dateControlFormat.format(date);
	}
	public static java.sql.Date parseCreditCardExpiry(String str) {
		try {
			return new java.sql.Date(creditCardControlFormat.parse(str).getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
}
