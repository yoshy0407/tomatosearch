package com.github.tomatosearch.template.exception;

/**
 * 評価時にエラーが発生した場合に投げられる例外です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class EvaluationException extends TemplateInternalException {

	private static final long serialVersionUID = 1L;

	public EvaluationException(String message) {
		super(message);
	}

	public EvaluationException(String message, Throwable ex) {
		super(message, ex);
	}

}
