package com.github.tomatosearch.template.expression.arithmetic.operator.type;

public class StringTypeCaliculator implements TypeCaliculator<String> {

	@Override
	public boolean isTarget(Object left, Object right) {
		return (left instanceof String) && (right instanceof String);
	}

	@Override
	public String plus(String left, String right) {
		return left + right;
	}

	@Override
	public String minus(String left, String right) {
		throw new IllegalArgumentException("String not support minus");
	}

	@Override
	public String multiple(String left, String right) {
		throw new IllegalArgumentException("String not support multiple");
	}

	@Override
	public String divide(String left, String right) {
		throw new IllegalArgumentException("String not support divide");
	}

	@Override
	public String remainder(String left, String right) {
		throw new IllegalArgumentException("String not support remainder");
	}

}
