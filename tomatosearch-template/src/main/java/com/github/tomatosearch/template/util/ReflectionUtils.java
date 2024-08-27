package com.github.tomatosearch.template.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.tomatosearch.template.exception.ReflectionException;

public class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static Field getField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

	public static Field getDeclaredField(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

	public static Object getFieldValue(Field field, Object instance) {
		try {
			return field.get(instance);
		} catch (IllegalAccessException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

	public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] argTypes) {
		try {
			return clazz.getMethod(methodName, argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

	public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>[] argTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ReflectionException(e);
		}
	}

	public static Object invokeMethod(Method method, Object instance, Object[] args) {
		try {
			return method.invoke(instance, args);
		} catch (InvocationTargetException | IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	public static Class<?> resolveClass(String className, ClassLoader classLoader) {
		try {
			return classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new ReflectionException(e);
		}
	}

}
