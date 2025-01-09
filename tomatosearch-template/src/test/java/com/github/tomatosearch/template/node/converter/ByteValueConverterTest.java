package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ByteValueConverterTest {

	ByteValueConverter sut = new ByteValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget((byte) 1)).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize((byte) 1, Map.of())).isEqualTo("1");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("1", Map.of())).isEqualTo((byte) 1);
	}

}
