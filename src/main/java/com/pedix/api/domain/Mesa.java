package com.pedix.api.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma mesa do restaurante.
 * Entidade persistida na tabela MESA.
 */
@Entity
@Table(name = "MESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mesa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MESA")
    private Long id;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "QR_CODE", length = 100)
    private String qrCode;

    @Override
    public String toString() {
        return String.format("Mesa[id=%d, numero=%d, status='%s']", id, numero, status);
    }
}
