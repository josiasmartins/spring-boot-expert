package io.github.josiasmartins;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration // passa a ser escanead pelo spring. Configuração customizado
@Profile("development")
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("RODANDO A CONFIGURAÇÃO DE DESENVOLVIMENTO");
        };
    }

//    @Bean(name = "applicationName")
//    public String applicationName() {
//        return "Sistema de Vendas";
//    }

}
