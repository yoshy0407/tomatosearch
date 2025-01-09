package com.github.tomatosearch.template.node.converter;

import java.util.Base64;
import java.util.Map;

/**
 * binaryにマッピングするためにbyteArrayを変換するクラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class ByteArrayValueConverter extends AbstractValueConverter<byte[]> {

	@Override
	public boolean isTarget(Object value) {
		return value instanceof byte[] || value instanceof Byte[];
	}

	@Override
	public String serialize(Object value, Map<String, Object> option) {
		return quote(Base64.getEncoder().encodeToString((byte[]) value));
	}

	@Override
	public byte[] deserialize(String value, Map<String, Object> option) {
		return Base64.getDecoder().decode(dequote(value));
	}

}
