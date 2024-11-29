package com.cuna_inteligente.backend_cuna_inteligente;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        // Cargar el archivo .env desde el directorio raíz del proyecto
        return Dotenv.load();
    }
}
