package com.github.tomatosearch.template.expression.logical;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.logical.operator.AndOperator;
import com.github.tomatosearch.template.expression.logical.operator.OrOperator;

public class Logicals {

	public static Operator<Boolean> resolve(String text) throws ExpressionParseException {
		if (text.equals("&&")) {
			return new AndOperator();
		} else if (text.equals("||")) {
			return new OrOperator();
		} else {
			throw new ExpressionParseException(ErrorCode.LOGICAL_NOT_FOUND.message(text));
		}
	}
}
