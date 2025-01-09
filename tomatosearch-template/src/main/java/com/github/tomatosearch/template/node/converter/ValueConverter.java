package com.github.tomatosearch.template.node.converter;

import java.util.Map;

/**
 * JSON文字列の形式に変換するコンバーターです
 *
 * @param
 * @author yoshiokahiroshi
 * @since
 */
public interface ValueConverter<T> {

	/**
	 * 変換対象かどうかチェックします
	 * 
	 * @param value 値
	 * @return 対象かどうか
	 */
	boolean isTarget(Object value);

	/**
	 * 値をJSON文字列に変換します
	 * 
	 * @param value 値
	 * @return JSON文字列後の値
	 */
	String serialize(Object value, Map<String, Object> option);

	/**
	 * JSON文字列から値に変換します
	 * 
	 * @param value JSON文字列の値
	 * @return 変換後の値
	 */
	T deserialize(String value, Map<String, Object> option);
}
