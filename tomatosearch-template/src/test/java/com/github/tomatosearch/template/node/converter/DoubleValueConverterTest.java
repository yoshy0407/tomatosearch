package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class DoubleValueConverterTest {

	DoubleValueConverter sut = new DoubleValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget(100.1d)).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(100.1d, Map.of())).isEqualTo("100.1");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("100.11", Map.of())).isEqualTo(100.11d);
	}

}