package com.github.tomatosearch.template.expression.logical.operator;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

public class NotOperator implements Operator<Boolean> {

	@Override
	public Boolean evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		// Do Nothing
		return null;
	}

	@Override
	public String toExpressionText() {
		return "!";
	}

	@Override
	public int getOrder() {
		return getTokenType().getOrder();
	}

	@Override
	public TokenType getTokenType() {
		return TokenType.LOGICAL_OPERATOR;
	}

}
