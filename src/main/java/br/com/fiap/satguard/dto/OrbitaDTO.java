package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record OrbitaDTO(
        @NotNull(message = "A altitude é obrigatória")
        @DecimalMin(value = "0.0", inclusive = false, message = "A altitude deve ser maior que zero")
        BigDecimal orbitaAltitudeKm,

        @NotBlank(message = "A categoria é obrigatória")
        @Size(max = 3, message = "A categoria deve ter no máximo 3 caracteres")
        String orbitaCategoria,

        @NotNull(message = "A inclinação é obrigatória")
        BigDecimal orbitaInclinacao
) {}
