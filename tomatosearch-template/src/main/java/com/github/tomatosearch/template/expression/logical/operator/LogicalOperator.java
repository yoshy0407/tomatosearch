package com.github.tomatosearch.template.expression.logical.operator;

import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

public abstract class LogicalOperator implements Operator<Boolean> {

	@Override
	public int getOrder() {
		return TokenType.LOGICAL_OPERATOR.getOrder();
	}

	@Override
	public TokenType getTokenType() {
		return TokenType.LOGICAL_OPERATOR;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}
}
