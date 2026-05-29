package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SateliteDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        String sateliteNome,

        @Size(max = 500, message = "A função deve ter no máximo 500 caracteres")
        String sateliteFuncao,

        @NotBlank(message = "O status é obrigatório")
        @Size(max = 1, message = "O status deve ter apenas 1 caractere")
        String sateliteStatus,

        LocalDateTime sateliteDataLancamento,
        BigDecimal velocidade,
        Integer empresaId,
        Integer orbitaId
) {}
