package com.github.tomatosearch.template.node.value;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringNodeTest {

	@Test
	void testHashCode() {
		var sut1 = new StringNode("test");
		var sut2 = new StringNode("test");
		
		assertThat(sut1.hashCode()).isEqualTo(sut2.hashCode());
	}

	@Test
	void testEqualsObject() {
		var sut1 = new StringNode("test");
		var sut2 = new StringNode("test");
		
		assertThat(sut1.equals(sut2)).isTrue();
	}

	@Test
	void testToJson() {
		var sut = new StringNode("test");
		assertThat(sut.toJson()).isEqualTo("\"test\"");
	}

	@Test
	void testToJsonPretty() {
		var sut = new StringNode("test");
		assertThat(sut.toJsonPretty(4)).isEqualTo("\"test\"");
	}

}
