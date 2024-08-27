package com.github.tomatosearch.template.expression.arithmetic.operator;

import com.github.tomatosearch.template.expression.arithmetic.operator.type.TypeCaliculator;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

public class DivideOperator extends AbstractArithmeticOperator {

	@Override
	protected Object doCaliculate(TypeCaliculator<Object> type, Object left, Object right) {
		return type.divide(left, right);
	}

	@Override
	public String toExpressionText() {
		return "/";
	}

	@Override
	public int getOrder() {
		return TokenType.ARITHMETIC_OPERATOR.getOrder();
	}

	@Override
	public TokenType getTokenType() {
		return TokenType.ARITHMETIC_OPERATOR;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
