package com.github.tomatosearch.template;

import java.text.MessageFormat;

public enum ErrorCode {
	COMPARISON_NOT_FOUND("10001", "comparison operator is invalid. value: {0}"),
	NOT_COMPABLE("10002", "value is not implemented Comparable. left: {0} comparison: {1} right: {2}"),
	CONCATE_FAILURE("10003", "concate failure. left: {0} right: {1}"),
	EXPRESSION_FUNCTION_METHOD_NOT_FOUNT("10004", "cannot find method. method: {0} args: {2}"),
	CAST_NUMBER_FAILURE("10005", "cannot cast number. type: {0}"),
	NOT_SUPPORT_NUMBER_TYPE("10006", "not support number type. type: {0}"),
	CALICULATE_NOT_FOUND("10007", "caliculation operator is invalid. value: {0}"),
	LOGICAL_NOT_FOUND("10008", "logical operator is invalid. value: {0}"),
	UNKNOWN_OPERATOR("10009", "unknown operator. value: {0}");

	private final String errorCode;

	private final MessageFormat messageFormat;

	private ErrorCode(String errorCode, String format) {
		this.errorCode = errorCode;
		this.messageFormat = new MessageFormat(format);
	}

	public String message(Object... args) {
		return "[" + this.errorCode + "] " + this.messageFormat.format(args);
	}

}
