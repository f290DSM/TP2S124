package model;

public class Papel implements Algoritmo{

    @Override
    public void executar(TipoJogada tipoJogada) {
        switch (tipoJogada) {
            case TESOURA:
                System.out.println("Perdeu! Tesoura corta o Papel");
                break;
            case PEDRA:
                System.out.println("Ganhou! Papel embrulha a Pedra.");
                break;
            case LAGARTO:
                System.out.println("Perdeu! Lagarto come o Papel.");
                break;
            case SPOCK:
                System.out.println("Ganhou! Papel refuta o Spock.");
                break;
            default:
                System.out.println("Empatou!");
        }
    }
    
}
