package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * {@link TypeCaliculator}の抽象クラスです
 *
 * @param <T> 対象の型
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public abstract class AbstractTypeCaliculator<T extends Number> implements TypeCaliculator<T> {

	/**
	 * 対象の型のクラス
	 */
	private final Class<T> clazz;

	protected AbstractTypeCaliculator(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean isTarget(Object left, Object right) {
		return clazz.isAssignableFrom(left.getClass()) && clazz.isAssignableFrom(right.getClass());
	}

}
