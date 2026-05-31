package br.com.fiap.satguard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Endereco {

    @Column(name = "endereco_rua", length = 100)
    private String enderecoRua;

    @Column(name = "endereco_cidade", length = 50)
    private String enderecoCidade;

    @Column(name = "endereco_estado", length = 2)
    private String enderecoEstado;

    @Column(name = "endereco_cep", length = 10)
    private String enderecoCep;
}
