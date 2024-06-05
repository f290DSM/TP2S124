package br.edu.fatecararas.devnotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError tratarRegraDeNegocioException(RegraDeNegocioException rne) {
        return new ApiError(rne);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError tratarArgumentosInvalidosException(MethodArgumentNotValidException manve) {
        return new ApiError(manve.getBindingResult());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ExceptionHandler
    public ResponseEntity<?> tratarStatusCodeException(ResponseStatusException rsce) {
        return new ResponseEntity(new ApiError(rsce), rsce.getStatusCode());
    }
}
