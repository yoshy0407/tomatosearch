package com.github.tomatosearch.template.node;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.node.value.StringNode;

class ObjectNodeTest {

	@Test
	void testToJson() {
		ObjectNode object = new ObjectNode();
		object.put("test1", new ArrayNode(List.of(new StringNode("value1"), new StringNode("value2"))));
		object.put("test2", new StringNode("value2"));
		ObjectNode inner = new ObjectNode();
		inner.put("f1", new StringNode("value1"));
		inner.put("f2", new StringNode("value2"));
		object.put("test3", inner);

		assertThat(object.toJson()).isEqualTo("{ \"test1\": [\"value1\", \"value2\"], \"test2\": \"value2\", \"test3\": { \"f1\": \"value1\", \"f2\": \"value2\"}}");
	}

	@Test
	void testToJsonPretty() {
		String expect = """
				{ 
				    \"test1\": [
				        \"value1\",
				        \"value2\"
				    ],
				    \"test2\": \"value2\",
				    \"test3\": { 
				        \"f1\": \"value1\", 
				        \"f2\": \"value2\"
				    }
				}""";
		
		ObjectNode object = new ObjectNode();
		object.put("test1", new ArrayNode(List.of(new StringNode("value1"), new StringNode("value2"))));
		object.put("test2", new StringNode("value2"));
		ObjectNode inner = new ObjectNode();
		inner.put("f1", new StringNode("value1"));
		inner.put("f2", new StringNode("value2"));
		object.put("test3", inner);

		assertThat(object.toJsonPretty(4)).isEqualTo(expect);
	}

	@Test
	void testIterator() {
		fail("まだ実装されていません");
	}

}
