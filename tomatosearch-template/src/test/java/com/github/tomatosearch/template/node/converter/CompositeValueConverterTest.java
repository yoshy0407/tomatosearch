package com.github.tomatosearch.template.node.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.tomatosearch.template.exception.ConversationException;

class CompositeValueConverterTest {

	CompositeValueConverter sut = new CompositeValueConverter("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.SSS", "HH:mm:ss.SSS",
			"yyyy-MM-dd HH:mm:ss.SSS Z VV");

	@Test
	void testSerializeBigDecimal() throws ConversationException {
		assertThat(sut.serialize(new BigDecimal("1000"), Map.of())).isEqualTo("1000");
	}

	@Test
	void testSerializeBigInteger() throws ConversationException {
		assertThat(sut.serialize(new BigInteger("1000"), Map.of())).isEqualTo("1000");
	}

	@Test
	void testSerializeBoolean() throws ConversationException {
		assertThat(sut.serialize(true, Map.of())).isEqualTo("\"true\"");
	}

	@Test
	void testSerializeByteArray() throws ConversationException {
		assertThat(sut.serialize("Test".getBytes(), Map.of())).isEqualTo("\"VGVzdA==\"");
	}

	@Test
	void testSerializeByte() throws ConversationException {
		assertThat(sut.serialize((byte) 1, Map.of())).isEqualTo("1");
	}

	@Test
	void testSerializeDouble() throws ConversationException {
		assertThat(sut.serialize(12.3d, Map.of())).isEqualTo("12.3");
	}

	@Test
	void testSerializeFloat() throws ConversationException {
		assertThat(sut.serialize(12.45f, Map.of())).isEqualTo("12.45");
	}

	@Test
	void testSerializeInteger() throws ConversationException {
		assertThat(sut.serialize(1000, Map.of())).isEqualTo("1000");
	}

	@Test
	void testSerializeLong() throws ConversationException {
		assertThat(sut.serialize(1000L, Map.of())).isEqualTo("1000");
	}

	@Test
	void testSerializeString() throws ConversationException {
		assertThat(sut.serialize("message", Map.of())).isEqualTo("\"message\"");
	}

	@Test
	void testSerializeLocalDate() throws ConversationException {
		assertThat(sut.serialize(LocalDate.of(2024, 5, 4), Map.of())).isEqualTo("\"2024-05-04\"");
	}

	@Test
	void testSerializeLocalDateTime() throws ConversationException {
		assertThat(sut.serialize(LocalDateTime.of(2024, 5, 4, 11, 34, 53), Map.of()))
				.isEqualTo("\"2024-05-04 11:34:53.000\"");
	}

	@Test
	void testSerializeLocalTime() throws ConversationException {
		assertThat(sut.serialize(LocalTime.of(11, 34, 43), Map.of())).isEqualTo("\"11:34:43.000\"");
	}

	@Test
	void testSerializeZonedDateTime() throws ConversationException {
		assertThat(sut.serialize(
				ZonedDateTime.of(LocalDate.of(2024, 5, 4), LocalTime.of(11, 11, 11), ZoneId.of("Asia/Tokyo")),
				Map.of())).isEqualTo("\"2024-05-04 11:11:11.000 +0900 Asia/Tokyo\"");
	}

	@Test
	void testDeserializeBigDecimal() throws ConversationException {
		assertThat(sut.deserialize(BigDecimal.class, "1.123", Map.of())).isEqualTo(new BigDecimal("1.123"));
	}

	@Test
	void testDeserializeBigInteger() throws ConversationException {
		assertThat(sut.deserialize(BigInteger.class, "100", Map.of())).isEqualTo(new BigInteger("100"));
	}

	@Test
	void testDeserializeBoolean() throws ConversationException {
		assertThat(sut.deserialize(Boolean.class, "\"true\"", Map.of())).isEqualTo(Boolean.TRUE);
	}

	@Test
	void testDeserializeByteArray() throws ConversationException {
		assertThat(sut.deserialize(Byte[].class, "\"VGVzdA==\"", Map.of())).isEqualTo("Test".getBytes());
	}

	@Test
	void testDeserializeByte() throws ConversationException {
		assertThat(sut.deserialize(Byte.class, "1", Map.of())).isEqualTo(Byte.decode("1"));
	}

	@Test
	void testDeserializeDouble() throws ConversationException {
		assertThat(sut.deserialize(Double.class, "12.3", Map.of())).isEqualTo(Double.valueOf("12.3"));
	}

	@Test
	void testDeserializeFloat() throws ConversationException {
		assertThat(sut.deserialize(Float.class, "12.45", Map.of())).isEqualTo(Float.valueOf("12.45"));
	}

	@Test
	void testDeserializeInteger() throws ConversationException {
		assertThat(sut.deserialize(Integer.class, "1000", Map.of())).isEqualTo(Integer.valueOf("1000"));
	}

	@Test
	void testDeserializeLong() throws ConversationException {
		assertThat(sut.deserialize(Long.class, "1000", Map.of())).isEqualTo(Long.valueOf("1000"));
	}

	@Test
	void testDeserializeString() throws ConversationException {
		assertThat(sut.deserialize(String.class, "\"message\"", Map.of())).isEqualTo("message");
	}

	@Test
	void testDeserializeLocalDate() throws ConversationException {
		assertThat(sut.deserialize(LocalDate.class, "\"2024-05-04\"", Map.of())).isEqualTo(LocalDate.of(2024, 5, 4));
	}

	@Test
	void testDeserializeLocalDateTime() throws ConversationException {
		assertThat(sut.deserialize(LocalDateTime.class, "\"2024-05-04 11:34:53.000\"", Map.of()))
				.isEqualTo(LocalDateTime.of(2024, 5, 4, 11, 34, 53));
	}

	@Test
	void testDeserializeLocalTime() throws ConversationException {
		assertThat(sut.deserialize(LocalTime.class, "\"11:34:43.000\"", Map.of())).isEqualTo(LocalTime.of(11, 34, 43));
	}

	@Test
	void testDeserializeZonedDateTime() throws ConversationException {
		assertThat(sut.deserialize(ZonedDateTime.class, "\"2024-05-04 11:11:11.000 +0900 Asia/Tokyo\"", Map.of()))
				.isEqualTo(
						ZonedDateTime.of(LocalDate.of(2024, 5, 4), LocalTime.of(11, 11, 11), ZoneId.of("Asia/Tokyo")));
	}

}
