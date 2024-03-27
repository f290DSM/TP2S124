package model;

public enum Tipo {
    PAPEL(1),
    TESOURA(2),
    PEDRA(3),
    LAGARTO(4),
    SPOCK(5);

    private final Integer id;

    public Integer getId() {        
        return id;
    }

    Tipo(Integer id) {
        
        this.id = id;
    }

    public static Tipo getTipo(Integer id) throws Exception {
        return Stream.of(values())
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrows(() -> new RunTimeException("Id invalido para os algoritmos. ID: " + id));
    }

}
