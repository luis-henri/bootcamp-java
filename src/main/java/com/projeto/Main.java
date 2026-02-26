package com.projeto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Server; // Importante para ver o banco no navegador

import java.sql.SQLException;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Inicia o H2 para você ver o banco de dados no navegador
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Console disponível em: http://localhost:8082");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("CRUD rodando, acesse: http://localhost:8080/usuarios\n");
    }
}