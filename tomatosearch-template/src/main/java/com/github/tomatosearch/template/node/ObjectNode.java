package com.github.tomatosearch.template.node;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.node.text.TextBuilder;

/**
 * JSONオブジェクトを表すクラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class ObjectNode implements JsonNode, Iterable<Entry<String, JsonNode>> {

	private int nodeLevel = 0;

	private final LinkedHashMap<String, JsonNode> values;

	public ObjectNode() {
		this(new LinkedHashMap<>());
	}

	public ObjectNode(LinkedHashMap<String, JsonNode> values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(values);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectNode other = (ObjectNode) obj;
		return Objects.equals(values, other.values);
	}

	public void put(String field, JsonNode node) {
		if (node instanceof ArrayNode arrayNode) {
			arrayNode.setNodeLevel(nodeLevel + 1);
			this.values.put(field, arrayNode);
			return;
		}
		if (node instanceof ObjectNode objectNode) {
			objectNode.setNodeLevel(nodeLevel + 1);
			this.values.put(field, objectNode);
			return;
		}

		this.values.put(field, node);
	}

	public JsonNode get(String field) {
		return this.values.get(field);
	}

	public JsonNode remove(String field) {
		return this.values.remove(field);
	}

	void setNodeLevel(int nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	@Override
	public String evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return toJson();
	}

	@Override
	public String toJson() {
		TextBuilder builder = new TextBuilder();
		builder.append("{").whitespace();
		values.entrySet().forEach(e -> {
			builder.field(e.getKey(), e.getValue().toJson()).commaWithWhitespace();
		});
		builder.removeEnd(2).append("}");
		return builder.toString();
	}

	@Override
	public String toJsonPretty(int indentSize) {
		TextBuilder builder = new TextBuilder();
		builder.append("{").nl();
		values.entrySet().forEach(e -> {
			builder.indent(indentSize * nodeLevel).indent(indentSize)
					.field(e.getKey(), e.getValue().toJsonPretty(indentSize + indentSize)).comma().nl();
		});
		builder.removeEnd(2).nl().indent(indentSize * nodeLevel).append("}");
		return builder.toString();
	}

	@Override
	public Iterator<Entry<String, JsonNode>> iterator() {
		return this.values.entrySet().iterator();
	}

}
