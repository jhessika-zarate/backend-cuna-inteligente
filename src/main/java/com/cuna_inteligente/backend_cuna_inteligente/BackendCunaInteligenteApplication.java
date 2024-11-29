package com.cuna_inteligente.backend_cuna_inteligente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import io.github.cdimascio.dotenv.Dotenv;
import com.cuna_inteligente.backend_cuna_inteligente.DotenvConfig;


@SpringBootApplication
public class BackendCunaInteligenteApplication  {

    
    public static void main(String[] args) {
        SpringApplication.run(BackendCunaInteligenteApplication.class, args);
    }
}