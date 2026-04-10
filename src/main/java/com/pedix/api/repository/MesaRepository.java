package com.pedix.api.repository;

import com.pedix.api.domain.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    List<Mesa> findByStatus(String status);

    Optional<Mesa> findByNumero(Integer numero);
}
