package com.github.tomatosearch.template.exception;

/**
 * 変換エラー時に発生する例外です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class ConversationException extends TemplateInternalException {

	private static final long serialVersionUID = 1L;

	public ConversationException(String message) {
		super(message);
	}

	public ConversationException(String message, Throwable ex) {
		super(message, ex);
	}

}
