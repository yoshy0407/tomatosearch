package com.github.tomatosearch.template.node.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeValueConverter extends AbstractDateValueConverter<LocalDateTime> {

	public LocalDateTimeValueConverter(String format) {
		super(format);
	}

	@Override
	public boolean isTarget(Object value) {
		return value instanceof LocalDateTime;
	}

	@Override
	protected String doSerialize(LocalDateTime value, DateTimeFormatter formatter) {
		return quote(value.format(formatter));
	}

	@Override
	protected LocalDateTime doDeserialize(String value, DateTimeFormatter formatter) {
		return LocalDateTime.parse(dequote(value), formatter);
	}

}
