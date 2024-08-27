package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * {@link Integer}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class IntegerTypeCaliculator extends AbstractTypeCaliculator<Integer> {

	public IntegerTypeCaliculator() {
		super(Integer.class);
	}

	@Override
	public Integer plus(Integer left, Integer right) {
		return left + right;
	}

	@Override
	public Integer minus(Integer left, Integer right) {
		return left - right;
	}

	@Override
	public Integer multiple(Integer left, Integer right) {
		return left * right;
	}

	@Override
	public Integer divide(Integer left, Integer right) {
		return left / right;
	}

	@Override
	public Integer remainder(Integer left, Integer right) {
		return left % right;
	}

}
