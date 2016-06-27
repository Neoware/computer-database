package com.excilys.formation.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Helper class to manipulate various date format.
 * 
 * @author neoware
 *
 */
public class DateUtils {

	/**
	 * Convert a Timestamp object to a LocalDate one.
	 * 
	 * @param timestamp
	 *            the timestamp that need to be converted.
	 * @return The generated LocalDate or null if the submitted timestamp wasn't
	 *         correct.
	 */
	public static LocalDate timestampToLocalDate(Timestamp timestamp) {
		LocalDate localDate = null;
		if (timestamp != null) {
			localDate = timestamp.toLocalDateTime().toLocalDate();
		}
		return localDate;
	}

	/**
	 * Convert a LocalDate object to a Timestamp One.
	 * 
	 * @param localDate
	 *            the LocalDate object that need to be converted
	 * @return The generated Timestamp object or null if the submitted LocalDate
	 *         was not correct
	 */
	public static Timestamp localDateToTimestamp(LocalDate localDate) {
		Timestamp timestamp = null;
		if (localDate != null) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		}
		return timestamp;
	}

	/**
	 * Convert a string to a localDate Object
	 * 
	 * @param string
	 *            The string containing the date that need to be converted.
	 * @return The generated localdate object or null if the submitted string
	 *         wasn't correct (don't respect the pattern).
	 */
	public static LocalDate stringToLocalDate(String string) {
		if (string != null && !string.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(string, formatter);
			return date;
		} else {
			return null;
		}
	}

	/**
	 * Converte a localDate object to a string.
	 * 
	 * @param localDate
	 *            The localdate object that need to be converted.
	 * @return The generate dstring or null if the submitted localdate object
	 *         wasn't correct.
	 */
	public static String localDateToString(LocalDate localDate) {
		if (localDate != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String date = localDate.format(formatter);
			return date;
		} else {
			return null;
		}
	}

	/**
	 * Convert a string to a Timestamp object.
	 * 
	 * @param inputString
	 *            The string that need to be converted.
	 * @return The generated timestamp, or null if the submitted string was
	 *         incorrect (doesn't respect the pattern)
	 */
	public static Timestamp getTimestampFromString(String inputString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			java.util.Date date = format.parse(inputString);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Convert a LocalDate object to a Timestamp One.
	 * 
	 * @param localDate
	 *            the LocalDate object that need to be converted
	 * @return The generated Timestamp object or null if the submitted LocalDate
	 *         was not correct
	 */
	public static LocalDate getLocalDateFromTimestamp(Timestamp timestamp) {
		if (timestamp != null) {
			return timestamp.toLocalDateTime().toLocalDate();
		} else {
			return null;
		}
	}
}
