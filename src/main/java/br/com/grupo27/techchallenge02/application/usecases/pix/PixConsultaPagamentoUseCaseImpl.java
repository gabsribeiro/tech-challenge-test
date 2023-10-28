package br.com.grupo27.techchallenge02.application.usecases.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.grupo27.techchallenge02.application.usecases.pix.utils.PixUtils;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixConsultaCobrancaUseCase;
import br.com.grupo27.techchallenge02.external.config.Credenciais;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PixConsultaPagamentoUseCaseImpl implements PixConsultaCobrancaUseCase{
    private final Credenciais credentials;
    private static final Logger logger = LoggerFactory.getLogger(PixConsultaPagamentoUseCaseImpl.class);

    public PixConsultaPagamentoUseCaseImpl(Credenciais credentials) {
        this.credentials = credentials;
    }

    @Override
    public boolean consultarCobranca(String idtx){
        String status = extrairStatus(detalhaCobrancaPix(idtx));
        logger.info("Efi - Status pagamento:{}.", status);
        if (status != null) {
            return status.equals("CONCLUIDA");
        }
        return false;
    }

    private Map<String, Object> detalhaCobrancaPix(String idtx) {
        JSONObject options = configurarOpcoes();
        HashMap<String, String> params = new HashMap<>();
        params.put("txid", idtx); 
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("pixDetailCharge", params, new HashMap<>());
            logger.info("Consulta de Cobrança PIX: {}", response);
    
            return response;
        } catch (EfiPayException e) {
            logger.error("Erro ao consultar cobrança PIX: {}", e.getErrorDescription());
            return Collections.emptyMap(); 
        } catch (Exception e) {
            logger.error("Erro desconhecido ao consultar cobrança PIX: {}", e.getMessage());
            return Collections.emptyMap(); 
        }
    }

    private String extrairStatus(Map<String, Object> detalhesCobranca) {
        if (detalhesCobranca != null && detalhesCobranca.containsKey("status")) {
            return (String) detalhesCobranca.get("status");
        } else {
            logger.error("Os detalhes da cobrança PIX não contêm o campo 'status' ou são nulos.");
            return null;
        }
    }
    
    private JSONObject configurarOpcoes() {
        return PixUtils.configurarOpcoes(credentials);
    }
}
