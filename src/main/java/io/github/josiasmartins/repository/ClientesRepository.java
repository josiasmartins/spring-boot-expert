package io.github.josiasmartins.repository;

import io.github.josiasmartins.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository // acessa a base clientes (tabela)
public class ClientesRepository  {

    public void persistir(Cliente cliente) {
        // acessa a base e salvar o cliente
    }

}
