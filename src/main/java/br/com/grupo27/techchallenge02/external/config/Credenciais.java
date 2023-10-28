package br.com.grupo27.techchallenge02.external.config;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Credenciais {
    private static final Logger logger = LoggerFactory.getLogger(Credenciais.class);

    private String clientId;
    private String clientSecret;
    private String certificate;
    private boolean sandbox;
    private boolean debug;

    public Credenciais() {
        loadCredentials();
    }

    private void loadCredentials() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream credentialsFile = classLoader.getResourceAsStream("credentials.json")) {
            if (credentialsFile != null) {
                JSONTokener tokener = new JSONTokener(credentialsFile);
                JSONObject credentials = new JSONObject(tokener);

                this.clientId = credentials.getString("client_id");
                this.clientSecret = credentials.getString("client_secret");
                this.certificate = credentials.getString("certificate");
                this.sandbox = credentials.getBoolean("sandbox");
                this.debug = credentials.getBoolean("debug");
            } else {
                logger.error("credentials.json not found or could not be opened.");
            }
        } catch (IOException e) {
            logger.error("Error while reading credentials.json: {}", e.getMessage());
        }
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCertificate() {
        return certificate;
    }

    public boolean isSandbox() {
        return sandbox;
    }

    public boolean isDebug() {
        return debug;
    }
}
