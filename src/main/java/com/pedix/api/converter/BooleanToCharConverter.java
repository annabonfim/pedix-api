package com.pedix.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter para transformar Boolean em CHAR(1) 'S'/'N' no Oracle
 */
@Converter
public class BooleanToCharConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return "N";
        }
        return attribute ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return false;
        }
        return "S".equalsIgnoreCase(dbData) || "1".equals(dbData);
    }
}

