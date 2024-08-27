package com.github.tomatosearch.template.exception;

/**
 * リフレクション時の例外時に発生する例外です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class ReflectionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReflectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReflectionException(String message) {
		super(message);
	}

	public ReflectionException(Throwable cause) {
		super(cause);
	}

}
