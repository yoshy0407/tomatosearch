package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class ShortValueConverterTest {

	ShortValueConverter sut = new ShortValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget((short) 10)).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize((short) 10, Map.of())).isEqualTo("10");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("10", Map.of())).isEqualTo((short) 10);
	}

}
