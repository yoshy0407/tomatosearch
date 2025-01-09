package com.github.tomatosearch.template.node.converter;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class AbstractDateValueConverter<T> extends AbstractValueConverter<T> {

	private final String format;

	protected AbstractDateValueConverter(String format) {
		this.format = format;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String serialize(Object value, Map<String, Object> option) {
		String format = option.getOrDefault(CompositeValueConverter.DATE_FORMAT, this.format).toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return doSerialize((T) value, formatter);
	}

	protected abstract String doSerialize(T value, DateTimeFormatter formatter);

	@Override
	public T deserialize(String value, Map<String, Object> option) {
		String format = option.getOrDefault(CompositeValueConverter.DATE_FORMAT, this.format).toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return doDeserialize(value, formatter);
	}

	protected abstract T doDeserialize(String value, DateTimeFormatter formatter);

}
