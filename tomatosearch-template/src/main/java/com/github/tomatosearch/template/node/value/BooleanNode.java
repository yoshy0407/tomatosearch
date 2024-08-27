package com.github.tomatosearch.template.node.value;

import java.util.Objects;

import com.github.tomatosearch.template.node.Node;

public class BooleanNode implements Node {

	private final Boolean value;
	
	public BooleanNode(Boolean value) {
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
		BooleanNode other = (BooleanNode) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toJson() {
		return this.value ? "true" : "false";
	}

	@Override
	public String toJsonPretty(int indentSize) {
		return toJson();
	}

}
