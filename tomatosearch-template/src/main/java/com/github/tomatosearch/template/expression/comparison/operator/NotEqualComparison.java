package com.github.tomatosearch.template.expression.comparison.operator;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

/**
 * "!="を表す比較演算子です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class NotEqualComparison implements Operator<Boolean> {

	@Override
	public Boolean evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		return !left.getValue().equals(right.getValue());
	}

	@Override
	public String toExpressionText() {
		return "!=";
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
