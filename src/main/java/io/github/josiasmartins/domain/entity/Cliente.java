package io.github.josiasmartins.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente") // permite associar o schema também
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // gera o id automaticamente
    @Column(name = "id") // serve para fazer as definições das colunas
    private Integer id;
    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome é obrigatorio")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo cpf é obrigatorio")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @JsonIgnore // deve ignorar essa propriedade
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) // um cliente para muitos pedidos
    private Set<Pedido> pedidos;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
