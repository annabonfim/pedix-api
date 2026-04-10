package com.pedix.api.converter;

import com.pedix.api.domain.enums.CategoriaItem;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter para transformar CategoriaItem enum em String minúscula para o banco
 */
@Converter
public class CategoriaItemConverter implements AttributeConverter<CategoriaItem, String> {

    @Override
    public String convertToDatabaseColumn(CategoriaItem categoria) {
        if (categoria == null) {
            return null;
        }
        return categoria.name().toLowerCase();
    }

    @Override
    public CategoriaItem convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        // Converte para maiúsculas para encontrar no enum
        try {
            return CategoriaItem.valueOf(dbData.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

