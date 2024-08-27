package com.github.tomatosearch.template.expression.literal;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;

public class LiteralExpression implements Expression {

	private final Object value;

	private final String expressionText;

	public LiteralExpression(Object value, String expressionText) {
		this.value = value;
		this.expressionText = expressionText;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		return new EvaluationResult(this.value);
	}

	@Override
	public String toExpressionText() {
		return expressionText;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
