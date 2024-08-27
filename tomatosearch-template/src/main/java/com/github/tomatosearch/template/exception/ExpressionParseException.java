package com.github.tomatosearch.template.exception;

/**
 * 表現のパース時に発生する例外クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class ExpressionParseException extends TemplateInternalException {

	private static final long serialVersionUID = 1L;

	public ExpressionParseException(String message) {
		super(message);
	}

	public ExpressionParseException(String message, Throwable ex) {
		super(message, ex);
	}

}
