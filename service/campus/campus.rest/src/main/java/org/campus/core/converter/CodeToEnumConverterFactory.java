package org.campus.core.converter;

import org.campus.core.type.EnumCodeGetter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class CodeToEnumConverterFactory implements ConverterFactory<String, Enum>, ConditionalConverter {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		Class<?> enumType = targetType;
		while(enumType != null && !enumType.isEnum()) {
			enumType = enumType.getSuperclass();
		}
		
		Assert.notNull(enumType, "The target type " + targetType.getName()
				+ " does not refer to an enum");
		
		return new CodeToEnum(enumType);
	}

	private class CodeToEnum<T extends Enum & EnumCodeGetter> implements Converter<String, T> {

		private final T[] enums;

		public CodeToEnum(Class<T> enumType) {
			this.enums = enumType.getEnumConstants();
		}

		@Override
		public T convert(String source) {
	        for (T em : enums) {
	            if (em.getCode().equals(source)) {
	                return em;
	            }
	        }
	        return null;
		}
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return EnumCodeGetter.class.isAssignableFrom(targetType.getType());
	}
}
