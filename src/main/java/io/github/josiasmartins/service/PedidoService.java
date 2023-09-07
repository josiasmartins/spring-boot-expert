package io.github.josiasmartins.service;

import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.domain.enums.StatusPedido;
import io.github.josiasmartins.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
