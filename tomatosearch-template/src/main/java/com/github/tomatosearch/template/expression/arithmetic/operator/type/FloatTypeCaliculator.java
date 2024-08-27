package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * {@link Float}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class FloatTypeCaliculator extends AbstractTypeCaliculator<Float> {

	public FloatTypeCaliculator() {
		super(Float.class);
	}

	@Override
	public Float plus(Float left, Float right) {
		return left + right;
	}

	@Override
	public Float minus(Float left, Float right) {
		return left - right;
	}

	@Override
	public Float multiple(Float left, Float right) {
		return left * right;
	}

	@Override
	public Float divide(Float left, Float right) {
		return left / right;
	}

	@Override
	public Float remainder(Float left, Float right) {
		return left % right;
	}

}
