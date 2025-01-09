package com.github.tomatosearch.template.node.template;

import java.util.Map;

import com.github.tomatosearch.template.directive.ParameterDirective;
import com.github.tomatosearch.template.exception.TemplateInternalException;
import com.github.tomatosearch.template.node.JsonNode;

public class ParameterNode implements JsonNode {

	private final ParameterDirective directive;

	public ParameterNode(ParameterDirective directive) {
		this.directive = directive;
	}

	@Override
	public String evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		return this.directive.evaluate(parameter);
	}

	@Override
	public String toJson() {
		return directive.toText();
	}

	@Override
	public String toJsonPretty(int indexSize) {
		return directive.toText();
	}

}
