package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class StringValueConverterTest {

	StringValueConverter sut = new StringValueConverter();

	@Test
	void testIsTargetStringIsTrue() {
		assertThat(sut.isTarget("test")).isTrue();
	}

	@Test
	void testIsTargetIntIsFalse() {
		assertThat(sut.isTarget(111)).isFalse();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize("test", Map.of())).isEqualTo("\"test\"");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("\"test\"", Map.of())).isEqualTo("test");
	}

}
