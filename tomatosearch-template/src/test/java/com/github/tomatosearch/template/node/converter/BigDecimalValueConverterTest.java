package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;

class BigDecimalValueConverterTest {

	BigDecimalValueConverter sut = new BigDecimalValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget(new BigDecimal("1000.01"))).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(new BigDecimal("1000.01"), Map.of())).isEqualTo("1000.01");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("1000.01", Map.of())).isEqualTo(new BigDecimal("1000.01"));
	}

}
