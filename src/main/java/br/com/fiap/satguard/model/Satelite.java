package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "SATELITES")
@PrimaryKeyJoinColumn(name = "objeto_id")
public class Satelite extends ObjetoEspacial {

    @Column(name = "satelite_nome", length = 50)
    private String sateliteNome;

    @Column(name = "satelite_funcao", length = 500)
    private String sateliteFuncao;

    @Column(name = "satelite_status", length = 1)
    private String sateliteStatus;

    @Column(name = "satelite_data_lancamento")
    private LocalDateTime sateliteDataLancamento;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "orbita_id")
    private Orbita orbita;
}
