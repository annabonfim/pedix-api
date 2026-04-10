package com.pedix.api.controller;

import com.pedix.api.domain.Pedido;
import com.pedix.api.dto.PedidoDTO;
import com.pedix.api.dto.PedidoResponseDTO;
import com.pedix.api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
@Tag(
        name = "Pedido",
        description = """
        Controla os **pedidos** vinculados às comandas do restaurante.  
        Permite **criar pedidos**, **listar por comanda**, **listar todos**, **buscar por ID**, **atualizar pedido** (itens, observação e status) e **deletar**.
        """
)
public class PedidoController {

    private final PedidoService service;
    @Operation(summary = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<List<EntityModel<PedidoResponseDTO>>> listarTodos() {
        List<EntityModel<PedidoResponseDTO>> resposta = service.listarTodos().stream()
                .map(service::toResponse)
                .map(dto -> EntityModel.of(dto,
                        linkTo(methodOn(PedidoController.class).obter(dto.getId())).withSelfRel(),
                        linkTo(methodOn(PedidoController.class).listarTodos()).withRel("todos_pedidos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }
    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PedidoResponseDTO>> obter(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        PedidoResponseDTO dto = service.toResponse(pedido);

        EntityModel<PedidoResponseDTO> model = EntityModel.of(dto,
                linkTo(methodOn(PedidoController.class).obter(id)).withSelfRel(),
                linkTo(methodOn(PedidoController.class).listarTodos()).withRel("todos_pedidos"));

        return ResponseEntity.ok(model);
    }

    @Operation(summary = "Listar pedidos por comanda")
    @GetMapping("/comanda/{comandaId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorComanda(@PathVariable Long comandaId) {
        List<Pedido> pedidos = service.listarPorComanda(comandaId);
        List<PedidoResponseDTO> resposta = pedidos.stream()
                .map(service::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Criar novo pedido vinculado a uma comanda")
    @PostMapping("/comanda/{comandaId}")
    public ResponseEntity<Map<String, Object>> criar(
            @PathVariable Long comandaId,
            @Valid @RequestBody PedidoDTO dto,
            UriComponentsBuilder uri) {

        PedidoResponseDTO resp = service.criarPedido(comandaId, dto);
        URI location = uri.path("/api/pedido/{id}").buildAndExpand(resp.getId()).toUri();

        Map<String, Object> body = Map.of(
                "mensagem", "Pedido criado com sucesso!",
                "pedido", resp,
                "_links", Map.of(
                        "self", linkTo(methodOn(PedidoController.class).obter(resp.getId())).toUri(),
                        "todos_pedidos", linkTo(methodOn(PedidoController.class).listarTodos()).toUri()
                )
        );

        return ResponseEntity.created(location).body(body);
    }

    @Operation(summary = "Atualizar pedido (itens, observação e status)")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PedidoDTO dto) {

        PedidoResponseDTO atualizado = service.atualizarPedido(id, dto);

        Map<String, Object> body = Map.of(
                "mensagem", "Pedido atualizado com sucesso!",
                "pedido", atualizado,
                "_links", Map.of(
                        "self", linkTo(methodOn(PedidoController.class).obter(atualizado.getId())).toUri(),
                        "todos_pedidos", linkTo(methodOn(PedidoController.class).listarTodos()).toUri()
                )
        );

        return ResponseEntity.ok(body);
    }

    @Operation(summary = "Deletar pedido por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        service.deletarPedido(id);

        Map<String, Object> body = Map.of(
                "mensagem", " Pedido removido com sucesso!",
                "status", HttpStatus.OK.value(),
                "timestamp", java.time.LocalDateTime.now()
        );

        return ResponseEntity.ok(body);
    }


}
