package br.edu.fatecararas.devnotes.api.exceptions;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public NaoEncontradoException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}
