package com.github.tomatosearch.template.node.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeValueConverter extends AbstractDateValueConverter<LocalTime> {

	public LocalTimeValueConverter(String format) {
		super(format);
	}

	@Override
	public boolean isTarget(Object value) {
		return value instanceof LocalTime;
	}

	@Override
	protected String doSerialize(LocalTime value, DateTimeFormatter formatter) {
		return quote(value.format(formatter));
	}

	@Override
	protected LocalTime doDeserialize(String value, DateTimeFormatter formatter) {
		return LocalTime.parse(dequote(value), formatter);
	}

}
