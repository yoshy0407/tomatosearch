package com.github.tomatosearch.template.expression.statics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.identifiers.statics.StaticMethodExpression;
import com.github.tomatosearch.template.expression.literal.LiteralExpression;

class StaticMethodExpressionTest {

	@Test
	void testEvaluate() throws EvaluationException {
		var sut = new StaticMethodExpression(getClass().getClassLoader(), "java.lang.Integer", "parseInt",
				new Expression[] { new LiteralExpression("100", "\"100\"") });

		assertThat(sut.evaluate(Map.of()).getValue()).isEqualTo(100);
	}

	@Test
	void testToExpressionText() {
		var sut = new StaticMethodExpression(getClass().getClassLoader(), "java.lang.Integer", "parseInt",
				new Expression[] { new LiteralExpression("100", "\"100\"") });

		assertThat(sut.toExpressionText()).isEqualTo("@java.lang.Integer@parseInt(\"100\")");
	}

}
