package br.edu.fatecararas.devnotes.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

public class ApiError {
    @Getter
    private List<String> errors = new ArrayList<>();
    public String path;
    public String date = LocalDateTime.now().toString();

    public ApiError(BindingResult bindingResult, String path) {
        bindingResult
                .getAllErrors()
                .forEach(error -> this.errors.add(error.getDefaultMessage()));
        this.path = path;
    }

    public ApiError(RegraDeNegocioException rne, String path) {
        this.errors = Collections.singletonList(rne.getMessage());
        this.path = path;
    }

    public ApiError(NaoEncontradoException rne, String path) {
        this.errors = Collections.singletonList(rne.getMessage());
        this.path = path;
    }

    public ApiError(ResponseStatusException rsce, String path) {
        this.errors = Collections.singletonList(rsce.getReason());
        this.path = path;
    }


}
