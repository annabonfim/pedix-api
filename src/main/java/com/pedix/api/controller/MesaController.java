package com.pedix.api.controller;

import com.pedix.api.domain.Mesa;
import com.pedix.api.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
@Tag(name = "Mesas", description = "Gerenciamento de mesas do restaurante")
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    @Operation(summary = "Listar todas as mesas")
    public ResponseEntity<List<Mesa>> listarTodas(@RequestParam(required = false) String status) {
        if (status != null && !status.isBlank()) {
            return ResponseEntity.ok(mesaService.listarPorStatus(status));
        }
        return ResponseEntity.ok(mesaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar mesa por ID")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.buscarPorId(id));
    }

    @GetMapping("/numero/{numero}")
    @Operation(summary = "Buscar mesa por número")
    public ResponseEntity<Mesa> buscarPorNumero(@PathVariable Integer numero) {
        return ResponseEntity.ok(mesaService.buscarPorNumero(numero));
    }
}
