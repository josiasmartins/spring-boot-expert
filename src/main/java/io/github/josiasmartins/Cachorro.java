package io.github.josiasmartins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) // para um anotation do propriedade funcionar, precisar adicionar @TARGET e @Retention
@Autowired // IOC (injecao de dependencia)
@Qualifier("cachorro") // resposável por o beans com o nome "cachorro"
public @interface Cachorro {
}
