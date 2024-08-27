package com.github.tomatosearch.template.expression.instance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.SampleDto;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceMethodExpression;
import com.github.tomatosearch.template.expression.identifiers.parameter.ParameterExpression;

class InstanceMethodExpressionTest {

	@Test
	void testNoArgMethod() throws EvaluationException {
		var sut = new InstanceMethodExpression(new ParameterExpression("sample"), "getText", new Expression[] {});

		SampleDto dto = new SampleDto("testId", "name");

		Map<String, Object> parameter = Map.of("sample", dto);

		EvaluationResult result = sut.evaluate(parameter);

		assertThat(result.getValue()).isEqualTo("[testId]name");
	}

	@Test
	void testToExpressionText() {
		var sut = new InstanceMethodExpression(new ParameterExpression("sample"), "getText", new Expression[] {});

		assertThat(sut.toExpressionText()).isEqualTo("sample.getText()");

	}

}
