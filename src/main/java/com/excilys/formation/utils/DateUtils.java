package com.excilys.formation.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDate timestampToLocalDate(Timestamp timestamp) {
		LocalDate localDate = null;
		if (timestamp != null) {
			localDate = timestamp.toLocalDateTime().toLocalDate();
		}
		return localDate;
	}

	public static Timestamp localDateToTimestamp(LocalDate localDate) {
		Timestamp timestamp = null;
		if (localDate != null) {
			timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		}
		return timestamp;
	}

	public static LocalDate stringToLocalDate(String string) {
		if (string != null && !string.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(string, formatter);
			return date;
		} else
			return null;
	}

	public static String localDateToString(LocalDate localDate) {
		if (localDate != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String date = localDate.format(formatter);
			return date;
		} else
			return null;
	}
	
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
	
	public static LocalDate getLocalDateFromTimestamp(Timestamp timestamp){
		if (timestamp != null)
			return timestamp.toLocalDateTime().toLocalDate();
		else
			return null;
	}
}
