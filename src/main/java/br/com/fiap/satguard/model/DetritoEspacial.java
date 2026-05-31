package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "DETRITO_ESPACIAL")
@PrimaryKeyJoinColumn(name = "objeto_id")
public class DetritoEspacial extends ObjetoEspacial {

    @Column(name = "detrito_tamanho", precision = 4, scale = 1)
    private BigDecimal detritoTamanho;

    @Column(name = "detrito_risco_colisao", precision = 5, scale = 2)
    private BigDecimal detritoRiscoColisao;

    @ManyToOne
    @JoinColumn(name = "orbita_id")
    private Orbita orbita;
}
