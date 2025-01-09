package com.github.tomatosearch.template.node.converter;

import java.util.Map;

/**
 * keyword, textなどをStringにマッピングするクラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class StringValueConverter extends AbstractValueConverter<String> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof String;
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return quote(value);
	}

	@Override
	public String deserialize(String value, Map<String, Object> option) {
		return dequote(value);
	}

}
