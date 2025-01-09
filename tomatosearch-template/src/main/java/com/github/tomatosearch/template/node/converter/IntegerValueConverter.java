package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class IntegerValueConverter extends AbstractValueConverter<Integer> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Integer;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Integer deserialize(String value, Map<String, Object> option) {
		return Integer.parseInt(value);
	}

}
