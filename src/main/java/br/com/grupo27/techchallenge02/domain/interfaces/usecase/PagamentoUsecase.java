package br.com.grupo27.techchallenge02.domain.interfaces.usecase;

import java.util.List;

import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;
import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.model.Pedido;

public interface PagamentoUsecase {
    
    Boolean consultaStatusPagamento(Long id);

    Boolean verificaStatusPagamento(Long id);

    List<PedidoDTO> findPedidosByStatusPagamento(boolean pago);

    String geraQrCodePedido(Long id);
    void gerarCobranca(PedidoDTO pedido, Cliente cliente);
}
