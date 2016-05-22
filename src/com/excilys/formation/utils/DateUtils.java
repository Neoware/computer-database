package com.excilys.formation.utils;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DateUtils {

	public static LocalDate timestampToLocalDate(Timestamp timestamp) {
		LocalDate localDate = null;
		if (timestamp != null) {
			localDate = timestamp.toLocalDateTime().toLocalDate();
		}
		return localDate;
	}
	
	public static Timestamp localDateToTimestamp(LocalDate localDate){
		Timestamp timestamp = null;
		if (localDate != null){
			timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		}
		return timestamp;
	}
}
