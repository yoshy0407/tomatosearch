package com.github.tomatosearch.template.expression.identifiers.parameter;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;

public class ParameterExpression implements Expression {

	private final String parameterName;

	public ParameterExpression(String parameterName) {
		this.parameterName = parameterName;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		return new EvaluationResult(parameter.get(parameterName));
	}

	@Override
	public String toExpressionText() {
		return parameterName;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
