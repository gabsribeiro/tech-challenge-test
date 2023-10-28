package br.com.grupo27.techchallenge02.application.usecases.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.grupo27.techchallenge02.external.config.Credenciais;

import java.util.HashMap;
import java.util.Map;

public class PixConfigWebhook {

    public static void main(String[] args) {

        Credenciais credentials = new Credenciais();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        options.put("x-skip-mtls-checking", "true");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("chave", "4b4bd5d4-1060-4fe6-b643-562803049d66");

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("webhookUrl", "https://webhook.site/f01c7d9d-5051-497b-9ccd-ff6ec8a05b62");

        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("pixConfigWebhook", params, body);
            System.out.println(response);

        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
