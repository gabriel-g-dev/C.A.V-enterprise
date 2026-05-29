package br.com.fiap.satguard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(
        @NotBlank(message = "O nome da empresa é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        String empresaNome,

        @NotBlank(message = "O país da empresa é obrigatório")
        @Size(max = 50, message = "O país deve ter no máximo 50 caracteres")
        String empresaPais
) {}
