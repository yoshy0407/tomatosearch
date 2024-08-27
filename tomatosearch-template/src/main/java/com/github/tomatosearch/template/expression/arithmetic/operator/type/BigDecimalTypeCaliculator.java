package com.github.tomatosearch.template.expression.arithmetic.operator.type;

import java.math.BigDecimal;

/**
 * {@link BigDecimal}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class BigDecimalTypeCaliculator extends AbstractTypeCaliculator<BigDecimal> {

	public BigDecimalTypeCaliculator() {
		super(BigDecimal.class);
	}

	@Override
	public BigDecimal plus(BigDecimal left, BigDecimal right) {
		return left.add(right);
	}

	@Override
	public BigDecimal minus(BigDecimal left, BigDecimal right) {
		return left.subtract(right);
	}

	@Override
	public BigDecimal multiple(BigDecimal left, BigDecimal right) {
		return left.multiply(right);
	}

	@Override
	public BigDecimal divide(BigDecimal left, BigDecimal right) {
		return left.divide(right);
	}

	@Override
	public BigDecimal remainder(BigDecimal left, BigDecimal right) {
		return left.remainder(right);
	}

}
