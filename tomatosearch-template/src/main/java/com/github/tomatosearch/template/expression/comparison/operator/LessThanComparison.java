package com.github.tomatosearch.template.expression.comparison.operator;

import com.github.tomatosearch.template.expression.parser.token.TokenType;

/**
 * "<"を表す比較演算子です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class LessThanComparison extends AbstractCompareComparison {

	public LessThanComparison(String text) {
		super(text);
	}

	@Override
	protected Boolean doEvaluate(Comparable<Object> left, Comparable<Object> right) {
		// left < right
		if (this.comparisonText.equals("<")) {
			return left.compareTo(right) < 0;
			// left < right
		} else {
			return right.compareTo(left) < 0;
		}
	}

	@Override
	public int getOrder() {
		return TokenType.COMPARISON_OPERATOR.getOrder();
	}

	@Override
	public TokenType getTokenType() {
		return TokenType.COMPARISON_OPERATOR;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
