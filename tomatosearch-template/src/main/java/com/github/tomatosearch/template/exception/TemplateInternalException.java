package com.github.tomatosearch.template.exception;

/**
 * 内部で使用する例外クラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class TemplateInternalException extends Exception {

	private static final long serialVersionUID = 1L;

	public TemplateInternalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemplateInternalException(String message) {
		super(message);
	}

}
