package io.github.josiasmartins.rest.controller;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.repository.Clientes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/api/clientes")
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

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = this.clientes.findById(id);

        if (cliente.isPresent()) {
//            HttpHeaders headers = new HttpHeaders();
//            List<String> valuesHeader = new ArrayList<>();
//            valuesHeader.add("token");
//            headers.put("Authorization", valuesHeader);
//            ResponseEntity<Cliente> responseEntity =
//                    new ResponseEntity<>(cliente.get(), HttpStatus.OK);

            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("api/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }


}
