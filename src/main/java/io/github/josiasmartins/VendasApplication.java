package io.github.josiasmartins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // inicializa uma aplicação spring boot
//@ComponentScan(
//        basePackages = {
//                "io.github.josiasmartins.repository.MeuRepository",
//                "io.github.josiasmartins.service.MeuService",
//                "com.outrabiblioteca.projeto"
//        }
//) utilizado quando utiliza biblioteca de terceiros. Ele escaneia do pacote dessa classe (package io.github.josiasmartins) automicamente. Mas fora não
@RestController // diz que é o controlador rest
public class VendasApplication {

//    @Autowired
//    @Qualifier("applicationName")
    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWord() {
        return applicationName;
    }

    // digite psvm
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
