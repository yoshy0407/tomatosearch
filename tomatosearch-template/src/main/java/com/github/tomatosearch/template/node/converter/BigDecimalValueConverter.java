package com.github.tomatosearch.template.node.converter;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalValueConverter extends AbstractValueConverter<BigDecimal> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof BigDecimal;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return value.toString();
	}

	@Override
	public BigDecimal deserialize(String value, Map<String, Object> option) {
		return new BigDecimal(value);
	}

}
