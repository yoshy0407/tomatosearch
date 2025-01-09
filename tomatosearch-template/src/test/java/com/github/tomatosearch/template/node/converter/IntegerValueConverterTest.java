package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class IntegerValueConverterTest {

	IntegerValueConverter sut = new IntegerValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget(100)).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(100, Map.of())).isEqualTo("100");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("100", Map.of())).isEqualTo(100);
	}

}
