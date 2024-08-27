package com.github.tomatosearch.template.expression.identifiers.instance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;
import com.github.tomatosearch.template.util.ReflectionUtils;

public class InstanceMethodExpression implements Expression {

	private final Expression expression;

	private final String methodName;

	private final Expression[] args;

	public InstanceMethodExpression(Expression expression, String methodName, Expression[] args) {
		this.expression = expression;
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		List<Object> argValues = new ArrayList<>();
		List<Class<?>> argClasses = new ArrayList<>();
		for (Expression arg : this.args) {
			EvaluationResult evaluationResult = arg.evaluate(parameter);
			argValues.add(evaluationResult.getValue());
			argClasses.add(evaluationResult.getType());
		}
		//@formatter:off
		Object[] argValueArray = argValues.stream()
				.toArray();
		Class<?>[] argClassArray = argClasses.stream()
				.toArray(Class<?>[]::new);
		//@formatter:on

		EvaluationResult result = this.expression.evaluate(parameter);

		Method method = ReflectionUtils.getDeclaredMethod(result.getType(), methodName, argClassArray);

		return new EvaluationResult(ReflectionUtils.invokeMethod(method, result.getValue(), argValueArray));
	}

	@Override
	public String toExpressionText() {
		return expression.toExpressionText() + "." + methodText();
	}

	@Override
	public String toString() {
		return toExpressionText();
	}

	private String methodText() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.methodName).append("(");
		for (Expression arg : this.args) {
			builder.append(arg.toExpressionText()).append(", ");
		}
		if (this.args.length > 0) {
			builder.setLength(builder.length() - 2);
		}
		builder.append(")");
		return builder.toString();
	}

}
