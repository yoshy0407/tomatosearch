package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ByteArrayValueConverterTest {

	ByteArrayValueConverter sut = new ByteArrayValueConverter();

	@Test
	void testIsTargetByteArrayIsTrue() {
		assertThat(sut.isTarget("テスト".getBytes())).isTrue();
	}

	@Test
	void testIsTargetOtherIsFalse() {
		assertThat(sut.isTarget("Test")).isFalse();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize("テスト".getBytes(), Map.of())).isEqualTo("\"44OG44K544OI\"");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("\"44OG44K544OI\"", Map.of())).isEqualTo("テスト".getBytes());
	}

}
