package com.github.tomatosearch.template.expression.arithmetic;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.arithmetic.operator.DivideOperator;
import com.github.tomatosearch.template.expression.arithmetic.operator.MinusOperator;
import com.github.tomatosearch.template.expression.arithmetic.operator.MultipleOperator;
import com.github.tomatosearch.template.expression.arithmetic.operator.PlusOperator;
import com.github.tomatosearch.template.expression.arithmetic.operator.RemainderOperator;

public class Arithmetics {

	private static final PlusOperator PLUS = new PlusOperator();

	private static final MinusOperator MINUS = new MinusOperator();

	private static final MultipleOperator MULTIPLE = new MultipleOperator();

	private static final DivideOperator DIVIDE = new DivideOperator();

	private static final RemainderOperator REMAINDER = new RemainderOperator();

	public static Operator<Object> resolve(String text) throws ExpressionParseException {
		if (text.equals("+")) {
			return PLUS;
		}
		if (text.equals("-")) {
			return MINUS;
		}
		if (text.equals("*")) {
			return MULTIPLE;
		}
		if (text.equals("/")) {
			return DIVIDE;
		}
		if (text.equals("%")) {
			return REMAINDER;
		}
		throw new ExpressionParseException(ErrorCode.CALICULATE_NOT_FOUND.message(text));
	}
}
