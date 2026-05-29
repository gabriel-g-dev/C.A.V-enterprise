package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record AlertaDTO(
        @NotBlank(message = "O nível é obrigatório")
        @Size(max = 1, message = "O nível deve ter 1 caractere")
        String alertaNivel,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String alertaDescricao,

        LocalDateTime alertaData,
        Integer sateliteId,
        Integer plataformaId
) {}
