package com.github.tomatosearch.template.node.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.tomatosearch.template.ErrorCode;
import com.github.tomatosearch.template.exception.ConversationException;

public class CompositeValueConverter {

	public static final String DATE_FORMAT = "dateFormat";

	private final Map<Class<?>, ValueConverter<? extends Object>> standardConverters;

	private final Map<Class<?>, ValueConverter<? extends Object>> customConverters = new LinkedHashMap<>();

	public CompositeValueConverter(String localDateFormat, String localDateTimeFormat, String localTimeFormat,
			String zonedDateTimeFormat) {
		//@formatter:off
		standardConverters = Map.ofEntries(
				Map.entry(byte[].class, new ByteArrayValueConverter()),
				Map.entry(Byte[].class, new ByteArrayValueConverter()),
				Map.entry(Boolean.class, new BooleanValueConverter()),
				Map.entry(String.class, new StringValueConverter()),
				Map.entry(BigDecimal.class, new BigDecimalValueConverter()),
				Map.entry(BigInteger.class, new BigIntegerValueConverter()),
				Map.entry(Byte.class, new ByteValueConverter()),
				Map.entry(Double.class, new DoubleValueConverter()),
				Map.entry(Float.class, new FloatValueConverter()),
				Map.entry(Integer.class, new IntegerValueConverter()),
				Map.entry(Long.class, new LongValueConverter()),
				Map.entry(Short.class, new ShortValueConverter()),
				Map.entry(LocalDate.class, new LocalDateValueConverter(localDateFormat)),
				Map.entry(LocalDateTime.class, new LocalDateTimeValueConverter(localDateTimeFormat)),
				Map.entry(LocalTime.class, new LocalTimeValueConverter(localTimeFormat)),
				Map.entry(ZonedDateTime.class, new ZonedDateTimeValueConverter(zonedDateTimeFormat)));
		//@formatter:on
	}

	public String serialize(Object value, Map<String, Object> option) throws ConversationException {
		for (Entry<Class<?>, ValueConverter<?>> entry : customConverters.entrySet()) {
			ValueConverter<?> converter = entry.getValue();
			if (converter.isTarget(value)) {
				return converter.serialize(value, option);
			}
		}
		for (Entry<Class<?>, ValueConverter<?>> entry : standardConverters.entrySet()) {
			ValueConverter<?> converter = entry.getValue();
			if (converter.isTarget(value)) {
				return converter.serialize(value, option);
			}
		}

		throw new ConversationException(ErrorCode.CONVERTER_NOT_FOUND.message(value, value.getClass()));
	}

	public Object deserialize(Class<?> targetClass, String value, Map<String, Object> option)
			throws ConversationException {
		ValueConverter<?> valueConverter = this.customConverters.get(targetClass);
		if (valueConverter != null) {
			return valueConverter.deserialize(value, option);
		}

		valueConverter = this.standardConverters.get(targetClass);
		if (valueConverter == null) {
			throw new ConversationException(
					ErrorCode.CONVERTER_NOT_FOUND.message(value, value.getClass(), targetClass));
		}
		return valueConverter.deserialize(value, option);
	}

	public <T> void addCustomConverter(Class<T> clazz, ValueConverter<T> converter) {
		this.customConverters.put(clazz, converter);
	}

}
