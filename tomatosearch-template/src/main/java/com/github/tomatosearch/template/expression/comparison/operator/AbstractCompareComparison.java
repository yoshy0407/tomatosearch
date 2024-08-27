package com.github.tomatosearch.template.expression.comparison.operator;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;

public abstract class AbstractCompareComparison implements Operator<Boolean> {

	protected final String comparisonText;

	protected AbstractCompareComparison(String comparisonText) {
		this.comparisonText = comparisonText;
	}

	@Override
	public Boolean evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		Object leftValue = left.getValue();
		Object rightValue = right.getValue();
		if (leftValue instanceof Comparable<?> && rightValue instanceof Comparable<?>) {
			@SuppressWarnings("unchecked")
			Comparable<Object> comparableLeft = (Comparable<Object>) leftValue;
			@SuppressWarnings("unchecked")
			Comparable<Object> comparableRight = (Comparable<Object>) rightValue;
			try {
				return doEvaluate(comparableLeft, comparableRight);
			} catch (Exception ex) {
				throw new EvaluationException(ErrorCode.NOT_COMPABLE.message(left, comparisonText, right));
			}
		}
		throw new EvaluationException(ErrorCode.NOT_COMPABLE.message(leftValue, comparisonText, rightValue));
	}

	protected abstract Boolean doEvaluate(Comparable<Object> left, Comparable<Object> right);

	public String toExpressionText() {
		return this.comparisonText;
	}
}
