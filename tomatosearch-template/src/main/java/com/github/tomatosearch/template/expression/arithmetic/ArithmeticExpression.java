package com.github.tomatosearch.template.expression.arithmetic;

import java.util.Map;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.Operator;

public class ArithmeticExpression implements Expression {

	private final Expression left;

	private final Operator<Number> caliculateOperator;

	private final Expression right;

	public ArithmeticExpression(Expression left, Operator<Number> caliculateOperator, Expression right) {
		this.left = left;
		this.caliculateOperator = caliculateOperator;
		this.right = right;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		EvaluationResult leftResult = left.evaluate(parameter);
		EvaluationResult rightResult = right.evaluate(parameter);

		try {
			return new EvaluationResult(this.caliculateOperator.evaluate(leftResult, rightResult));
		} catch (Exception ex) {
			throw new EvaluationException(
					ErrorCode.CONCATE_FAILURE.message(leftResult.getValue(), rightResult.getValue()), ex);
		}
	}

	@Override
	public String toExpressionText() {
		return this.left.toExpressionText() + this.caliculateOperator.toExpressionText()
				+ this.right.toExpressionText();
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
