package com.github.tomatosearch.template.expression.parser.token;

/**
 * トークン分割で使用するテキストオブジェクト
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class Text {

	private final String text;

	public Text(String text) {
		this.text = text;
	}

	public int length() {
		return this.text.length();
	}

	/**
	 * インデックスの数字が長さ以内かチェックします
	 * 
	 * @param index インデックス
	 * @return
	 */
	public boolean isValidPosition(int index) {
		return index < length();
	}

	public Word charAt(int index) {
		return new Word(this.text.charAt(index));
	}

	public String toString() {
		return text;
	}

}
