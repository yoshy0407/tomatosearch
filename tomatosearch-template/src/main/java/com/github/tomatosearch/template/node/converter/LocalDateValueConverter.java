package com.github.tomatosearch.template.node.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateValueConverter extends AbstractDateValueConverter<LocalDate> {

	public LocalDateValueConverter(String format) {
		super(format);
	}

	@Override
	public boolean isTarget(Object value) {
		return value instanceof LocalDate;
	}

	@Override
	protected String doSerialize(LocalDate value, DateTimeFormatter formatter) {
		return quote(value.format(formatter));
	}

	@Override
	protected LocalDate doDeserialize(String value, DateTimeFormatter formatter) {
		return LocalDate.parse(dequote(value), formatter);
	}

}
