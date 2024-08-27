package com.github.tomatosearch.template.expression.instance;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.SampleDto;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.identifiers.instance.InstanceFieldExpression;
import com.github.tomatosearch.template.expression.identifiers.parameter.ParameterExpression;

class InstanceFieldExpressionTest {

	@Test
	void testEvaluate() throws EvaluationException {
		var sut = new InstanceFieldExpression(new ParameterExpression("test"), "id");

		Map<String, Object> parameter = Map.of("test", new SampleDto("testId", "testName"));

		assertThat(sut.evaluate(parameter).getValue()).isEqualTo("testId");
	}

	@Test
	void testToExpressionText() {
		var sut = new InstanceFieldExpression(new ParameterExpression("test"), "id");

		assertThat(sut.toExpressionText()).isEqualTo("test.id");

	}

}
