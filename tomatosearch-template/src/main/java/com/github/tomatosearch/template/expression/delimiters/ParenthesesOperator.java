package com.github.tomatosearch.template.expression.delimiters;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

public enum ParenthesesOperator implements Operator<String> {
	//@formatter:off
	START("("), 
	END(")");
	//@formatter:on

	private final String parenthesesText;

	private ParenthesesOperator(String parenthesesText) {
		this.parenthesesText = parenthesesText;
	}

	@Override
	public String evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		// DO NOTHING
		return null;
	}

	@Override
	public String toExpressionText() {
		return this.parenthesesText;
	}

	@Override
	public int getOrder() {
		return TokenType.DELIMITER.getOrder();
	}

	@Override
	public TokenType getTokenType() {
		return TokenType.DELIMITER;
	}

	public String toString() {
		return toExpressionText();
	}

}
