package com.github.tomatosearch.template.directive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.expression.identifiers.parameter.ParameterExpression;
import com.github.tomatosearch.template.node.converter.CompositeValueConverter;

class ParameterDirectiveTest {

	ParameterDirective directive = new ParameterDirective(new ParameterExpression("value"), new CompositeValueConverter(
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.SSS", "HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.SSS Z VV"));

	@Test
	void testEvaluate_array() throws TemplateInternalException {
		Map<String, Object> parameter = Map.of("value", new String[] { "test1", "test2", "test3" });

		String text = directive.evaluate(parameter);

		assertThat(text).isEqualTo("[\"test1\", \"test2\", \"test3\"]");
	}

	@Test
	void testEvaluate_iterable() throws TemplateInternalException {
		Map<String, Object> parameter = Map.of("value", List.of("test1", "test2", "test3"));

		String text = directive.evaluate(parameter);

		assertThat(text).isEqualTo("[\"test1\", \"test2\", \"test3\"]");
	}

	@Test
	void testEvaluate_single() throws TemplateInternalException {
		Map<String, Object> parameter = Map.of("value", 10000L);

		String text = directive.evaluate(parameter);

		assertThat(text).isEqualTo("10000");
	}

	@Test
	void testToText() {
		String text = directive.toText();
		assertThat(text).isEqualTo("/* value */");
	}

}
