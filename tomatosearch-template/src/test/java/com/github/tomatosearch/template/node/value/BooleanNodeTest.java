package com.github.tomatosearch.template.node.value;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BooleanNodeTest {

	@Test
	void testHashCode() {
		var sut1 = new BooleanNode(Boolean.TRUE);
		var sut2 = new BooleanNode(Boolean.TRUE);
		assertThat(sut1.hashCode()).isEqualTo(sut2.hashCode());
	}

	@Test
	void testEqualsObject() {
		var sut1 = new BooleanNode(Boolean.TRUE);
		var sut2 = new BooleanNode(Boolean.TRUE);
		assertThat(sut1.equals(sut2)).isTrue();
	}

	@Test
	void testToJson() {
		var sut = new BooleanNode(Boolean.TRUE);
		assertThat(sut.toJson()).isEqualTo("true");
	}

	@Test
	void testToJsonPretty() {
		var sut = new BooleanNode(Boolean.TRUE);
		assertThat(sut.toJsonPretty(4)).isEqualTo("true");
	}

}
