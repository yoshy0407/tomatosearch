package com.github.tomatosearch.template.expression;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.tomatosearch.template.ExpressionFunction;

/**
 * {@link ExpressionFunction}の標準的な実装です
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class StandardExpressionFunction implements ExpressionFunction {

	private static final char DEFAULT_ESCAPE_CHAR = '\\';

	private static final char[] DEFAULT_WILDCARDS = { '*' };

	private final char[] wildcards;

	public StandardExpressionFunction() {
		this(DEFAULT_WILDCARDS);
	}

	public StandardExpressionFunction(char[] wildcards) {
		this.wildcards = wildcards;
	}

	@Override
	public String escape(CharSequence text, char escapeChar) {
		if (text == null) {
			return null;
		}
		return escapeWildcard(text, escapeChar);
	}

	@Override
	public String prefix(CharSequence text) {
		if (text == null) {
			return null;
		}
		return escape(text, DEFAULT_ESCAPE_CHAR) + "*";
	}

	@Override
	public String prefix(CharSequence text, char escapeChar) {
		if (text == null) {
			return null;
		}
		return escape(text, escapeChar) + "*";
	}

	@Override
	public String infix(CharSequence text) {
		if (text == null) {
			return null;
		}
		return "*" + escape(text, DEFAULT_ESCAPE_CHAR) + "*";
	}

	@Override
	public String infix(CharSequence text, char escapeChar) {
		if (text == null) {
			return null;
		}
		return "*" + escape(text, escapeChar) + "*";
	}

	@Override
	public String suffix(CharSequence text) {
		if (text == null) {
			return null;
		}
		return "*" + escape(text, DEFAULT_ESCAPE_CHAR);
	}

	@Override
	public String suffix(CharSequence text, char escapeChar) {
		if (text == null) {
			return null;
		}
		return "*" + escape(text, escapeChar);
	}

	protected String escapeWildcard(CharSequence text, char escapeChar) {
		Pattern pattern = createWildcardReplacementPattern(escapeChar, this.wildcards);
		String replacement = Matcher.quoteReplacement(String.valueOf(escapeChar)) + "$0";
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll(replacement);
	}

	protected Pattern createWildcardReplacementPattern(char escapeChar, char[] wildcards) {
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		for (char wildcard : wildcards) {
			if (escapeChar == '[' || escapeChar == ']') {
				buf.append("\\");
			}
			buf.append(Matcher.quoteReplacement(String.valueOf(escapeChar)));
			if (wildcard == '[' || wildcard == ']') {
				buf.append("\\");
			}
			buf.append(wildcard);
		}
		buf.append("]");
		return Pattern.compile(buf.toString());
	}

	@Override
	public Date roundDownTimePart(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = roundDownCalender(date);
		return new Date(calendar.getTimeInMillis());
	}

	@Override
	public java.sql.Date roundDownTimePart(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = roundDownCalender(date);
		return new java.sql.Date(calendar.getTimeInMillis());
	}

	@Override
	public Timestamp roundDownTimePart(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		Calendar calendar = roundDownCalender(timestamp);
		return new Timestamp(calendar.getTimeInMillis());
	}

	@Override
	public LocalDateTime roundDownTimePart(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return localDateTime.truncatedTo(ChronoUnit.DAYS);
	}

	protected Calendar roundDownCalender(Date utilDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(utilDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	@Override
	public Date roundUpTimePart(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = roundUpCalender(date);
		return new Date(calendar.getTimeInMillis());
	}

	@Override
	public java.sql.Date roundUpTimePart(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = roundUpCalender(date);
		return new java.sql.Date(calendar.getTimeInMillis());
	}

	@Override
	public Timestamp roundUpTimePart(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		Calendar calendar = roundUpCalender(timestamp);
		return new Timestamp(calendar.getTimeInMillis());
	}

	@Override
	public LocalDate roundUpTimePart(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return localDate.plusDays(1);
	}

	@Override
	public LocalDateTime roundUpTimePart(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return localDateTime.plusDays(1).truncatedTo(ChronoUnit.DAYS);
	}

	protected Calendar roundUpCalender(Date utilDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(utilDate);
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	@Override
	public boolean isEmpty(CharSequence text) {
		return text == null || text.length() == 0;
	}

	@Override
	public boolean isNotEmpty(CharSequence text) {
		return !isEmpty(text);
	}

	@Override
	public boolean isBlank(CharSequence text) {
		return text == null || text.length() == 0 || Pattern.matches("\\s*", text);
	}

	@Override
	public boolean isNotBlank(CharSequence text) {
		return !isBlank(text);
	}

}
