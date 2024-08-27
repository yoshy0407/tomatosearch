package com.github.tomatosearch.template.expression.statics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticFieldExpression;

class StaticFieldExpressionTest {

	@Test
	void testEvaluate() throws EvaluationException {
		var sut = new StaticFieldExpression(getClass().getClassLoader(), "java.lang.Integer", "MAX_VALUE");

		assertThat(sut.evaluate(Map.of()).getValue()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	void testToExpressionText() {
		var sut = new StaticFieldExpression(getClass().getClassLoader(), "java.lang.Integer", "MAX_VALUE");

		assertThat(sut.toExpressionText()).isEqualTo("@java.lang.Integer@MAX_VALUE");
	}

}
