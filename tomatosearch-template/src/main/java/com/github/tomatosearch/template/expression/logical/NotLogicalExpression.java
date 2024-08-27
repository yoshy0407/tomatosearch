package com.github.tomatosearch.template.expression.logical;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;

public class NotLogicalExpression implements Expression {

	private final Expression expression;

	public NotLogicalExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		EvaluationResult result = this.expression.evaluate(parameter);
		return new EvaluationResult(!result.getBooleanValue());
	}

	@Override
	public String toExpressionText() {
		return "!" + expression.toExpressionText();
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
