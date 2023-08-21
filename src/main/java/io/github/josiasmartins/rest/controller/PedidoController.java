package io.github.josiasmartins.rest.controller;

import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.repository.Pedidos;
import io.github.josiasmartins.rest.controller.service.PedidoService;
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

    private Pedidos repository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido getById(@PathVariable("id") Integer id) {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pedidos não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido save(@RequestBody Pedido pedido) {
        return this.repository.save(pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.repository
                .findById(id)
                .map(pedido -> {
                    this.repository.delete(pedido);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pedidos não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody Pedido pedido) {
        this.repository
                .findById(id)
                .map(pedidoExistente -> {
                    pedido.setId(pedidoExistente.getId());
                    this.repository.save(pedido);
                    return pedidoExistente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pedidos não encontrado"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> find(Pedido filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return this.repository.findAll(example);
    }

}
