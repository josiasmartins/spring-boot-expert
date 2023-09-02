package io.github.josiasmartins.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id // key primary
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    @NotEmpty(message = "Campo descricao é obrigatório.")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "Campo preço é obrigatorio.")
    private BigDecimal preco;

}
