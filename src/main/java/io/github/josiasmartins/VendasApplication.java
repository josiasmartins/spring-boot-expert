package io.github.josiasmartins;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.domain.entity.Pedido;
import io.github.josiasmartins.repository.Clientes;
import io.github.josiasmartins.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication // inicializa uma aplicação spring boot
//@ComponentScan(
//        basePackages = {
//                "io.github.josiasmartins.repository.MeuRepository",
//                "io.github.josiasmartins.service.MeuService",
//                "com.outrabiblioteca.projeto"
//        }
//) utilizado quando utiliza biblioteca de terceiros. Ele escaneia do pacote dessa classe (package io.github.josiasmartins) automicamente. Mas fora não
@RestController // diz que é o controlador rest
public class VendasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
