package com.cuna_inteligente.backend_cuna_inteligente;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.springframework.stereotype.Component;
@Component
public class SSLValidator {

    public boolean isSSLValid(String url) {
        try {
            // Intentar conectarse al servidor a través de HTTPS
            URL connectionUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) connectionUrl.openConnection();
            
            // Establecer un tiempo de espera
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            // Intentar obtener el certificado SSL del servidor
            connection.connect();
            
            // Si la conexión se establece correctamente, el certificado es válido
            connection.disconnect();
            return true;
        } catch (IOException e) {
            // Si hay un error, el certificado no es válido o la conexión falló
            System.out.println("Error al verificar el certificado SSL: " + e.getMessage());
            return false;
        }
    }
}
