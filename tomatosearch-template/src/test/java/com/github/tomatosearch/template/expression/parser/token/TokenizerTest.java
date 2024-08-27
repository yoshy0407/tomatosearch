package com.github.tomatosearch.template.expression.parser.token;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class TokenizerTest {

	@Test
	void testComparisonOperator() {
		String text = "(test == \"test\") != (sample < 10)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(11);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.IDENTIFIER, "test"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "=="));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.LITERAL, "\"test\""));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "!="));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.IDENTIFIER, "sample"));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "<"));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
	}

	@Test
	void testComparisonOperator2() {
		String text = "(test > \"test\") <= (sample >= 10)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(11);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.IDENTIFIER, "test"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, ">"));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.LITERAL, "\"test\""));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "<="));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.IDENTIFIER, "sample"));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, ">="));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
	}

	@Test
	void testLogicalOperator() {
		String text = "(test && \"test\") || !(sample >= 10)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(12);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.IDENTIFIER, "test"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "&&"));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.LITERAL, "\"test\""));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "||"));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "!"));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.IDENTIFIER, "sample"));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, ">="));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(11)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
	}

	@Test
	void testCaliculateOperator() {
		String text = "10 + 10 - 10 * 10 / 10 % 10";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(11);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.ARITHMETIC_OPERATOR, "+"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.ARITHMETIC_OPERATOR, "-"));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.ARITHMETIC_OPERATOR, "*"));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.ARITHMETIC_OPERATOR, "/"));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.ARITHMETIC_OPERATOR, "%"));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.LITERAL, "10"));
	}

	// fix
	@Test
	void testInstanceMethod() {
		String text = "instance.length()";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(1);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.INSTANCE_METHOD_IDENTIFIER, "instance.length()"));
	}

	@Test
	void testInstanceField() {
		String text = "instance.field";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(1);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.INSTANCE_FIELD_IDENTIFIER, "instance.field"));
	}

	@Test
	void testStaticMethod() {
		String text = "@java.util.regex.Pattern@matches(\"^[a-z]*$\", employeeName)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(1);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.STATIC_METHOD_IDENTIFIER,
				"@java.util.regex.Pattern@matches(\"^[a-z]*$\", employeeName)"));
	}

	@Test
	void testStaticField() {
		String text = "@java.lang.Byte@MAX_VALUE";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(1);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.STATIC_FIELD_IDENTIFIER, "@java.lang.Byte@MAX_VALUE"));
	}

	@Test
	void testEmbeddedFunction() {
		String text = "@prefix(employee.employeeName)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(1);
		assertThat(tokens.get(0))
				.isEqualTo(new Token(TokenType.EMBEDED_FUNCTION_IDENTIFIER, "@prefix(employee.employeeName)"));
	}

	@Test
	void testMixed() {
		String text = "(test.length() > 10) && (@length(sample) <= @java.lang.Long@MAX_VALUE) && @java.util.Values@isTrue(test)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(13);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.INSTANCE_METHOD_IDENTIFIER, "test.length()"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, ">"));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "&&"));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.EMBEDED_FUNCTION_IDENTIFIER, "@length(sample)"));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "<="));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.STATIC_FIELD_IDENTIFIER, "@java.lang.Long@MAX_VALUE"));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(11)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "&&"));
		assertThat(tokens.get(12))
				.isEqualTo(new Token(TokenType.STATIC_METHOD_IDENTIFIER, "@java.util.Values@isTrue(test)"));
	}

	@Test
	void testMixed2() {
		String text = "(10 > test.length()) && (@length(sample) <= sample.field)";

		Tokenizer tokenizer = new Tokenizer(text);

		List<Token> tokens = tokenizer.getTokens();

		assertThat(tokens).hasSize(11);
		assertThat(tokens.get(0)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(1)).isEqualTo(new Token(TokenType.LITERAL, "10"));
		assertThat(tokens.get(2)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, ">"));
		assertThat(tokens.get(3)).isEqualTo(new Token(TokenType.INSTANCE_METHOD_IDENTIFIER, "test.length()"));
		assertThat(tokens.get(4)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
		assertThat(tokens.get(5)).isEqualTo(new Token(TokenType.LOGICAL_OPERATOR, "&&"));
		assertThat(tokens.get(6)).isEqualTo(new Token(TokenType.DELIMITER, "("));
		assertThat(tokens.get(7)).isEqualTo(new Token(TokenType.EMBEDED_FUNCTION_IDENTIFIER, "@length(sample)"));
		assertThat(tokens.get(8)).isEqualTo(new Token(TokenType.COMPARISON_OPERATOR, "<="));
		assertThat(tokens.get(9)).isEqualTo(new Token(TokenType.INSTANCE_FIELD_IDENTIFIER, "sample.field"));
		assertThat(tokens.get(10)).isEqualTo(new Token(TokenType.DELIMITER, ")"));
	}

}
