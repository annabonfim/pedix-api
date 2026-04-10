package com.pedix.api.service;

import com.pedix.api.domain.ItemCardapio;
import com.pedix.api.domain.Pedido;
import com.pedix.api.domain.PedidoItem;
import com.pedix.api.dto.PedidoItemRequestDTO;
import com.pedix.api.repository.PedidoItemRepository;
import com.pedix.api.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de serviço responsável pela regra de negócio dos Itens de Pedido.
 */
@Service
@RequiredArgsConstructor
public class PedidoItemService {

    private final PedidoItemRepository pedidoItemRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemCardapioService itemCardapioService;

    /**
     * Lista todos os itens de pedido cadastrados.
     */
    public List<PedidoItem> listarTodos() {
        return pedidoItemRepository.findAll();
    }

    /**
     * Busca um item de pedido por ID, lançando exceção se não encontrado.
     */
    public PedidoItem buscarPorId(Long id) {
        return pedidoItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item de pedido não encontrado: " + id));
    }

    /**
     * Cria um novo item de pedido.
     */
    @Transactional
    public PedidoItem criar(PedidoItemRequestDTO dto) {
        // Valida existência do pedido
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado: " + dto.getPedidoId()));
        
        // Valida existência do item de cardápio
        ItemCardapio itemCardapio = itemCardapioService.buscarPorId(dto.getItemCardapioId());
        
        if (!Boolean.TRUE.equals(itemCardapio.getDisponivel())) {
            throw new IllegalArgumentException("Item indisponível: " + itemCardapio.getNome());
        }
        
        // Valida quantidade e preço
        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade inválida: " + dto.getQuantidade());
        }
        if (dto.getPrecoUnitario() == null || dto.getPrecoUnitario().signum() < 0) {
            throw new IllegalArgumentException("Preço unitário inválido: " + dto.getPrecoUnitario());
        }
        
        // Calcula subtotal
        java.math.BigDecimal subtotal = dto.getPrecoUnitario().multiply(
            java.math.BigDecimal.valueOf(dto.getQuantidade()));
        
        PedidoItem pedidoItem = PedidoItem.builder()
                .pedido(pedido)
                .itemCardapio(itemCardapio)
                .quantidade(dto.getQuantidade())
                .precoUnitario(dto.getPrecoUnitario())
                .subtotal(subtotal)
                .build();
        
        return pedidoItemRepository.save(pedidoItem);
    }

    /**
     * Atualiza um item de pedido existente.
     */
    @Transactional
    public PedidoItem atualizar(Long id, PedidoItemRequestDTO dto) {
        PedidoItem pedidoItem = buscarPorId(id);
        
        // Valida quantidade e preço
        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade inválida: " + dto.getQuantidade());
        }
        if (dto.getPrecoUnitario() == null || dto.getPrecoUnitario().signum() < 0) {
            throw new IllegalArgumentException("Preço unitário inválido: " + dto.getPrecoUnitario());
        }
        
        // Calcula subtotal
        java.math.BigDecimal subtotal = dto.getPrecoUnitario().multiply(
            java.math.BigDecimal.valueOf(dto.getQuantidade()));
        
        pedidoItem.setQuantidade(dto.getQuantidade());
        pedidoItem.setPrecoUnitario(dto.getPrecoUnitario());
        pedidoItem.setSubtotal(subtotal);
        
        return pedidoItemRepository.save(pedidoItem);
    }

    /**
     * Remove um item de pedido.
     */
    @Transactional
    public void deletar(Long id) {
        PedidoItem pedidoItem = buscarPorId(id);
        pedidoItemRepository.delete(pedidoItem);
    }
}

