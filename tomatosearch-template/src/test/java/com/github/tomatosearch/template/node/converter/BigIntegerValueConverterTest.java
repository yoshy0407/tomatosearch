package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.Map;

import org.junit.jupiter.api.Test;

class BigIntegerValueConverterTest {

	BigIntegerValueConverter sut = new BigIntegerValueConverter();

	@Test
	void testIsTarget() {
		assertThat(sut.isTarget(new BigInteger("1000"))).isTrue();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(new BigInteger("1000"), Map.of())).isEqualTo("1000");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("1000", Map.of())).isEqualTo(new BigInteger("1000"));
	}

}
