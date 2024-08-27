package com.github.tomatosearch.template.expression.parser.token;

/**
 * トークンのタイプを表す列挙型です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public enum TokenType {
	//@formatter:off
	/**
	 * 変数名や関数名
	 */
	IDENTIFIER(0), 
	/**
	 * 組み込み関数
	 */
	EMBEDED_FUNCTION_IDENTIFIER(0), 
	/**
	 * 静的フィールド
	 */
	STATIC_FIELD_IDENTIFIER(0), 
	/**
	 * 静的メソッド
	 */
	STATIC_METHOD_IDENTIFIER(0), 
	/**
	 * インスタンスフィールド
	 */
	INSTANCE_FIELD_IDENTIFIER(0), 
	/**
	 * インスタンスメソッド
	 */
	INSTANCE_METHOD_IDENTIFIER(0), 
	/**
	 * 文字列リテラル
	 */
	LITERAL(0), 
	/**
	 * 比較演算子
	 */
	COMPARISON_OPERATOR(2), 
	/**
	 * 論理演算子
	 */
	LOGICAL_OPERATOR(1), 
	/**
	 * 算術演算子
	 */
	ARITHMETIC_OPERATOR(3), 
	/**
	 * 区切り文字
	 */
	DELIMITER(0);
	//@formatter:on

	private final int order;

	private TokenType(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

}
