package com.github.tomatosearch.template.node.text;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextBuilder {

	public final static String DOUBLE_QUOTE = "\"";

	public final static String COMMA = ",";

	public final static String CORON = ":";

	public final static String NL = System.lineSeparator();

	private final static String WHITESPACE = " ";
	
	private final StringBuilder sb = new StringBuilder();
	
	public TextBuilder append(String text) {
		sb.append(text);
		return this;
	}
	
	public TextBuilder indent(int size) {
		if (size == 0) {
			return this;
		}
		String indent = IntStream.rangeClosed(1, size)
				.mapToObj(i -> WHITESPACE)
				.collect(Collectors.joining());
		sb.append(indent);
		return this;
	}

	public TextBuilder field(String field, String value) {
		sb.append(DOUBLE_QUOTE).append(field).append(DOUBLE_QUOTE);
		sb.append(CORON).append(WHITESPACE).append(value);
		return this;
	}
	
	public TextBuilder nl() {
		sb.append(NL);
		return this;
	}

	public TextBuilder comma() {
		sb.append(COMMA);
		return this;
	}

	public TextBuilder commaWithWhitespace() {
		sb.append(COMMA);
		sb.append(WHITESPACE);
		return this;
	}
	
	public TextBuilder whitespace() {
		sb.append(WHITESPACE);
		return this;
	}

	
	public TextBuilder removeEnd(int size) {
		sb.setLength(sb.length() - 2);
		return this;
	}
	
	public String build() {
		return sb.toString();
	}

	@Override
	public String toString() {
		return build();
	}
}
