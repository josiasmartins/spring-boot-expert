package io.github.josiasmartins.rest.controller;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.repository.Clientes;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController // adiciona @ResponseBody em todos os metodos
@RequestMapping("/api/clientes")
@Api("Api de clientes") // diz qual tipo de api para o swagger
public class ClienteController {

    public Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado"),
    })
    public Cliente getClienteById(@PathVariable("id") @ApiParam("Id do cliente") Integer id) {
//        Optional<Cliente> cliente = this.clientes.findById(id);

        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"
                ));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "cliente salvo com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado"),
    })
    public Cliente save(@RequestBody @Valid Cliente cliente) {
//        Cliente clienteSalvo = clientes.save(cliente);
//        return ResponseEntity.ok(clienteSalvo);
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {

        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {

        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher // define algumas configurações atraves da propriedade
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

       Example example = Example.of(filtro, matcher);
       return clientes.findAll(example);

    }


}
