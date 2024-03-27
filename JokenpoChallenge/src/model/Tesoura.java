package model;

public class Tesoura implements Algoritmo {

    @Override
    public void executar(TipoJogada tipoJogada) {
        switch (tipoJogada) {
            case PAPEL:
                System.out.println("Ganhou! Tesoura corta o Papel");
                break;
            case LAGARTO:
                System.out.println("Ganhou! Tesoura decapta o Lagarto.");
                break;
            case PEDRA:
                System.out.println("Perdeu! Pedra quebra a Tesoura.");
                break;
            case SPOCK:
                System.out.println("Perdeu! Spock derrete a Tesoura.");
                break;
            default:
                System.out.println("Empatou!");
        }
    }

}
