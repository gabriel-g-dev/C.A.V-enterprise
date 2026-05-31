package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "OBJETO_ESPACIAL")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ObjetoEspacial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objeto_id")
    private Integer id;

    @Column(name = "velocidade", precision = 10, scale = 2)
    private BigDecimal velocidade;
}
