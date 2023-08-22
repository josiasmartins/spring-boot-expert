package io.github.josiasmartins.rest.controller;

import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.repository.Pedidos;
import io.github.josiasmartins.rest.controller.service.PedidoService;
import io.github.josiasmartins.rest.dto.PedidoDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = this.service.salvar(dto);
        return pedido.getId();
    }

}
