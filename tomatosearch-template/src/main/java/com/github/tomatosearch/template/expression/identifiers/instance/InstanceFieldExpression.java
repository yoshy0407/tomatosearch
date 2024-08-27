package com.github.tomatosearch.template.expression.identifiers.instance;

import java.lang.reflect.Field;
import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.util.ReflectionUtils;

public class InstanceFieldExpression implements Expression {

	private final Expression expression;

	private final String fieldName;

	public InstanceFieldExpression(Expression expression, String fieldName) {
		this.expression = expression;
		this.fieldName = fieldName;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		EvaluationResult result = expression.evaluate(parameter);
		Field field = ReflectionUtils.getDeclaredField(result.getType(), fieldName);
		field.setAccessible(true);
		return new EvaluationResult(ReflectionUtils.getFieldValue(field, result.getValue()));
	}

	@Override
	public String toExpressionText() {
		return expression.toExpressionText() + "." + fieldName;
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
