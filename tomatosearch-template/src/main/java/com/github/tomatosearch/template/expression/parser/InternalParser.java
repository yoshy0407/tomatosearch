package com.github.tomatosearch.template.expression.parser;

import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.parser.token.Token;

public interface InternalParser {

	Expression parse(Token token, ParseFunction expressionFunc) throws ExpressionParseException;
}
