package com.pedix.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoItemRequestDTO {
    @NotNull(message = "O ID do pedido é obrigatório.")
    private Long pedidoId;
    
    @NotNull(message = "O ID do item do cardápio é obrigatório.")
    private Long itemCardapioId;
    
    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade mínima é 1.")
    private Integer quantidade;
    
    @NotNull(message = "O preço unitário é obrigatório.")
    @Min(value = 0, message = "O preço unitário deve ser maior ou igual a zero.")
    private BigDecimal precoUnitario;
}

