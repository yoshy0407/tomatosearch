package com.github.tomatosearch.template.expression.arithmetic.operator.type;

import java.math.BigInteger;

/**
 * {@link BigInteger}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class BigIntegerTypeCaliculator extends AbstractTypeCaliculator<BigInteger> {

	public BigIntegerTypeCaliculator() {
		super(BigInteger.class);
	}

	@Override
	public BigInteger plus(BigInteger left, BigInteger right) {
		return left.add(right);
	}

	@Override
	public BigInteger minus(BigInteger left, BigInteger right) {
		return left.subtract(right);
	}

	@Override
	public BigInteger multiple(BigInteger left, BigInteger right) {
		return left.multiply(right);
	}

	@Override
	public BigInteger divide(BigInteger left, BigInteger right) {
		return left.divide(right);
	}

	@Override
	public BigInteger remainder(BigInteger left, BigInteger right) {
		return left.remainder(right);
	}

}
