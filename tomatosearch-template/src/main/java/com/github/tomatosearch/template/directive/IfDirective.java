package com.github.tomatosearch.template.directive;

import java.util.Map;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.expression.Expression;

public class IfDirective implements Directive<Boolean> {

	private final Expression expression;

	public IfDirective(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Boolean evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return evaluate(parameter).booleanValue();
	}

	@Override
	public String toText() {
		return "/*%if %s */".formatted(expression.toExpressionText());
	}

}
