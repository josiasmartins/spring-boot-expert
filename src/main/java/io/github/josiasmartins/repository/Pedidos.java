package io.github.josiasmartins.repository;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
