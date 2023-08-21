package io.github.josiasmartins.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne // usado quando a entidade posui muitos pedidos
    @JoinColumn(name = "cliente_id") // coluna com a chave estrangeira
    private Cliente cliente; // chave estrangeira

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "total", precision = 20, scale = 2) // scale: casas decimais
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }
}
