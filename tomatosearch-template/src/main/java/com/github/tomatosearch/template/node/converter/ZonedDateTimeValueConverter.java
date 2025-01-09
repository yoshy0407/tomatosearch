package com.github.tomatosearch.template.node.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeValueConverter extends AbstractDateValueConverter<ZonedDateTime> {

	public ZonedDateTimeValueConverter(String format) {
		super(format);
	}

	@Override
	public boolean isTarget(Object value) {
		return value instanceof ZonedDateTime;
	}

	@Override
	protected String doSerialize(ZonedDateTime value, DateTimeFormatter formatter) {
		return quote(value.format(formatter));
	}

	@Override
	protected ZonedDateTime doDeserialize(String value, DateTimeFormatter formatter) {
		return ZonedDateTime.parse(dequote(value), formatter);
	}

}
