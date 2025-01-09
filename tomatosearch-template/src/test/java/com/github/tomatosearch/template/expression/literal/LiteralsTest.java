package com.github.tomatosearch.template.expression.literal;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.EvaluationException;
import com.github.tomatosearch.template.expression.Expression;

class LiteralsTest {

	@Test
	void testResolveNull() throws EvaluationException {
		Expression value = Literals.resolve("null");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isNull();
	}

	@Test
	void testResolveTrue() throws EvaluationException {
		Expression value = Literals.resolve("true");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(true);
	}

	@Test
	void testResolveFalse() throws EvaluationException {
		Expression value = Literals.resolve("false");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(false);
	}

	@Test
	void testResolveInt() throws EvaluationException {
		Expression value = Literals.resolve("10");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(10);
	}

	@Test
	void testResolveLong() throws EvaluationException {
		Expression value = Literals.resolve("100L");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(100L);
	}

	@Test
	void testResolveFloat() throws EvaluationException {
		Expression value = Literals.resolve("0.1F");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(0.1F);
	}

	@Test
	void testResolveDouble() throws EvaluationException {
		Expression value = Literals.resolve("0.00004D");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(0.00004D);
	}

	@Test
	void testResolveBigDecimal() throws EvaluationException {
		Expression value = Literals.resolve("0.123B");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo(new BigDecimal("0.123"));
	}

	@Test
	void testResolveChar() throws EvaluationException {
		Expression value = Literals.resolve("'a'");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo('a');
	}

	@Test
	void testResolveString() throws EvaluationException {
		Expression value = Literals.resolve("\"test\"");
		assertThat(value.evaluate(Collections.emptyMap()).getValue()).isEqualTo("test");
	}

}
