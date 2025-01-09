package com.github.tomatosearch.template.node.value;

import java.util.Map;
import java.util.Objects;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.node.JsonNode;

public class StringNode implements JsonNode {

	private static final String FORMAT = "\"%s\"";

	private final String value;

	public StringNode(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringNode other = (StringNode) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return toJson();
	}

	@Override
	public String toJson() {
		return FORMAT.formatted(this.value);
	}

	@Override
	public String toJsonPretty(int indentSize) {
		return toJson();
	}

}
