package org.campus.core.converter.json;

import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ExtObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public ExtObjectMapper() {
        BaseSettings baseSettings = new BaseSettings(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR,
                STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, null, null, Locale.CHINA,
                TimeZone.getDefault(), Base64Variants.getDefaultVariant());
        _serializationConfig = new SerializationConfig(baseSettings, _subtypeResolver, _mixInAnnotations);
        _deserializationConfig = new DeserializationConfig(baseSettings, _subtypeResolver, _mixInAnnotations);
    }
}
