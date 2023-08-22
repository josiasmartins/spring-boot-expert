package io.github.josiasmartins.rest;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

}
