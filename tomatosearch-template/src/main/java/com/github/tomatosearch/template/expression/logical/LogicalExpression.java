package com.github.tomatosearch.template.expression.logical;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.Operator;

public class LogicalExpression implements Expression {

	private final Expression left;

	private Operator<Boolean> logical;

	private final Expression right;

	public LogicalExpression(Expression left, Operator<Boolean> logical, Expression right) {
		this.left = left;
		this.logical = logical;
		this.right = right;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		EvaluationResult leftResult = this.left.evaluate(parameter);
		EvaluationResult rightResult = this.right.evaluate(parameter);
		return new EvaluationResult(logical.evaluate(leftResult, rightResult));
	}

	@Override
	public String toExpressionText() {
		return left.toExpressionText() + " " + logical.toExpressionText() + " " + left.toExpressionText();
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
