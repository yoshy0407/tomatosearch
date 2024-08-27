package com.github.tomatosearch.template.expression.arithmetic.operator;

import java.util.List;
import java.util.Optional;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.EvaluationResult;
import com.github.tomatosearch.template.expression.Operator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.BigDecimalTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.BigIntegerTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.DoubleTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.FloatTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.IntegerTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.LongTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.StringTypeCaliculator;
import com.github.tomatosearch.template.expression.arithmetic.operator.type.TypeCaliculator;

public abstract class AbstractArithmeticOperator implements Operator<Object> {

	//@formatter:off
	private final List<TypeCaliculator<? extends Object>> number = List.of(
			new BigDecimalTypeCaliculator(), 
			new BigIntegerTypeCaliculator(), 
			new DoubleTypeCaliculator(),
			new FloatTypeCaliculator(),
			new IntegerTypeCaliculator(),
			new LongTypeCaliculator(),
			new StringTypeCaliculator());
	//@formatter:on

	@SuppressWarnings("unchecked")
	@Override
	public Object evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException {
		Object leftValue = left.getValue();
		Object rightValue = right.getValue();

		//@formatter:off
		Optional<TypeCaliculator<?>> optNumberType = number.stream()
				.filter(t -> t.isTarget(leftValue, rightValue))
				.findFirst();
		//@formatter:on

		if (optNumberType.isEmpty()) {
			throw new EvaluationException(ErrorCode.NOT_SUPPORT_NUMBER_TYPE.message(leftValue.getClass()));
		}

		return doCaliculate((TypeCaliculator<Object>) optNumberType.get(), leftValue, rightValue);
	}

	protected abstract Object doCaliculate(TypeCaliculator<Object> type, Object left, Object right);
}
