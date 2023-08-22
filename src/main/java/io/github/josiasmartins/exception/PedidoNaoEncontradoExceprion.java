package io.github.josiasmartins.exception;

public class PedidoNaoEncontradoExceprion extends RuntimeException {

    public PedidoNaoEncontradoExceprion() {
        super("pedido não encontrado");
    }

}
