package com.github.tomatosearch.template;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ExpressionFunction {

	// いらないのかも
	String escape(CharSequence text, char escapeChar);

	// いらないのかも
	String prefix(CharSequence text);

	// いらないのかも
	String prefix(CharSequence text, char escapeChar);

	// いらないのかも
	String infix(CharSequence text);

	// いらないのかも
	String infix(CharSequence text, char escapeChar);

	// いらないのかも
	String suffix(CharSequence text);

	// いらないのかも
	String suffix(CharSequence text, char escapeChar);

	java.util.Date roundDownTimePart(java.util.Date date);

	Date roundDownTimePart(Date date);

	Timestamp roundDownTimePart(Timestamp timestamp);

	LocalDateTime roundDownTimePart(LocalDateTime localDateTime);

	java.util.Date roundUpTimePart(java.util.Date date);

	Date roundUpTimePart(Date date);

	Timestamp roundUpTimePart(Timestamp timestamp);

	LocalDate roundUpTimePart(LocalDate localDate);

	LocalDateTime roundUpTimePart(LocalDateTime localDateTime);

	boolean isEmpty(CharSequence text);

	boolean isNotEmpty(CharSequence text);

	boolean isBlank(CharSequence text);

	boolean isNotBlank(CharSequence text);

}
