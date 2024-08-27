package com.github.tomatosearch.template.expression.literal;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class LiteralsTest {

	@Test
	void testResolveNull() {
		Object value = Literals.resolve("null");
		assertThat(value).isNull();
	}

	@Test
	void testResolveTrue() {
		Object value = Literals.resolve("true");
		assertThat(value).isEqualTo(true);
	}

	@Test
	void testResolveFalse() {
		Object value = Literals.resolve("false");
		assertThat(value).isEqualTo(false);
	}

	@Test
	void testResolveInt() {
		Object value = Literals.resolve("10");
		assertThat(value).isEqualTo(10);
	}

	@Test
	void testResolveLong() {
		Object value = Literals.resolve("100L");
		assertThat(value).isEqualTo(100L);
	}

	@Test
	void testResolveFloat() {
		Object value = Literals.resolve("0.1F");
		assertThat(value).isEqualTo(0.1F);
	}

	@Test
	void testResolveDouble() {
		Object value = Literals.resolve("0.00004D");
		assertThat(value).isEqualTo(0.00004D);
	}

	@Test
	void testResolveBigDecimal() {
		Object value = Literals.resolve("0.123B");
		assertThat(value).isEqualTo(new BigDecimal("0.123"));
	}

	@Test
	void testResolveChar() {
		Object value = Literals.resolve("'a'");
		assertThat(value).isEqualTo('a');
	}

	@Test
	void testResolveString() {
		Object value = Literals.resolve("\"test\"");
		assertThat(value).isEqualTo("test");
	}

}
