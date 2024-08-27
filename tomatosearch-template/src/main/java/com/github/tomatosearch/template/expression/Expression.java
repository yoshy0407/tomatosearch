package com.github.tomatosearch.template.expression;

import java.util.Map;

import com.github.tomatosearch.template.exception.EvaluationException;

/**
 * 表現を表すインタフェースです
 *
 * @param <T> 戻り値の型
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public interface Expression {

	/**
	 * 表現の評価結果を返します
	 * 
	 * @param parameter パラメータ
	 * @return 結果
	 */
	EvaluationResult evaluate(Map<String, Object> parameter) throws EvaluationException;

	/**
	 * 表現の文字列を表示します
	 * 
	 * @return 表現の文字列
	 */
	String toExpressionText();
}
