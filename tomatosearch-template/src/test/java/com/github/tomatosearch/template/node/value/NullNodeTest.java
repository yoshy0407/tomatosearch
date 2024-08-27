package com.github.tomatosearch.template.node.value;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NullNodeTest {

	@Test
	void testHashCode() {
		var sut1 = new NullNode();
		var sut2 = new NullNode();
		
		assertThat(sut1.hashCode()).isEqualTo(sut2.hashCode());
	}

	@Test
	void testEqualsObject() {
		var sut1 = new NullNode();
		var sut2 = new NullNode();
		
		assertThat(sut1.equals(sut2)).isTrue();
	}

	@Test
	void testToJson() {
		var sut1 = new NullNode();
		assertThat(sut1.toJson()).isEqualTo("null");
	}

	@Test
	void testToJsonPretty() {
		var sut1 = new NullNode();
		assertThat(sut1.toJsonPretty(4)).isEqualTo("null");
	}

}
