package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class LongValueConverterTest {

	LongValueConverter sut = new LongValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget(100L)).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(100L, Map.of())).isEqualTo("100");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("100", Map.of())).isEqualTo(100L);
	}

}
