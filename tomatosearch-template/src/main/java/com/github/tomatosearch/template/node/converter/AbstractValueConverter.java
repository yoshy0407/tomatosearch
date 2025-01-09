package com.github.tomatosearch.template.node.converter;

public abstract class AbstractValueConverter<T> implements ValueConverter<T> {

	protected String quote(Object value) {
		return "\"%s\"".formatted(value);
	}

	protected String dequote(String value) {
		return value.substring(1, value.length() - 1);
	}
}
