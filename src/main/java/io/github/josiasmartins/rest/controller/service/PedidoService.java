package io.github.josiasmartins.rest.controller.service;

import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
