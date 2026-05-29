package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PLATAFORMA")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plataforma_id")
    private Integer plataformaId;

    @Column(name = "plataforma_nome", length = 50)
    private String plataformaNome;

    @Column(name = "plataforma_status", length = 1)
    private String plataformaStatus;

    @Embedded
    private Coordenada coordenada;
}
