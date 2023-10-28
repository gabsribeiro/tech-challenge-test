package br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix;

import java.util.HashMap;


import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;
import br.com.grupo27.techchallenge02.domain.model.Cliente;

public interface PixUseCase {
    HashMap<String, String> gerarCobranca(PedidoDTO pedido, Cliente cliente);
    String gerarQrCode(String string);
    Boolean consultaStatusPagamento(String idtx);
}
