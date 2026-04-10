package com.pedix.api.converter;

import com.pedix.api.domain.enums.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter para transformar StatusPedido enum em String minúscula para o banco
 */
@Converter
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, String> {

    @Override
    public String convertToDatabaseColumn(StatusPedido status) {
        if (status == null) {
            return null;
        }
        // Converte para minúsculas
        return status.name().toLowerCase();
    }

    @Override
    public StatusPedido convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        // Converte para maiúsculas para encontrar no enum
        try {
            String upperCase = dbData.toUpperCase();
            // Substitui hífens por underscores para corresponder ao enum
            return StatusPedido.valueOf(upperCase.replace("-", "_"));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

