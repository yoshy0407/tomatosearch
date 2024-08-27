package com.github.tomatosearch.template.expression.parser.token;

import java.util.ArrayList;
import java.util.List;

/**
 * ブロックごとに分割するトークナイザークラスです
 *
 * @author yoshiokahiroshi
 * @since 1.0.0
 */
public class Tokenizer {

	private static final Word AND_WORD = new Word('&');

	private static final Word OR_WORD = new Word('|');

	private final Text text;

	public Tokenizer(String text) {
		this.text = new Text(text);
	}

	public List<Token> getTokens() {
		List<Token> tokens = new ArrayList<>();
		TokenBuilder builder = new TokenBuilder();

		for (int i = 0; i < this.text.length(); i++) {

			Word word = this.text.charAt(i);

			// 空白の場合はトーク化して次へ
			if (word.isWhitespace()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				continue;
			}

			// instance field or instance method
			if (word.isDot()) {
				builder.append(word);
				i++;

				while (this.text.isValidPosition(i) && isMethodOrFieldContinue(builder, text.charAt(i))) {
					Word next = this.text.charAt(i);
					builder.append(next);
					i++;
				}

				if (this.text.isValidPosition(i)) {
					Word last = this.text.charAt(i);
					if (builder.isMethodWithLastWord(last)) {
						builder.append(last); // Add the closing quote
					} else {
						i--;
					}
				}

				if (builder.isMethod()) {
					tokens.add(builder.build(TokenType.INSTANCE_METHOD_IDENTIFIER));
				} else {
					tokens.add(builder.build(TokenType.INSTANCE_FIELD_IDENTIFIER));
				}
				builder.resetWords();
				continue;
			}

			// static field or static method or embeddedFunction
			if (word.isEmbeddedFunctionOrStatic()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				Word atMark = word;
				builder.append(atMark);
				i++;

				// スタートの(があるかをチェっく
				while (this.text.isValidPosition(i) && isMethodOrFieldContinue(builder, text.charAt(i))) {
					Word next = this.text.charAt(i);
					builder.append(next);
					i++;
				}

				if (this.text.isValidPosition(i)) {
					Word last = this.text.charAt(i);
					if (builder.isMethodWithLastWord(last)) {
						builder.append(last); // Add the closing quote
					} else {
						i--;
					}
				}
				if (builder.isStatic()) {
					if (builder.isMethod()) {
						tokens.add(builder.build(TokenType.STATIC_METHOD_IDENTIFIER));
					} else {
						tokens.add(builder.build(TokenType.STATIC_FIELD_IDENTIFIER));
					}
				} else {
					tokens.add(builder.build(TokenType.EMBEDED_FUNCTION_IDENTIFIER));
				}
				builder.resetWords();
				continue;
			}

			// Handle string literals
			if (word.isStringLiteal()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				Word quote = word;
				builder.append(quote);
				i++;
				while (this.text.isValidPosition(i) && this.text.charAt(i).notEquals(quote)) {
					builder.append(this.text.charAt(i));
					i++;
				}
				if (this.text.isValidPosition(i)) {
					builder.append(quote); // Add the closing quote
				}
				tokens.add(builder.build(TokenType.LITERAL));
				builder.resetWords();
				continue;
			}

			// Handle number literals
			if (word.isDigitLiteal()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				Word quote = word;
				builder.append(quote);
				i++;
				while (this.text.isValidPosition(i) && this.text.charAt(i).isDigitLiteal()) {
					builder.append(this.text.charAt(i));
					i++;
				}
				--i; // Adjust index since the loop overshoots
				tokens.add(builder.build(TokenType.LITERAL));
				builder.resetWords();
				continue;
			}

			// Handle Comparison Operators
			if (word.isComparisonOperator()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				Word next = this.text.isValidPosition(i + 1) ? this.text.charAt(i + 1) : new Word('\0');
				if (next.equals(new Word('='))) {
					tokens.add(new Token(TokenType.COMPARISON_OPERATOR, "" + word + next));
					i++;
					// Skip the next character
				} else if (word.equals(new Word('!'))) {
					tokens.add(new Token(TokenType.LOGICAL_OPERATOR, "" + word));
				} else {
					tokens.add(new Token(TokenType.COMPARISON_OPERATOR, "" + word));
				}
				continue;
			}

			// Handle Logical Operators
			if (word.isLogicalOperator()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				Word next = this.text.isValidPosition(i + 1) ? this.text.charAt(i + 1) : new Word('\0');
				if (isConjunctionOperator(word, next) || isDisjunctiveOperator(word, next)) {
					tokens.add(new Token(TokenType.LOGICAL_OPERATOR, "" + word + next));
					i++; // Skip the nextcharacter
				} else {
					tokens.add(new Token(TokenType.LOGICAL_OPERATOR, "" + word));
				}
				continue;
			}

			// Handle arithmetic operators
			if (word.isArithmeticOperator()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				tokens.add(new Token(TokenType.ARITHMETIC_OPERATOR, "" + word));
				continue;
			}

			// Handle delimiters
			if (word.isDelimiter()) {
				if (builder.hasWord()) {
					tokens.add(buildIdentifierAndReset(builder));
				}
				tokens.add(new Token(TokenType.DELIMITER, "" + word));
				continue;
			}

			// Default: append to buffer
			builder.append(word);
		}

		if (builder.hasWord()) {
			tokens.add(buildIdentifierAndReset(builder));
		}

		return tokens;
	}

	private boolean isConjunctionOperator(Word first, Word next) {
		return first.equals(AND_WORD) && next.equals(AND_WORD);
	}

	private boolean isDisjunctiveOperator(Word first, Word next) {
		return first.equals(OR_WORD) && next.equals(OR_WORD);
	}

	private Token buildIdentifierAndReset(TokenBuilder builder) {
		Token token = builder.build(TokenType.IDENTIFIER);
		builder.resetWords();
		return token;
	}

	private boolean isMethodOrFieldContinue(TokenBuilder builder, Word next) {
		if (builder.hasWordOf("(")) {
			return !next.isEndParentheses();
		} else {
			return !next.isWhitespace() && !next.isEndParentheses();
		}
	}
}
