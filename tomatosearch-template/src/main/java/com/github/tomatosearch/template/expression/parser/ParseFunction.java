package com.github.tomatosearch.template.expression.parser;

import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Expression;

@FunctionalInterface
public interface ParseFunction {

	Expression apply(String text) throws ExpressionParseException;

}
