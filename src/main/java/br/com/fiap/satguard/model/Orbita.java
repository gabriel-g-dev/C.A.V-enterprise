package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORBITAS")
public class Orbita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orbita_id")
    private Integer orbitaId;

    @Column(name = "orbita_altitude_km", precision = 10, scale = 2)
    private BigDecimal orbitaAltitudeKm;

    @Column(name = "orbita_categoria", length = 3)
    private String orbitaCategoria;

    @Column(name = "orbita_inclinacao", precision = 5, scale = 2)
    private BigDecimal orbitaInclinacao;
}
