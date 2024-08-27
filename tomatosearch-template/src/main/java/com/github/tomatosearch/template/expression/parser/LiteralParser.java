package com.github.tomatosearch.template.expression.parser;

import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.literal.Literals;
import com.github.tomatosearch.template.expression.parser.token.Token;

public class LiteralParser implements InternalParser {

	@Override
	public Expression parse(Token token, ParseFunction expressionFunc) throws ExpressionParseException {
		return Literals.resolve(token.getToken());
	}

}
