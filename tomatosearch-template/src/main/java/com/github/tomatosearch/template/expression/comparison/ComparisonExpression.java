package com.github.tomatosearch.template.expression.comparison;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.Operator;

public class ComparisonExpression implements Expression {

	private final Expression left;

	private final Operator<Boolean> comparison;

	private final Expression right;

	public ComparisonExpression(Expression left, Operator<Boolean> comparison, Expression right) {
		this.left = left;
		this.comparison = comparison;
		this.right = right;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		EvaluationResult leftResult = this.left.evaluate(parameter);
		EvaluationResult rightResult = this.right.evaluate(parameter);
		return new EvaluationResult(this.comparison.evaluate(leftResult, rightResult));
	}

	@Override
	public String toExpressionText() {
		return left.toExpressionText() + comparison.toExpressionText() + right.toExpressionText();
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
