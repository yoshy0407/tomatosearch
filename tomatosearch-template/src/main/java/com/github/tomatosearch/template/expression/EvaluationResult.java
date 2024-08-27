package com.github.tomatosearch.template.expression;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.EvaluationException;

public class EvaluationResult {

	private final Object value;

	public EvaluationResult(Object value) {
		this.value = value;
	}

	public Class<?> getType() {
		return this.value.getClass();
	}

	public Object getValue() {
		return value;
	}

	public boolean getBooleanValue() {
		if (this.value instanceof Boolean bool) {
			return bool;
		}
		return false;
	}

	public Number toNumber() throws EvaluationException {
		if (this.value instanceof Number num) {
			return num;
		}
		throw new EvaluationException(ErrorCode.CAST_NUMBER_FAILURE.message(this.value.getClass()));
	}
}
