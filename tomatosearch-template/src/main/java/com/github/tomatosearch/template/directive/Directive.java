package com.github.tomatosearch.template.directive;

import java.util.Map;

import com.github.tomatosearch.template.exception.TemplateInternalException;

/**
 * ディレクティブを表すインタフェースです
 *
 * @param <R> 判定結果の型
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public interface Directive<R> {

	/**
	 * 評価結果を出力します
	 * 
	 * @param parameter パラメータ
	 * @return 評価結果
	 * @throws TemplateInternalException 処理エラー
	 */
	R evaluate(Map<String, Object> parameter) throws TemplateInternalException;

	/**
	 * テキスト表示
	 * 
	 * @return
	 */
	String toText();

}
