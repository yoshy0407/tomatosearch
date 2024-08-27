package com.github.tomatosearch.template.node.value;

import java.util.Objects;

import com.github.tomatosearch.template.node.Node;

public class StringNode implements Node {

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
	public String toJson() {
		return FORMAT.formatted(this.value);
	}

	@Override
	public String toJsonPretty(int indentSize) {
		return toJson();
	}

}
