package com.github.tomatosearch.template.expression.identifiers.function;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.ExpressionFunction;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.util.ReflectionUtils;

public class ExpressionFunctionWrapper {

	private final ExpressionFunction expressionFunction;

	private final Map<String, List<Method>> methodMap = new HashMap<>();

	public ExpressionFunctionWrapper(ExpressionFunction expressionFunction) {
		this.expressionFunction = expressionFunction;
		Method[] methods = expressionFunction.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			List<Method> methodList = methodMap.getOrDefault(methodName, new ArrayList<>());
			methodList.add(method);
			if (!methodMap.containsKey(methodName)) {
				methodMap.put(methodName, methodList);
			}
		}
	}

	public Object invoke(String methodName, Object[] args) throws EvaluationException {
		List<Method> methodList = this.methodMap.get(methodName);
		if (methodList == null) {
			throw new EvaluationException(
					ErrorCode.EXPRESSION_FUNCTION_METHOD_NOT_FOUNT.message(methodName, args.toString()));
		}
		if (methodList.size() == 1) {
			return ReflectionUtils.invokeMethod(methodList.get(0), this.expressionFunction, args);
		}

		for (Method method : methodList) {
			if (method.getParameterCount() == args.length) {
				return ReflectionUtils.invokeMethod(method, expressionFunction, args);
			}
		}
		throw new EvaluationException(
				ErrorCode.EXPRESSION_FUNCTION_METHOD_NOT_FOUNT.message(methodName, args.toString()));
	}
}
