package com.github.tomatosearch.template.node;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.node.value.StringNode;

class ArrayNodeTest {

	@Test
	void testHashCode() {
		var sut1 = new ArrayNode(List.of(new StringNode("tes1"), new StringNode("test2")));
		var sut2 = new ArrayNode(List.of(new StringNode("tes1"), new StringNode("test2")));
		assertThat(sut1.hashCode()).isEqualTo(sut2.hashCode());
	}

	@Test
	void testEqualsObject() {
		var sut1 = new ArrayNode(List.of(new StringNode("tes1"), new StringNode("test2")));
		var sut2 = new ArrayNode(List.of(new StringNode("tes1"), new StringNode("test2")));
		assertThat(sut1.equals(sut2)).isTrue();
	}

	@Test
	void testToJson() {
		var sut = new ArrayNode(List.of(new StringNode("tes1"), new StringNode("test2")));
		assertThat(sut.toJson()).isEqualTo("[\"tes1\", \"test2\"]");
	}

	@Test
	void testToJsonPretty() {
		var sut = new ArrayNode(List.of(new StringNode("test1"), new StringNode("test2")));
		assertThat(sut.toJsonPretty(4)).isEqualTo("[\n    \"test1\",\n    \"test2\"\n]");
	}

	@Test
	void testIterator() {
		var sut = new ArrayNode(List.of(new StringNode("test1"), new StringNode("test2")));
		for (Node node : sut) {
			assertThat(node).isNotNull();
		}
	}

}
