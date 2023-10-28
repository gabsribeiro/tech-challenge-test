package br.com.grupo27.techchallenge02.application.usecases.pix;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import br.com.grupo27.techchallenge02.application.dto.PedidoDTO;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixConsultaCobrancaUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixGeraCobrancaUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixGeraQRCodeUseCase;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge02.domain.model.Cliente;
import br.com.grupo27.techchallenge02.domain.model.Pedido;

public class PixUseCaseImpl implements PixUseCase {

    private final PixGeraCobrancaUseCase pixCobranca;
    private final PixGeraQRCodeUseCase pixQrCode;
    private final PixConsultaCobrancaUseCase pixConsulta;

    

    public PixUseCaseImpl(PixGeraCobrancaUseCase pixCobranca, PixGeraQRCodeUseCase pixQrCode,
            PixConsultaCobrancaUseCase pixConsulta) {
        this.pixCobranca = pixCobranca;
        this.pixQrCode = pixQrCode;
        this.pixConsulta = pixConsulta;
    }

    @Override
    public HashMap<String, String> gerarCobranca(PedidoDTO pedido, Cliente cliente){

        return pixCobranca.registraCobranca(pedido, cliente);
    }

    @Override
    public String gerarQrCode(String idCobranca){
        return pixQrCode.criaPixQRCode(idCobranca);
    }

    @Override
    public Boolean consultaStatusPagamento(String idtx) {
        return pixConsulta.consultarCobranca(idtx);
    }

}