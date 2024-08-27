package com.github.tomatosearch.template.expression.literal;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.github.tomatosearch.template.expression.Expression;

/**
 * リテラルを解決するクラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class Literals {

	private static final Pattern charPattern = Pattern.compile("'([^']*)'");

	private static final Pattern stringPattern = Pattern.compile("\"([^\"]*)\"");

	private Literals() {
	}

	public static boolean isLiteral(String literalText) {
		try {
			return resolve(literalText) != null;
		} catch (Exception ex) {
			return false;
		}
	}

	public static Expression resolve(String literalText) {
		if (literalText.equals("null")) {
			return new LiteralExpression(null, "null");

		} else if (literalText.equals("true")) {

			return new LiteralExpression(true, "true");
		} else if (literalText.equals("false")) {

			return new LiteralExpression(false, "false");
		} else if (charPattern.matcher(literalText).find()) {
			return new LiteralExpression(toStringFromLiteral(literalText).toCharArray()[0], literalText);

		} else if (stringPattern.matcher(literalText).find()) {
			return new LiteralExpression(toStringFromLiteral(literalText), literalText);

		} else if (literalText.endsWith("B")) {
			return new LiteralExpression(new BigDecimal(backoff(literalText)), literalText);

		} else if (literalText.endsWith("D")) {
			return new LiteralExpression(Double.parseDouble(backoff(literalText)), literalText);

		} else if (literalText.endsWith("F")) {
			return new LiteralExpression(Float.parseFloat(backoff(literalText)), literalText);

		} else if (literalText.endsWith("L")) {
			return new LiteralExpression(Long.parseLong(backoff(literalText)), literalText);

		} else {
			return new LiteralExpression(Integer.parseInt(literalText), literalText);
		}
	}

	private static String toStringFromLiteral(String literal) {
		return literal.substring(1, literal.length() - 1);
	}

	private static String backoff(String literal) {
		return literal.substring(0, literal.length() - 1);
	}
}
