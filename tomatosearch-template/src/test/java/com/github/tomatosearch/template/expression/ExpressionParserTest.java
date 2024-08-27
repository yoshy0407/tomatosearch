package com.github.tomatosearch.template.expression;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.SampleDto;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.exception.ExpressionParseException;

class ExpressionParserTest {

	ExpressionParser parser = new ExpressionParser(getClass().getClassLoader(), new StandardExpressionFunction());

	@Test
	void testParseParameter() throws ExpressionParseException, EvaluationException {
		String text = "instance.name";

		Expression expression = parser.parse(text);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("testId", "testName"));

		EvaluationResult result = expression.evaluate(parameter);

		assertThat(result.getValue()).isEqualTo("testName");
		assertThat(result.getType()).isEqualTo(String.class);
	}

	@Test
	void testParseMix() throws ExpressionParseException, EvaluationException {
		String text = "(@prefix(instance.name) == \"testName*\") && (number < 10)";

		Expression expression = parser.parse(text);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("testId", "testName"), "number", 9);

		EvaluationResult result = expression.evaluate(parameter);

		assertThat(result.getValue()).isEqualTo(true);
		assertThat(result.getType()).isEqualTo(Boolean.class);
	}

	@Test
	void testParseMix2() throws ExpressionParseException, EvaluationException {
		String text = "(@prefix(instance.name) == \"testName\" + \"*\") && (number < 10)";

		Expression expression = parser.parse(text);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("testId", "testName"), "number", 9);

		EvaluationResult result = expression.evaluate(parameter);

		assertThat(result.getValue()).isEqualTo(true);
		assertThat(result.getType()).isEqualTo(Boolean.class);
	}

	@Test
	void testParseMix3() throws ExpressionParseException, EvaluationException {
		String text = "!(@prefix(instance.name) == \"testName*\") && (number < 10)";

		Expression expression = parser.parse(text);

		Map<String, Object> parameter = Map.of("instance", new SampleDto("testId", "testName"), "number", 9);

		EvaluationResult result = expression.evaluate(parameter);

		assertThat(result.getValue()).isEqualTo(false);
		assertThat(result.getType()).isEqualTo(Boolean.class);
	}

}
