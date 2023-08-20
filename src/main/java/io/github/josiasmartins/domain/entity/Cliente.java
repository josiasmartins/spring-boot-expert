package io.github.josiasmartins.domain.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cliente") // permite associar o schema também
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // gera o id automaticamente
    @Column(name = "id") // serve para fazer as definições das colunas
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;

    @OneToMany(mappedBy = "cliente") // um cliente para muitos pedidos
    private Set<Pedido> pedidos;

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente() {}

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
