package com.github.tomatosearch.template.node.converter;

import java.util.Map;

/**
 * booleanをbooleanにマッピングするクラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class BooleanValueConverter extends AbstractValueConverter<Boolean> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof Boolean;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return quote(value);
	}

	@Override
	public Boolean deserialize(String value, Map<String, Object> option) {
		String actualText = value;
		if (value.matches("^\".*?\"$")) {
			actualText = dequote(value);
		}
		return Boolean.valueOf(actualText);
	}

}
