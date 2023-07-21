package io.github.josiasmartins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // inicializa uma aplicação spring boot
@RestController // diz que é o controlador rest
public class VendasApplication {

    @GetMapping("/hello")
    public String helloWord() {
        return "hello word";
    }

    // digite psvm
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
