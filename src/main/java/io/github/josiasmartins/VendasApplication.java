package io.github.josiasmartins;

import io.github.josiasmartins.domain.entity.Cliente;
import io.github.josiasmartins.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Douglas"));
            clientes.save(new Cliente("outro cliente"));

            List<Cliente> result = clientes.encontrarPorNome("Douglas");
            result.forEach(System.out::println);

            boolean existe = clientes.existsByNome("Douglas");
            System.out.println("Existe um cliente com o nome Douglas? " + existe);

//            List<Cliente> todosClientes = clientes.findAll();
//            // :: metodo de referencia do java 8
//            todosClientes.forEach(System.out::println);



//            System.out.println("Atualizado clientes");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado.");
//                clientes.save(c);
//            });
//
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Buscando clientes");
//            clientes.findByNomeLike("cli").forEach(System.out::println);
//
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("deletando clientes ");
//            clientes.findAll().forEach(c -> {
//                clientes.delete(c);
//            });
//
//            todosClientes = clientes.findAll();
//            if (todosClientes.isEmpty()) {
//
//            } else {
//                todosClientes.forEach(System.out::println);
//            }
        };
    }

    // digite psvm
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
