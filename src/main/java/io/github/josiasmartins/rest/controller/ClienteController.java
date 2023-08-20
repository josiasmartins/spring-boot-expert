package io.github.josiasmartins.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    @ResponseBody
    public String helloClientes(@PathVariable("nome") String nomeCliente) {
        return String.format("HEllo %s ", nomeCliente);
    }

}
