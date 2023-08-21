package io.github.josiasmartins.rest.controller.service.impl;

import io.github.josiasmartins.repository.Pedidos;
import io.github.josiasmartins.rest.controller.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }

}
