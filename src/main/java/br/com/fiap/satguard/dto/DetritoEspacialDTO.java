package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record DetritoEspacialDTO(
        @NotNull(message = "O tamanho é obrigatório")
        @DecimalMin(value = "0.0", message = "O tamanho não pode ser negativo")
        BigDecimal detritoTamanho,

        @NotNull(message = "O risco de colisão é obrigatório")
        BigDecimal detritoRiscoColisao,

        @NotNull(message = "A velocidade é obrigatória")
        BigDecimal velocidade,

        Integer orbitaId
) {}
