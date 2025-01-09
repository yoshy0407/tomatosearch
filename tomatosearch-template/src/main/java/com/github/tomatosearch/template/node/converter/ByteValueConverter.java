package com.github.tomatosearch.template.node.converter;

import java.util.Map;

public class ByteValueConverter extends AbstractValueConverter<Byte> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Byte;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return String.valueOf(value);
	}

	@Override
	public Byte deserialize(String value, Map<String, Object> option) {
		return Byte.parseByte(value);
	}

}
