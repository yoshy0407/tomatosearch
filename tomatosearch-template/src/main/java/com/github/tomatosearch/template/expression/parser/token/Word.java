package com.github.tomatosearch.template.expression.parser.token;

import java.util.Objects;

public class Word {

	private final char word;

	public Word(char word) {
		this.word = word;
	}

	public char toChar() {
		return this.word;
	}

	public boolean isWhitespace() {
		return Character.isWhitespace(word);
	}

	public boolean isDigitLiteal() {
		return Character.isDigit(word);
	}

	public boolean isStringLiteal() {
		return word == '"' || word == '\'';
	}

	public boolean isComparisonOperator() {
		return "=<>!".indexOf(word) != -1;
	}

	public boolean isLogicalOperator() {
		return "&|".indexOf(word) != -1;
	}

	public boolean isArithmeticOperator() {
		return "+-*/%".indexOf(word) != -1;
	}

	public boolean isDot() {
		return '.' == word;
	}

	public boolean isDelimiter() {
		return "()".indexOf(word) != -1;
	}

	public boolean isEmbeddedFunctionOrStatic() {
		return '@' == word;
	}

	public boolean isStartParentheses() {
		return '(' == word;
	}

	public boolean isEndParentheses() {
		return ')' == word;
	}

	@Override
	public int hashCode() {
		return Objects.hash(word);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return word == other.word;
	}

	public boolean notEquals(Object obj) {
		return !equals(obj);
	}

	@Override
	public String toString() {
		return "" + word;
	}
}
