package model;

public class Papel implements Algoritmo{

    @Override
    public void executar(Tipo tipo) {
        switch (tipo) {
            case TESOURA -> System.out.println("Perdeu! Tesoura corta o Papel");
            case PEDRA -> System.out.println("Ganhou! Papel embrulha a Pedra.");
            case LAGARTO -> System.out.println("Perdeu! Lagarto come o Papel.");
            case SPOCK -> System.out.println("Ganhou! Papel refuta o Spock.");
            default -> System.out.println("Empatou!");
        }
    }
    
}
