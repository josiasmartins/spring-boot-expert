package io.github.josiasmartins.rest.dto;

import io.github.josiasmartins.domain.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente;
    @NotNull(message = "campo total do pedido é obrigatorio.")
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

}
