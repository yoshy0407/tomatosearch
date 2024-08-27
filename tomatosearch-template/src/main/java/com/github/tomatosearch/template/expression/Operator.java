package com.github.tomatosearch.template.expression;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.parser.token.TokenType;

/**
 * 演算子を表すインタフェースです
 * 
 * @param <L> 左の値の型
 * @param <R> 右の値の型
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public interface Operator<T> {

	/**
	 * 評価を実施します
	 * 
	 * @param left  左の値
	 * @param right 右の値
	 * @return 評価結果
	 * @throws EvaluationFailureException 評価に失敗した際に投げる例外
	 */
	T evaluate(EvaluationResult left, EvaluationResult right) throws EvaluationException;

	/**
	 * 評価のテキストを表示します
	 * 
	 * @return 評価のテキスト
	 */
	String toExpressionText();

	/**
	 * 処理順序
	 * 
	 * @return 処理順序
	 */
	int getOrder();

	/**
	 * トークンタイプ
	 * 
	 * @return トークタイプ
	 */
	TokenType getTokenType();
}
