package io.github.josiasmartins.rest.controller;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.repository.Clientes;
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
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController // adiciona @ResponseBody em todos os metodos
@RequestMapping("/api/clientes")
public class ClienteController {

    public Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

//    @RequestMapping(
//            value = {"/hello/{nome}", "/api/hello"},
//            method = RequestMethod.GET,
//            consumes = { "application/json", "application/xml" }, // tipo de conteudo do metodo pode receber
//            produces = { "appliction/json", "application/xml" } // tipo de retorno do objeto
//    )
//    @ResponseBody
//    public String helloClientes(@PathVariable("nome") String nomeCliente) {
//        return String.format("HEllo %s ", nomeCliente);
//    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id) {
//        Optional<Cliente> cliente = this.clientes.findById(id);

        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"
                ));

//        if (cliente.isPresent()) {
////            HttpHeaders headers = new HttpHeaders();
////            List<String> valuesHeader = new ArrayList<>();
////            valuesHeader.add("token");
////            headers.put("Authorization", valuesHeader);
////            ResponseEntity<Cliente> responseEntity =
////                    new ResponseEntity<>(cliente.get(), HttpStatus.OK);
//
//            return cliente.get();
//        }

//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
//        Cliente clienteSalvo = clientes.save(cliente);
//        return ResponseEntity.ok(clienteSalvo);
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
//        Optional<Cliente> cliente = clientes.findById(id);
//
//        if (cliente.isPresent()) {
//            clientes.delete(cliente.get());
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.notFound().build();

        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
//        return clientes.findById(id)
//                .map(clienteExistente -> {
//                    cliente.setId(clienteExistente.getId());
//                    clientes.save(cliente);
//                    return ResponseEntity.noContent().build();
//                }).orElseGet(() -> ResponseEntity.notFound().build());

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
