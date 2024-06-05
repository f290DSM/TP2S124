package br.edu.fatecararas.devnotes.api.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

public class ApiError {
    private List<String> errors = new ArrayList<>();

    public ApiError(BindingResult bindingResult) {
        bindingResult
                .getAllErrors()
                .forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

    public ApiError(RegraDeNegocioException rne) {
        this.errors = Arrays.asList(rne.getMessage());
    }

    public ApiError(ResponseStatusException rsce) {
        this.errors = Arrays.asList(rsce.getReason());
    }

    public List<String> getErrors() {
        return errors;
    }

    
}
