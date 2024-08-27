package com.github.tomatosearch.template.expression.comparison;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.exception.ExpressionParseException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;

class ComparisonsTest {

	@Test
	void testResolveEqual() throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve("==");

		assertThat(comparison.evaluate(result("test"), result("test"))).isTrue();
	}

	@Test
	void testResolveNotEqual() throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve("!=");

		assertThat(comparison.evaluate(result("test"), result("test"))).isFalse();
	}

	@ParameterizedTest
	//@formatter:off
	@CsvSource({ 
		"1, <, 2, true",
		"2, >, 1, true",
		"1, <, 1, false",
		"1, >, 1, false",
	})
	//@formatter:on
	void testResolveLessThan(int left, String comparisonText, int right, boolean expect)
			throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve(comparisonText);

		assertThat(comparison.evaluate(result(left), result(right))).isEqualTo(expect);
	}

	@Test
	void testResolveLessThanError() throws ExpressionParseException {
		Operator<Boolean> comparison = Comparisons.resolve("<");

		assertThrows(EvaluationException.class, () -> {
			comparison.evaluate(result("test"), result(2));
		});

	}

	@ParameterizedTest
	//@formatter:off
	@CsvSource({ 
		"1, <=, 2, true",
		"1, =<, 2, true",
		"2, >=, 1, true",
		"2, =>, 1, true",
		"1, <=, 1, true",
	})
	//@formatter:on
	void testResolveLessThanEqualTrue() throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve("<=");
		assertThat(comparison.evaluate(result(1), result(2))).isTrue();

		Operator<Boolean> comparison2 = Comparisons.resolve("=<");
		assertThat(comparison2.evaluate(result(1), result(2))).isTrue();
	}

	@Test
	void testResolveLessThanEqualError() throws ExpressionParseException {
		Operator<Boolean> comparison = Comparisons.resolve("<=");

		assertThrows(EvaluationException.class, () -> {
			comparison.evaluate(result("test"), result(2));
		});

	}

	@ParameterizedTest
	//@formatter:off
	@CsvSource({ 
		"2, >, 1, true",
		"1, <, 2, true",
		"1, >, 1, false",
		"1, <, 1, false",
	})
	//@formatter:on
	void testResolveGreaterThanTrue() throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve(">");

		assertThat(comparison.evaluate(result(2), result(1))).isTrue();
	}

	@Test
	void testResolveGreaterThanError() throws ExpressionParseException {
		Operator<Boolean> comparison = Comparisons.resolve(">");

		assertThrows(EvaluationException.class, () -> {
			comparison.evaluate(result("test"), result(2));
		});

	}

	@ParameterizedTest
	//@formatter:off
	@CsvSource({ 
		"2, >=, 1, true",
		"2, =>, 1, true",
		"1, <=, 2, true",
		"1, =<, 1, true",
		"1, >=, 1, true",
	})
	//@formatter:on
	void testResolveGreaterThanEqualTrue() throws ExpressionParseException, EvaluationException {
		Operator<Boolean> comparison = Comparisons.resolve(">=");
		assertThat(comparison.evaluate(result(2), result(1))).isTrue();

		Operator<Boolean> comparison2 = Comparisons.resolve("=>");
		assertThat(comparison2.evaluate(result(2), result(1))).isTrue();
	}

	@Test
	void testResolveGreaterThanEqualError() throws ExpressionParseException {
		Operator<Boolean> comparison = Comparisons.resolve(">=");

		assertThrows(EvaluationException.class, () -> {
			comparison.evaluate(result("test"), result(2));
		});

	}

	private EvaluationResult result(Object value) {
		return new EvaluationResult(value);
	}

}
