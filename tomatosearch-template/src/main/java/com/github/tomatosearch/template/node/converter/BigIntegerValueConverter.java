package com.github.tomatosearch.template.node.converter;

import java.math.BigInteger;
import java.util.Map;

public class BigIntegerValueConverter extends AbstractValueConverter<BigInteger> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof BigInteger;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return value.toString();
	}

	@Override
	public BigInteger deserialize(String value, Map<String, Object> option) {
		return new BigInteger(value);
	}

}
