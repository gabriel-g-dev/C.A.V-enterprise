package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ALERTAS")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alerta_id")
    private Integer alertaId;

    @Column(name = "alerta_nivel", length = 1)
    private String alertaNivel;

    @Column(name = "alerta_descricao", length = 500)
    private String alertaDescricao;

    @Column(name = "alerta_data")
    private LocalDateTime alertaData;

    @ManyToOne
    @JoinColumn(name = "satelite_id")
    private Satelite satelite;

    @ManyToOne
    @JoinColumn(name = "PLATAFORMA_plataforma_id")
    private Plataforma plataforma;
}
