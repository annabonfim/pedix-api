package com.pedix.api.service;

import com.pedix.api.domain.ItemCardapio;
import com.pedix.api.domain.enums.CategoriaItem;
import com.pedix.api.dto.ItemCardapioDTO;
import com.pedix.api.repository.ItemCardapioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de serviço responsável pela regra de negócio dos Itens do Cardápio.
 * Controla criação, atualização, busca e exclusão com foco em integridade e boas práticas REST.
 */
@Service
@RequiredArgsConstructor
public class ItemCardapioService {

    private final ItemCardapioRepository repository;

    /**
     * Lista todos os itens disponíveis no cardápio.
     */
    public List<ItemCardapio> listarDisponiveis() {
        return repository.findByDisponivelTrue();
    }

    /**
     * Lista itens disponíveis com paginação.
     */
    public Page<ItemCardapio> listarDisponiveis(Pageable pageable) {
        return repository.findByDisponivelTrue(pageable);
    }

    /**
     * Lista itens filtrados por categoria.
     */
    public List<ItemCardapio> listarPorCategoria(CategoriaItem categoria) {
        return repository.findByCategoriaAndDisponivelTrue(categoria);
    }

    /**
     * Busca item do cardápio por ID.
     */
    public ItemCardapio buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item do cardápio não encontrado: " + id));
    }

    /**
     * Cria um novo item no cardápio.
     */
    @Transactional
    public ItemCardapio criar(ItemCardapioDTO dto) {
        ItemCardapio item = converterDtoParaEntidade(dto);
        return repository.save(item);
    }

    /**
     * Atualiza um item existente no cardápio.
     */
    @Transactional
    public ItemCardapio atualizar(Long id, ItemCardapioDTO dto) {
        ItemCardapio itemExistente = buscarPorId(id);

        itemExistente.setNome(dto.getNome());
        itemExistente.setDescricao(dto.getDescricao());
        itemExistente.setCategoria(dto.getCategoria());
        itemExistente.setPreco(dto.getPreco());
        itemExistente.setDisponivel(dto.getDisponivel());
        itemExistente.setImagemUrl(dto.getImagemUrl());

        return repository.save(itemExistente);
    }

    /**
     * Remove um item do cardápio.
     */
    @Transactional
    public void deletar(Long id) {
        ItemCardapio item = buscarPorId(id);
        repository.delete(item);
    }

    // ===================== MÉTODOS AUXILIARES ===================== //

    /**
     * Converte um DTO em uma entidade de ItemCardapio.
     */
    private ItemCardapio converterDtoParaEntidade(ItemCardapioDTO dto) {
        ItemCardapio item = new ItemCardapio();
        item.setNome(dto.getNome());
        item.setDescricao(dto.getDescricao());
        item.setCategoria(dto.getCategoria());
        item.setPreco(dto.getPreco());
        item.setDisponivel(dto.getDisponivel() != null ? dto.getDisponivel() : Boolean.TRUE);
        item.setImagemUrl(dto.getImagemUrl());
        return item;
    }
}
