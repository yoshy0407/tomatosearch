package com.github.tomatosearch.template.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.github.tomatosearch.template.node.text.TextBuilder;

public class ArrayNode implements Node, Iterable<Node> {
	
	private int nodeLevel = 0;
	
	private final List<Node> nodes;
	
	public ArrayNode() {
		this(new ArrayList<>());
	}
	
	public ArrayNode(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void add(Node node) {
		if (node instanceof ArrayNode arrayNode) {
			arrayNode.setNodeLevel(nodeLevel + 1);
			this.nodes.add(arrayNode);
			return;
		}
		if (node instanceof ObjectNode objectNode) {
			objectNode.setNodeLevel(nodeLevel + 1);
			this.nodes.add(objectNode);
			return;
		}
		this.nodes.add(node);
	}

	public void remove(Node node) {
		this.nodes.remove(node);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodes);
	}

	void setNodeLevel(int nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayNode other = (ArrayNode) obj;
		return Objects.equals(nodes, other.nodes);
	}
	
	@Override
	public String toJson() {
		TextBuilder builder = new TextBuilder();
		builder.append("[");
		nodes.forEach(n -> {
			builder
				.append(n.toJson())
				.commaWithWhitespace();
		});
		builder.removeEnd(1).append("]");
		return builder.toString();
	}

	@Override
	public String toJsonPretty(int indentSize) {
		TextBuilder builder = new TextBuilder();
		builder.append("[").nl();
		nodes.forEach(n -> {
			builder
				.indent(indentSize * nodeLevel)
				.indent(indentSize)
				.append(n.toJsonPretty(indentSize))
				.comma()
				.nl();
		});
		//remove comma, new line
		builder.removeEnd(2).nl().indent(indentSize * nodeLevel).append("]");
		return builder.toString();
	}

	@Override
	public Iterator<Node> iterator() {
		return this.nodes.iterator();
	}

}
