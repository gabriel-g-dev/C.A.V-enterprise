package br.com.fiap.satguard.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.math.BigDecimal;

@Data
@MappedSuperclass
public abstract class ObjetoEspacial {
    private BigDecimal velocidade;
}
