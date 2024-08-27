package com.github.tomatosearch.template.expression.identifiers.statics;

import java.lang.reflect.Field;
import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.util.ReflectionUtils;

public class StaticFieldExpression implements Expression {

	private final ClassLoader classLoader;

	private final String className;

	private final String fieldName;

	public StaticFieldExpression(ClassLoader classLoader, String className, String fieldName) {
		this.classLoader = classLoader;
		this.className = className;
		this.fieldName = fieldName;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		Class<?> clazz = ReflectionUtils.resolveClass(className, classLoader);
		Field field = ReflectionUtils.getDeclaredField(clazz, this.fieldName);
		return new EvaluationResult(ReflectionUtils.getFieldValue(field, null));
	}

	@Override
	public String toExpressionText() {
		return "@%s@%s".formatted(this.className, this.fieldName);
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

}
