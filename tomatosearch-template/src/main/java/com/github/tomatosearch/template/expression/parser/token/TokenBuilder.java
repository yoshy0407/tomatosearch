package com.github.tomatosearch.template.expression.parser.token;

public class TokenBuilder {

	private final StringBuilder builder = new StringBuilder();

	public TokenBuilder append(char word) {
		builder.append(word);
		return this;
	}

	public TokenBuilder append(String word) {
		builder.append(word);
		return this;
	}

	public TokenBuilder append(Word word) {
		builder.append(word.toChar());
		return this;
	}

	public boolean hasWord() {
		return builder.length() > 0;
	}

	public boolean hasWordOf(String word) {
		return builder.indexOf(word) != -1;
	}

	public boolean isMethod() {
		return builder.indexOf("(") < builder.indexOf(")");
	}

	public boolean isMethodWithLastWord(Word last) {
		return builder.indexOf("(") != -1 && last.isEndParentheses();
	}

	public boolean isStatic() {
		int first = builder.indexOf("@");
		int second = builder.indexOf("@", first + 1);
		return first < second;
	}

	public TokenBuilder resetWords() {
		builder.setLength(0);
		return this;
	}

	public Token build(TokenType tokenType) {
		return new Token(tokenType, builder.toString());
	}

	public String toString() {
		return builder.toString();
	}

}
