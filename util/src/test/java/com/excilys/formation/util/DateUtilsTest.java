package com.excilys.formation.util;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testTimestampToLocalDateWithACorrectTimestamp() {
		Timestamp timestamp = new Timestamp(1482537600000L);
		LocalDate localDate = LocalDate.of(2016, 12, 24);
		LocalDate localDateCreated = DateUtils.getLocalDateFromTimestamp(timestamp);
		localDateCreated.toString();
		assertEquals(localDate, localDateCreated);
	}
}
