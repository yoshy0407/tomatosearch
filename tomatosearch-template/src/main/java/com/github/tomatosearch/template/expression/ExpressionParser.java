package com.github.tomatosearch.template.expression;

import java.util.List;
import java.util.Stack;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.ExpressionFunction;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.arithmetic.ArithmeticExpression;
import com.github.tomatosearch.template.expression.arithmetic.Arithmetics;
import com.github.tomatosearch.template.expression.comparison.ComparisonExpression;
import com.github.tomatosearch.template.expression.comparison.Comparisons;
import com.github.tomatosearch.template.expression.delimiters.ParenthesesOperator;
import com.github.tomatosearch.template.expression.identifiers.function.ExpressionFunctionWrapper;
import com.github.tomatosearch.template.expression.logical.LogicalExpression;
import com.github.tomatosearch.template.expression.logical.Logicals;
import com.github.tomatosearch.template.expression.logical.NotLogicalExpression;
import com.github.tomatosearch.template.expression.logical.operator.NotOperator;
import com.github.tomatosearch.template.expression.parser.IdentifierParser;
import com.github.tomatosearch.template.expression.parser.LiteralParser;
import com.github.tomatosearch.template.expression.parser.ParseFunction;
import com.github.tomatosearch.template.expression.parser.token.Token;
import com.github.tomatosearch.template.expression.parser.token.TokenType;
import com.github.tomatosearch.template.expression.parser.token.Tokenizer;

public class ExpressionParser {

	private final ClassLoader classLoader;

	private final LiteralParser literalParser;

	private final IdentifierParser identifierParser;

	public ExpressionParser(ClassLoader classLoader, ExpressionFunction expressionFunction) {
		this.classLoader = classLoader;
		this.literalParser = new LiteralParser();
		this.identifierParser = new IdentifierParser(this.classLoader,
				new ExpressionFunctionWrapper(expressionFunction));
	}

	public Expression parse(String text) throws ExpressionParseException {
		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		Stack<Expression> nodes = new Stack<>();
		Stack<Operator<?>> operators = new Stack<>();

		for (Token token : tokens) {
			switch (token.getTokenType()) {
			case IDENTIFIER:
			case STATIC_FIELD_IDENTIFIER:
			case STATIC_METHOD_IDENTIFIER:
			case INSTANCE_FIELD_IDENTIFIER:
			case INSTANCE_METHOD_IDENTIFIER:
			case EMBEDED_FUNCTION_IDENTIFIER:
				nodes.push(this.identifierParser.parse(token, doParse()));
				break;
			case LITERAL:
				nodes.push(this.literalParser.parse(token, doParse()));
				break;

			case COMPARISON_OPERATOR:
			case LOGICAL_OPERATOR:
			case ARITHMETIC_OPERATOR:
				if (token.getToken().equals("!")) {
					operators.push(new NotOperator());
				} else {
					Operator<?> operator = resolveOperator(token);
					while (!operators.isEmpty() && operators.peek().getOrder() >= operator.getOrder()) {
						Operator<?> op = operators.pop();
						if (op.toExpressionText().equals("!")) {
							Expression right = nodes.pop();
							Expression notExpression = new NotLogicalExpression(right);
							nodes.push(notExpression);
						} else {
							Expression right = nodes.pop();
							Expression left = nodes.pop();
							Expression expression = resolveOperatorExpression(left, op, right);
							nodes.push(expression);
						}
					}
					operators.push(operator);
				}
				break;
			case DELIMITER:
				if (token.getToken().equals("(")) {
					operators.push(ParenthesesOperator.START);
				} else if (token.getToken().equals(")")) {
					while (!operators.peek().equals(ParenthesesOperator.START)) {
						Operator<?> op = operators.pop();
						if (op.toExpressionText().equals("!")) {
							Expression right = nodes.pop();
							Expression notExpression = new NotLogicalExpression(right);
							nodes.push(notExpression);
						} else {
							Expression right = nodes.pop();
							Expression left = nodes.pop();
							Expression expression = resolveOperatorExpression(left, op, right);
							nodes.push(expression);
						}
					}
					operators.pop(); // Remove (
				}
			default:
				break;
			}
		}

		while (!operators.isEmpty()) {
			Expression right = nodes.pop();
			Expression left = nodes.pop();
			Operator<?> op = operators.pop();
			Expression operatorExpression = resolveOperatorExpression(left, op, right);
			nodes.push(operatorExpression);
		}

		return nodes.pop();

	}

	private Operator<?> resolveOperator(Token token) throws ExpressionParseException {
		switch (token.getTokenType()) {
		case COMPARISON_OPERATOR -> {
			return Comparisons.resolve(token.getToken());
		}
		case LOGICAL_OPERATOR -> {
			return Logicals.resolve(token.getToken());
		}
		case ARITHMETIC_OPERATOR -> {
			return Arithmetics.resolve(token.getToken());
		}
		default -> {
			throw new ExpressionParseException(ErrorCode.UNKNOWN_OPERATOR.message(token.getToken()));
		}
		}
	}

	@SuppressWarnings("unchecked")
	private Expression resolveOperatorExpression(Expression left, Operator<?> op, Expression right)
			throws ExpressionParseException {
		if (op.getTokenType().equals(TokenType.COMPARISON_OPERATOR)) {
			return new ComparisonExpression(left, (Operator<Boolean>) op, right);

		} else if (op.getTokenType().equals(TokenType.LOGICAL_OPERATOR)) {
			return new LogicalExpression(left, (Operator<Boolean>) op, right);

		} else if (op.getTokenType().equals(TokenType.ARITHMETIC_OPERATOR)) {
			return new ArithmeticExpression(left, (Operator<Number>) op, right);
		}
		throw new ExpressionParseException(ErrorCode.UNKNOWN_OPERATOR.message(op.toExpressionText()));
	}

	private ParseFunction doParse() {
		return (text) -> parse(text);
	}

}
