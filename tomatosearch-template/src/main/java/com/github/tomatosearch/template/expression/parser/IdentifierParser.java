package com.github.tomatosearch.template.expression.parser;

import java.util.ArrayList;
import java.util.List;

import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.identifiers.function.ExpressionFunctionWrapper;
import com.github.tomatosearch.template.expression.identifiers.function.FunctionExpression;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceFieldExpression;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceMethodExpression;
import com.github.tomatosearch.template.expression.identifiers.parameter.ParameterExpression;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticFieldExpression;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticMethodExpression;
import com.github.tomatosearch.template.expression.parser.token.Token;

public class IdentifierParser implements InternalParser {

	private final ClassLoader classLoader;

	private final ExpressionFunctionWrapper expressionFunctionWrapper;

	public IdentifierParser(ClassLoader classLoader, ExpressionFunctionWrapper expressionFunctionWrapper) {
		this.classLoader = classLoader;
		this.expressionFunctionWrapper = expressionFunctionWrapper;
	}

	@Override
	public Expression parse(Token token, ParseFunction expressionFunc) throws ExpressionParseException {
		return switch (token.getTokenType()) {
		case EMBEDED_FUNCTION_IDENTIFIER -> functionExpression(token, expressionFunc);
		case STATIC_METHOD_IDENTIFIER -> staticMethodExpression(token, expressionFunc);
		case STATIC_FIELD_IDENTIFIER -> staticFieldExpression(token, expressionFunc);
		case INSTANCE_METHOD_IDENTIFIER -> instanceMethodExpression(token, expressionFunc);
		case INSTANCE_FIELD_IDENTIFIER -> instanceFieldExpression(token, expressionFunc);
		default -> new ParameterExpression(token.getToken());
		};
	}

	private FunctionExpression functionExpression(Token token, ParseFunction expressionFunc)
			throws ExpressionParseException {
		// remove @
		String tokenText = token.getToken().substring(1);
		String[] tokens = tokenText.split("\\(|,|\\)");
		String methodName = tokens[0];
		Expression[] args = convertExpression(tokens, 1, expressionFunc);
		return new FunctionExpression(this.expressionFunctionWrapper, methodName, args);
	}

	private StaticMethodExpression staticMethodExpression(Token token, ParseFunction expressionFunc)
			throws ExpressionParseException {
		String[] tokens = token.getToken().split("@|\\(|,|\\)");
		String className = tokens[1];
		String methodName = tokens[2];
		Expression[] args = convertExpression(tokens, 3, expressionFunc);
		return new StaticMethodExpression(this.classLoader, className, methodName, args);
	}

	private StaticFieldExpression staticFieldExpression(Token token, ParseFunction expressionFunc) {
		String[] tokens = token.getToken().split("@");
		String className = tokens[1];
		String field = tokens[2];

		return new StaticFieldExpression(this.classLoader, className, field);
	}

	private InstanceMethodExpression instanceMethodExpression(Token token, ParseFunction expressionFunc)
			throws ExpressionParseException {
		String[] tokens = token.getToken().split("\\.|\\(|,|\\)");
		String parameter = tokens[0];
		String methodName = tokens[1];
		Expression[] args = convertExpression(tokens, 3, expressionFunc);
		return new InstanceMethodExpression(new ParameterExpression(parameter), methodName, args);
	}

	private InstanceFieldExpression instanceFieldExpression(Token token, ParseFunction expressionFunc) {
		String[] tokens = token.getToken().split("\\.");
		String parameter = tokens[0];
		String field = tokens[1];

		return new InstanceFieldExpression(new ParameterExpression(parameter), field);
	}

	private Expression[] convertExpression(String[] tokens, int startIndex, ParseFunction parserFunc)
			throws ExpressionParseException {
		List<Expression> expressions = new ArrayList<>();
		for (int i = startIndex; i < tokens.length; i++) {
			String token = tokens[i];
			expressions.add(parserFunc.apply(token));
		}
		return expressions.toArray(Expression[]::new);
	}

}
