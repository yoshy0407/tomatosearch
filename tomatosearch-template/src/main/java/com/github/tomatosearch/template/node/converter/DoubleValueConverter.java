package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class DoubleValueConverter extends AbstractValueConverter<Double> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Double;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Double deserialize(String value, Map<String, Object> option) {
		return Double.parseDouble(value);
	}

}
