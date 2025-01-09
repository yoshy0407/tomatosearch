package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class LongValueConverter extends AbstractValueConverter<Long> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Long;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Long deserialize(String value, Map<String, Object> option) {
		return Long.parseLong(value);
	}

}
