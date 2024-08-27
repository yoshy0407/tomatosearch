package com.github.tomatosearch.template.node.value;

import java.util.Objects;

import com.github.tomatosearch.template.node.Node;

public class NullNode implements Node {

	private final String value = "null";
	
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
		NullNode other = (NullNode) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toJson() {
		return value;
	}

	@Override
	public String toJsonPretty(int indentSize) {
		return toJson();
	}

}
