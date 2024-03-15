package model;

public class Tesoura extends Algoritmo {

    @Override
    public void executar(Tipo tipo) {
        switch (tipo) {
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
