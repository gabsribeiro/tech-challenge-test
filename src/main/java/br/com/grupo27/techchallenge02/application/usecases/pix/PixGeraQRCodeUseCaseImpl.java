package br.com.grupo27.techchallenge02.application.usecases.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.grupo27.techchallenge02.application.usecases.pix.utils.PixUtils;
import br.com.grupo27.techchallenge02.domain.interfaces.usecase.pix.PixGeraQRCodeUseCase;
import br.com.grupo27.techchallenge02.external.config.Credenciais;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PixGeraQRCodeUseCaseImpl implements PixGeraQRCodeUseCase{
    private final Credenciais credentials;
    private static final Logger logger = LoggerFactory.getLogger(PixGeraQRCodeUseCaseImpl.class);

    public PixGeraQRCodeUseCaseImpl(Credenciais credentials) {
        this.credentials = credentials;
    }

    @Override
    public String criaPixQRCode(String idCobranca){
        return extrairValorQRCodeDoJSON(gerarPixQRCode(idCobranca));
    }

    private Map<String, Object> gerarPixQRCode(String idCobranca) {
        JSONObject options = configurarOpcoes();

        HashMap<String, String> params = new HashMap<>();
        params.put("id", idCobranca);
    
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<>());
            logger.info("QRCode PIX gerado com sucesso: {}", response);
    
            return response;
        } catch (EfiPayException e) {
            logger.error("Erro ao gerar QRCode: {}", e.getErrorDescription());
            return Collections.emptyMap(); 
        } catch (Exception e) {
            logger.error("Erro desconhecido ao gerar QRCode: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }

    private String extrairValorQRCodeDoJSON(Map<String, Object> response) {
        if (response.containsKey("qrcode")) {
            return (String) response.get("qrcode");
        }
        return null; 
    }
    
    private JSONObject configurarOpcoes() {
        return PixUtils.configurarOpcoes(credentials);
    }
}
