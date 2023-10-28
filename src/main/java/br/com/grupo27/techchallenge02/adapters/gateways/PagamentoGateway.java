package br.com.grupo27.techchallenge02.adapters.gateways;

import br.com.grupo27.techchallenge02.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge02.domain.model.Pagamento;

public interface PagamentoGateway {
    
    Pagamento savePagamento(Pagamento pagamento);

    Pagamento findPagamentoByIdPedido(Long id);

}
