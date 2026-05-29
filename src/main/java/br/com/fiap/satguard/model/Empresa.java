package br.com.fiap.satguard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPRESAS")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Integer empresaId;

    @Column(name = "empresa_nome", length = 50)
    private String empresaNome;

    @Column(name = "empresa_pais", length = 50)
    private String empresaPais;
}
