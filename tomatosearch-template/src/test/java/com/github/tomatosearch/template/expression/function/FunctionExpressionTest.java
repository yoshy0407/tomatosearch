package com.github.tomatosearch.template.expression.function;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.StandardExpressionFunction;
import com.github.tomatosearch.template.expression.identifiers.function.ExpressionFunctionWrapper;
import com.github.tomatosearch.template.expression.identifiers.function.FunctionExpression;
import com.github.tomatosearch.template.expression.literal.LiteralExpression;

class FunctionExpressionTest {

	ExpressionFunctionWrapper wrapper = new ExpressionFunctionWrapper(new StandardExpressionFunction());

	@Test
	void testEvaluate() throws EvaluationException {
		var sut = new FunctionExpression(wrapper, "prefix",
				new Expression[] { new LiteralExpression("test", "\"test\"") });

		assertThat(sut.evaluate(Map.of()).getValue()).isEqualTo("test*");
	}

	@Test
	void testToExpressionText() {
		var sut = new FunctionExpression(wrapper, "isEmpty", new Expression[] { new LiteralExpression(null, "null") });

		assertThat(sut.toExpressionText()).isEqualTo("@isEmpty(null)");
	}

}
