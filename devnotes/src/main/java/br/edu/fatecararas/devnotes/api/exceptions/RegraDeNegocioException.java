package br.edu.fatecararas.devnotes.api.exceptions;

public class RegraDeNegocioException extends RuntimeException{
    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }

    public RegraDeNegocioException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
