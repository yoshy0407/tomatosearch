package com.github.tomatosearch.template.directive;

import java.util.Map;

import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.expression.Expression;

public class ElseIfDirective implements Directive<Boolean> {

	private final Expression expression;

	public ElseIfDirective(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Boolean evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return evaluate(parameter).booleanValue();
	}

	@Override
	public String toText() {
		return "/*%elseif %s */".formatted(expression.toExpressionText());
	}

}
