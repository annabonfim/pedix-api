package com.pedix.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

/**
 * Representa a associação entre um Pedido e um Item do Cardápio,
 * contendo a quantidade e valores calculados.
 */
@Entity
@Table(name = "PEDIDO_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PedidoItem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_ITEM")
    private Long id;

    /** Pedido ao qual este item pertence */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO_ID", nullable = false)
    @JsonBackReference
    private Pedido pedido;

    /** Item do cardápio associado */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_CARDAPIO_ID", nullable = false)
    @NotNull(message = "O item do cardápio é obrigatório.")
    private ItemCardapio itemCardapio;

    /** Quantidade solicitada */
    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    /** Valor unitário no momento do pedido */
    @Column(name = "PRECO_UNITARIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario = BigDecimal.ZERO;

    /** Valor total (preço unitário * quantidade) */
    @Column(name = "SUBTOTAL", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    // ====================== MÉTODOS DE DOMÍNIO ====================== //

    /**
     * Define o preço unitário e o subtotal com base no preço atual do item de cardápio.
     */
    public void definirPrecoPadrao() {
        if (itemCardapio != null && itemCardapio.getPreco() != null) {
            this.precoUnitario = itemCardapio.getPreco();
            this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }

    /**
     * Recalcula o subtotal com base na quantidade e preço unitário atual.
     * É útil para manter a integridade quando a quantidade muda.
     */
    public void recalcularSubtotal() {
        if (precoUnitario != null && quantidade != null) {
            this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }

    @Override
    public String toString() {
        return String.format("PedidoItem{id=%d, item='%s', qtd=%d, preco=%.2f, subtotal=%.2f}",
                id,
                itemCardapio != null ? itemCardapio.getNome() : "N/A",
                quantidade,
                precoUnitario,
                subtotal);
    }
}
