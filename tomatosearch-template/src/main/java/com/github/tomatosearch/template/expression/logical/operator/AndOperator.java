package com.github.tomatosearch.template.expression.logical.operator;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

public class AndOperator extends LogicalOperator {

	@Override
	public Boolean evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		return left.getBooleanValue() && right.getBooleanValue();
	}

	@Override
	public String toExpressionText() {
		return "&&";
	}

	@Override
	public int getOrder() {
		return TokenType.LOGICAL_OPERATOR.getOrder();
	}

}
