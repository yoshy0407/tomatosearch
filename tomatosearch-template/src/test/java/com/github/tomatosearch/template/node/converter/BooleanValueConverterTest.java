package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class BooleanValueConverterTest {

	BooleanValueConverter sut = new BooleanValueConverter();

	@Test
	void testIsTargetBooleanIsTrue() {
		assertThat(sut.isTarget(true)).isTrue();
	}

	@Test
	void testIsTargetStringIsFalse() {
		assertThat(sut.isTarget("Test")).isFalse();
	}

	@Test
	void testSerialize() {
		assertThat(sut.serialize(true, Map.of())).isEqualTo("\"true\"");
	}

	@Test
	void testDeserialize() {
		assertThat(sut.deserialize("true", Map.of())).isTrue();
		assertThat(sut.deserialize("false", Map.of())).isFalse();
	}

	@Test
	void testDeserializeWithComma() {
		assertThat(sut.deserialize("\"true\"", Map.of())).isTrue();
		assertThat(sut.deserialize("\"false\"", Map.of())).isFalse();
	}

	@Test
	void testDeserializeEmptyIsFalse() {
		assertThat(sut.deserialize("", Map.of())).isFalse();
	}

}
