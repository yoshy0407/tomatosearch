package com.github.tomatosearch.template.expression.parser.token;

import java.util.Objects;

public class Token {

	private final TokenType tokenType;

	private final String token;

	public Token(TokenType tokenType, String token) {
		this.tokenType = tokenType;
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public String getToken() {
		return token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, tokenType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(token, other.token) && tokenType == other.tokenType;
	}

	@Override
	public String toString() {
		return "Token [tokenType=" + tokenType + ", token=" + token + "]";
	}

}
