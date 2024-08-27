package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * {@link Double}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class DoubleTypeCaliculator extends AbstractTypeCaliculator<Double> {

	public DoubleTypeCaliculator() {
		super(Double.class);
	}

	@Override
	public Double plus(Double left, Double right) {
		return left + right;
	}

	@Override
	public Double minus(Double left, Double right) {
		return left - right;
	}

	@Override
	public Double multiple(Double left, Double right) {
		return left * right;
	}

	@Override
	public Double divide(Double left, Double right) {
		return left / right;
	}

	@Override
	public Double remainder(Double left, Double right) {
		return left % right;
	}

}
