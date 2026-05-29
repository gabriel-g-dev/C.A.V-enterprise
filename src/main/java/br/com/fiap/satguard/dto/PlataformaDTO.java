package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record PlataformaDTO(
        @NotBlank(message = "O nome da plataforma é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        String plataformaNome,

        @NotBlank(message = "O status é obrigatório")
        @Size(max = 1, message = "O status deve ter apenas 1 caractere")
        String plataformaStatus,

        BigDecimal latitude,
        BigDecimal longitude
) {}
