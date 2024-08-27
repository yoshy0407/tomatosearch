package com.github.tomatosearch.template.node;

/**
 * Jsonのノードを表すインタフェースです
 */
public interface Node {

	/**
	 * JSON文字列を出力します
	 * @return JSON文字列
	 */
	String toJson();
	
	/**
	 * 整形形式のJSONを出力します
	 * @return
	 */
	String toJsonPretty(int indexSize);
	
}
