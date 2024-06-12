package br.edu.fatecararas.devnotes.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
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
    public ApiError tratarRegraDeNegocioException(RegraDeNegocioException rne, HttpServletRequest request) {
        return new ApiError(rne, request.getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError tratarNaoEncontradoException(NaoEncontradoException nee, HttpServletRequest request) {
        return new ApiError(nee, request.getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError tratarArgumentosInvalidosException(MethodArgumentNotValidException manve, HttpServletRequest request) {
        return new ApiError(manve.getBindingResult(), request.getRequestURI());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ExceptionHandler
    public ResponseEntity<?> tratarStatusCodeException(ResponseStatusException rsce, HttpServletRequest request) {
        return new ResponseEntity(new ApiError(rsce, request.getRequestURI()), rsce.getStatusCode());
    }
}
