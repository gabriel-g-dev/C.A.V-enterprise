package br.com.fiap.satguard.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AcessoLogId implements Serializable {
    private Integer usuarioId;
    private LocalDateTime dataAcesso;
}
