package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * {@link Long}を対象とした{@link TypeCaliculator}の実装クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class LongTypeCaliculator extends AbstractTypeCaliculator<Long> {

	public LongTypeCaliculator() {
		super(Long.class);
	}

	@Override
	public Long plus(Long left, Long right) {
		return left + right;
	}

	@Override
	public Long minus(Long left, Long right) {
		return left - right;
	}

	@Override
	public Long multiple(Long left, Long right) {
		return left * right;
	}

	@Override
	public Long divide(Long left, Long right) {
		return left / right;
	}

	@Override
	public Long remainder(Long left, Long right) {
		return left % right;
	}

}
