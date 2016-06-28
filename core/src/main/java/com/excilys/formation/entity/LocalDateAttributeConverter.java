package com.excilys.formation.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.excilys.formation.util.DateUtils;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDate localDate) {
		return DateUtils.localDateToTimestamp(localDate);
	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp timestamp) {
		return DateUtils.timestampToLocalDate(timestamp);
	}

}
