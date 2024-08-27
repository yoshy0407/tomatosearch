package com.github.tomatosearch.template.expression.comparison;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.comparison.operator.EqualComparison;
import com.github.tomatosearch.template.expression.comparison.operator.GreaterThanComparison;
import com.github.tomatosearch.template.expression.comparison.operator.GreaterThanEqualComparison;
import com.github.tomatosearch.template.expression.comparison.operator.LessThanComparison;
import com.github.tomatosearch.template.expression.comparison.operator.LessThanEqualComparison;
import com.github.tomatosearch.template.expression.comparison.operator.NotEqualComparison;

/**
 * 比較演算子の集合体です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class Comparisons {

	private static final EqualComparison EQUAL = new EqualComparison();

	private static final NotEqualComparison NOT_EQUAL = new NotEqualComparison();

	private Comparisons() {
	}

	/**
	 * 比較演算子を解決します
	 * 
	 * @param text 比較演算子のテキスト
	 * @return 比較演算子
	 * @throws ComparisonNotFoundException 存在しない比較演算子が投げられた場合
	 */
	public static Operator<Boolean> resolve(String text) throws ExpressionParseException {
		if (text.equals("==")) {
			return EQUAL;

		} else if (text.equals("!=")) {
			return NOT_EQUAL;

		} else if (text.equals("<")) {
			return new LessThanComparison(text);

		} else if (text.equals("<=") || text.equals("=<")) {
			return new LessThanEqualComparison(text);

		} else if (text.equals(">")) {
			return new GreaterThanComparison(text);

		} else if (text.equals(">=") || text.equals("=>")) {
			return new GreaterThanEqualComparison(text);

		} else {
			throw new ExpressionParseException(ErrorCode.COMPARISON_NOT_FOUND.message(text));
		}
	}
}
