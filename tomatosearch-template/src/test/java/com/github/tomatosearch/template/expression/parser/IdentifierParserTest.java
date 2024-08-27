package com.github.tomatosearch.template.expression.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.SampleDto;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.StandardExpressionFunction;
import com.github.tomatosearch.template.expression.identifiers.function.ExpressionFunctionWrapper;
import com.github.tomatosearch.template.expression.identifiers.function.FunctionExpression;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceFieldExpression;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceMethodExpression;
import com.github.tomatosearch.template.expression.identifiers.parameter.ParameterExpression;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticFieldExpression;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticMethodExpression;
import com.github.tomatosearch.template.expression.literal.Literals;
import com.github.tomatosearch.template.expression.parser.token.Token;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

class IdentifierParserTest {

	IdentifierParser sut;

	ParseFunction expressionFunc;

	@BeforeEach
	void setup() {
		sut = new IdentifierParser(getClass().getClassLoader(),
				new ExpressionFunctionWrapper(new StandardExpressionFunction()));
		expressionFunc = (token) -> Literals.resolve(token);
	}

	@Test
	void testFunction() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(new Token(TokenType.EMBEDED_FUNCTION_IDENTIFIER, "@prefix(\"test\")"),
				expressionFunc);

		assertThat(expression).isInstanceOf(FunctionExpression.class);

		Map<String, Object> parameter = Map.of();

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo("test*");
		assertThat(result.getType()).isEqualTo(String.class);
	}

	@Test
	void testStaticMethod() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(
				new Token(TokenType.STATIC_METHOD_IDENTIFIER, "@java.lang.Integer@parseInt(\"1000\")"), expressionFunc);

		assertThat(expression).isInstanceOf(StaticMethodExpression.class);

		Map<String, Object> parameter = Map.of();

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo(1000);
		assertThat(result.getType()).isEqualTo(Integer.class);
	}

	@Test
	void testParseStaticField() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(new Token(TokenType.STATIC_FIELD_IDENTIFIER, "@java.lang.Integer@MAX_VALUE"),
				expressionFunc);

		assertThat(expression).isInstanceOf(StaticFieldExpression.class);

		Map<String, Object> parameter = Map.of();

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo(Integer.MAX_VALUE);
		assertThat(result.getType()).isEqualTo(Integer.class);
	}

	@Test
	void testParseInstanceMethod() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(new Token(TokenType.INSTANCE_METHOD_IDENTIFIER, "instance.getText()"),
				expressionFunc);

		assertThat(expression).isInstanceOf(InstanceMethodExpression.class);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("001", "TestName"));

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo("[001]TestName");
		assertThat(result.getType()).isEqualTo(String.class);
	}

	@Test
	void testParseInstanceField() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(new Token(TokenType.INSTANCE_FIELD_IDENTIFIER, "instance.name"),
				expressionFunc);

		assertThat(expression).isInstanceOf(InstanceFieldExpression.class);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("001", "TestName"));

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo("TestName");
		assertThat(result.getType()).isEqualTo(String.class);
	}

	@Test
	void testParseParameter() throws ExpressionParseException, EvaluationException {
		Expression expression = sut.parse(new Token(TokenType.IDENTIFIER, "sample"), expressionFunc);

		assertThat(expression).isInstanceOf(ParameterExpression.class);

		Map<String, Object> parameter = Map.of("sample", 100L);

		EvaluationResult result = expression.evaluate(parameter);
		assertThat(result.getValue()).isEqualTo(100L);
		assertThat(result.getType()).isEqualTo(Long.class);
	}
}
