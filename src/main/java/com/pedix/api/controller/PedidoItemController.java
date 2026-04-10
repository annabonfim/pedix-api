package com.pedix.api.controller;

import com.pedix.api.domain.PedidoItem;
import com.pedix.api.dto.PedidoItemRequestDTO;
import com.pedix.api.service.PedidoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedido-item")
@RequiredArgsConstructor
@Tag(
        name = "Item de Pedido",
        description = """
        Controla os **itens individuais de pedidos**.  
        Permite **criar**, **atualizar** e **remover** itens de pedidos.
        """
)
public class PedidoItemController {

    private final PedidoItemService service;

    @Operation(summary = "Listar todos os itens de pedido")
    @GetMapping
    public ResponseEntity<List<PedidoItem>> listarTodos() {
        List<PedidoItem> itens = service.listarTodos();
        return ResponseEntity.ok(itens);
    }

    @Operation(summary = "Buscar item de pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> buscarPorId(@PathVariable Long id) {
        PedidoItem item = service.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Criar novo item de pedido")
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(
            @Valid @RequestBody PedidoItemRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        PedidoItem salvo = service.criar(dto);
        URI location = uriBuilder.path("/api/pedido-item/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        Map<String, Object> body = Map.of(
                "mensagem", "Item de pedido criado com sucesso!",
                "item", salvo
        );

        return ResponseEntity.created(location).body(body);
    }

    @Operation(summary = "Atualizar item de pedido")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PedidoItemRequestDTO dto) {

        PedidoItem atualizado = service.atualizar(id, dto);

        Map<String, Object> body = Map.of(
                "mensagem", "Item de pedido atualizado com sucesso!",
                "item", atualizado
        );

        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Deletar item de pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        service.deletar(id);

        Map<String, Object> body = Map.of(
                "mensagem", "Item de pedido removido com sucesso!",
                "status", HttpStatus.OK.value(),
                "timestamp", java.time.LocalDateTime.now()
        );

        return ResponseEntity.ok(body);
    }
}

