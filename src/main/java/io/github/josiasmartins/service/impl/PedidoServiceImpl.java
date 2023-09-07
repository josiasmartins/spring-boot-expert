package io.github.josiasmartins.service.impl;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.domain.entity.ItemPedido;
import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.domain.entity.Produto;
import io.github.josiasmartins.domain.enums.StatusPedido;
import io.github.josiasmartins.exception.PedidoNaoEncontradoExceprion;
import io.github.josiasmartins.exception.RegraNegocioException;
import io.github.josiasmartins.repository.Clientes;
import io.github.josiasmartins.repository.ItemsPedido;
import io.github.josiasmartins.repository.Pedidos;
import io.github.josiasmartins.repository.Produtos;
import io.github.josiasmartins.service.PedidoService;
import io.github.josiasmartins.rest.dto.ItemPedidoDTO;
import io.github.josiasmartins.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;


    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idClientes= dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idClientes)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
       pedido.setTotal(dto.getTotal());
       pedido.setDataPedido(LocalDateTime.now());
       pedido.setCliente(cliente);
       pedido.setStatus(StatusPedido.REALIZADO);

       List<ItemPedido> itemsPedido = this.converterItems(pedido, dto.getItems());
       repository.save(pedido);
       itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
       return pedido;
    }


    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return this.repository.findByIdFetchItens(id);
    }

    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        this.repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoExceprion());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
