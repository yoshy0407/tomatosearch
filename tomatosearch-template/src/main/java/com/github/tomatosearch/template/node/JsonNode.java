package com.github.tomatosearch.template.node;

import java.util.Map;

import com.github.tomatosearch.template.exception.TemplateInternalException;

/**
 * Jsonのノードを表すインタフェースです
 */
public interface JsonNode {

	/**
	 * パラメータを元にJSONを出力します
	 * 
	 * @param parameter パラメータ
	 * @return JSON
	 */
	String evaluate(Map<String, Object> parameter) throws TemplateInternalException;

	/**
	 * JSON文字列を出力します
	 * 
	 * @return JSON文字列
	 */
	String toJson();

	/**
	 * 整形形式のJSONを出力します
	 * 
	 * @return
	 */
	String toJsonPretty(int indexSize);

}
