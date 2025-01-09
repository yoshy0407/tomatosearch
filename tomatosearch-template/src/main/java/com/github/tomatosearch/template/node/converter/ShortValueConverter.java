package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class ShortValueConverter extends AbstractValueConverter<Short> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Short;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Short deserialize(String value, Map<String, Object> option) {
		return Short.parseShort(value);
	}

}
