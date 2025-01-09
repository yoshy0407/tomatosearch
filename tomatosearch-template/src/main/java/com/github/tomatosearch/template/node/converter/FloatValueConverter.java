package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class FloatValueConverter extends AbstractValueConverter<Float> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Float;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Float deserialize(String value, Map<String, Object> option) {
		return Float.parseFloat(value);
	}

}
