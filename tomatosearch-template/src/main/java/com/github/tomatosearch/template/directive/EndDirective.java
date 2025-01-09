package com.github.tomatosearch.template.directive;

import java.util.Map;

import com.github.tomatosearch.template.exception.TemplateInternalException;

public class EndDirective implements Directive<Void> {

	@Override
	public Void evaluate(Map<String, Object> parameter) throws TemplateInternalException {
		// do nothing
		return null;
	}

	@Override
	public String toText() {
		return "/*%end*/";
	}

}
