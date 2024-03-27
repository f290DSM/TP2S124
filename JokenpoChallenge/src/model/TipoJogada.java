package model;

import java.util.stream.Stream;

public enum TipoJogada {
    PAPEL(1),
    TESOURA(2),
    PEDRA(3),
    LAGARTO(4),
    SPOCK(5);

    private final Integer id;

    public Integer getId() {        
        return id;
    }

    TipoJogada(Integer id) {
        
        this.id = id;
    }

    public static TipoJogada getTipo(Integer id) throws RuntimeException {
        return Stream.of(values())
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Id invalido para os algoritmos. ID: " + id));
    }

}
