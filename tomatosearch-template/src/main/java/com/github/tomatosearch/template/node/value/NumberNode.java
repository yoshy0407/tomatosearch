package com.github.tomatosearch.template.node.value;

import java.util.Map;
import java.util.Objects;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.node.JsonNode;

public class NumberNode implements JsonNode {

	private final Number value;

	public NumberNode(Number value) {
		this.value = value;
	}

	@Override
	public String evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return toJson();
	}

	@Override
	public String toJson() {
		return this.value.toString();
	}

	@Override
	public String toJsonPretty(int indentSize) {
		return toJson();
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
		NumberNode other = (NumberNode) obj;
		return Objects.equals(value, other.value);
	}

}
