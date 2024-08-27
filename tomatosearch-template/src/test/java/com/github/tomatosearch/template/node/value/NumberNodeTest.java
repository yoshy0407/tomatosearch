package com.github.tomatosearch.template.node.value;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class NumberNodeTest {

	@Test
	void testHashCode() {
		var sut1 = new NumberNode(Integer.valueOf(1));
		var sut2 = new NumberNode(Integer.valueOf(1));
		assertThat(sut1.hashCode()).isEqualTo(sut2.hashCode());
	}

	@Test
	void testEqualsObject() {
		var sut1 = new NumberNode(Long.valueOf(1));
		var sut2 = new NumberNode(Long.valueOf(1));
		assertThat(sut1.equals(sut2)).isTrue();
	}

	@Test
	void testToJsonFromInteger() {
		var sut1 = new NumberNode(Integer.valueOf(100));
		assertThat(sut1.toJson()).isEqualTo("100");
	}

	@Test
	void testToJsonFromFloat() {
		var sut1 = new NumberNode(Float.valueOf(100.34f));
		assertThat(sut1.toJson()).isEqualTo("100.34");
	}

	@Test
	void testToJsonFromBigDecimal() {
		var sut1 = new NumberNode(new BigDecimal("1000.324"));
		assertThat(sut1.toJson()).isEqualTo("1000.324");
	}

	
	@Test
	void testToJsonPretty() {
		var sut1 = new NumberNode(Long.valueOf(100));
		assertThat(sut1.toJsonPretty(4)).isEqualTo("100");
	}

}
