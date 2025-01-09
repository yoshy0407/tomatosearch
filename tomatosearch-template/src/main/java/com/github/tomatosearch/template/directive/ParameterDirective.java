package com.github.tomatosearch.template.directive;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.github.tomatosearch.template.exception.ConversationException;
import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.node.converter.CompositeValueConverter;
import com.github.tomatosearch.template.node.text.TextBuilder;

public class ParameterDirective implements Directive<String> {

	private final Expression expression;

	private final CompositeValueConverter valueConverter;

	public ParameterDirective(Expression expression, CompositeValueConverter valueConverter) {
		this.expression = expression;
		this.valueConverter = valueConverter;
	}

	@Override
	public String evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		EvaluationResult result = this.expression.evaluate(parameter);
		if (result.getValue() instanceof Iterable values) {
			return convertArrayText(values);
		}
		if (result.getValue().getClass().isArray()) {
			Iterable<?> iterable = Arrays.asList((Object[]) result.getValue());
			return convertArrayText(iterable);
		}
		return convertText(result.getValue());
	}

	@Override
	public String toText() {
		return "/* %s */".formatted(this.expression.toExpressionText());
	}

	private String convertText(Object value) throws ConversationException {
		// :TODO not support Options?
		return valueConverter.serialize(value, Collections.emptyMap());
	}

	private String convertArrayText(Iterable<?> values) throws ConversationException {

		TextBuilder builder = new TextBuilder();
		builder.append("[");
		for (Object value : values) {
			builder.append(convertText(value)).commaWithWhitespace();
		}
		builder.removeEnd(1).append("]");
		return builder.toString();
	}

}
