package com.pedix.api.service;

import com.pedix.api.domain.Mesa;
import com.pedix.api.repository.MesaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;

    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    public Mesa buscarPorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada com id: " + id));
    }

    public Mesa buscarPorNumero(Integer numero) {
        return mesaRepository.findByNumero(numero)
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada com número: " + numero));
    }

    public List<Mesa> listarPorStatus(String status) {
        return mesaRepository.findByStatus(status);
    }
}
