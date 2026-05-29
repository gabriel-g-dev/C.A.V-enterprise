package br.com.fiap.satguard.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Embeddable
public class Coordenada {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
