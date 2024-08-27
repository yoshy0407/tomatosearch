package com.github.tomatosearch.template.expression.arithmetic.operator.type;

/**
 * 型ごとに数値の計算を行うインタフェースです
 *
 * @param <N> 対象とする型
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public interface TypeCaliculator<N> {

	/**
	 * 数値が対象の型であることを判別します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 判定結果
	 */
	boolean isTarget(Object left, Object right);

	/**
	 * 足し算を実施します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 足し算の結果
	 */
	N plus(N left, N right);

	/**
	 * 引き算を実施します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 引き算の結果
	 */
	N minus(N left, N right);

	/**
	 * 掛け算を実施します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 掛け算の結果
	 */
	N multiple(N left, N right);

	/**
	 * 割り算を実施します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 割り算の結果
	 */
	N divide(N left, N right);

	/**
	 * 剰余算を実施します
	 * 
	 * @param left  左側の値
	 * @param right 右側の値
	 * @return 剰余算の結果
	 */
	N remainder(N left, N right);

}
