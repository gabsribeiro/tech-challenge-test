package br.com.grupo27.techchallenge02.application.usecases.pix.utils;

import org.json.JSONObject;

import br.com.grupo27.techchallenge02.external.config.Credenciais;

public class PixUtils {

    private static final String CHAVE_PIX = "4b4bd5d4-1060-4fe6-b643-562803049d66";

    private PixUtils() {
    }

    public static JSONObject configurarOpcoes(Credenciais credentials) {
        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());
        return options;
    }

    public static JSONObject construirCorpoCobranca(Long id, String cpf, String nome) {
        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", cpf).put("nome", nome));
        body.put("valor", new JSONObject().put("original", "0.01"));
        body.put("chave", CHAVE_PIX);
        body.put("solicitacaoPagador", "Cobrança para o pedido id:" + id);
        return body;
    }
}
