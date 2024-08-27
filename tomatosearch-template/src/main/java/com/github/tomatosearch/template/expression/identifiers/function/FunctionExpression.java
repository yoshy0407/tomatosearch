package com.github.tomatosearch.template.expression.identifiers.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Expression;

public class FunctionExpression implements Expression {

	private final ExpressionFunctionWrapper expressionFunctionWrapper;

	private final String methodName;

	private final Expression[] args;

	public FunctionExpression(ExpressionFunctionWrapper expressionFunctionWrapper, String methodName,
			Expression[] args) {
		this.expressionFunctionWrapper = expressionFunctionWrapper;
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException {
		List<Object> argValues = new ArrayList<>();
		for (Expression arg : this.args) {
			EvaluationResult evaluationResult = arg.evaluate(parameter);
			argValues.add(evaluationResult.getValue());
		}
		//@formatter:off
		Object[] argValueArray = argValues.stream()
				.toArray();
		//@formatter:on

		return new EvaluationResult(this.expressionFunctionWrapper.invoke(methodName, argValueArray));
	}

	@Override
	public String toExpressionText() {
		return "@" + methodText();
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

	@Override
	public String toString() {
		return toExpressionText();
	}

}
