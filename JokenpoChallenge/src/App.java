import java.util.Random;
import java.util.Scanner;

public class App {

    public static final int PAPEL = 1;
    public static final int TESOURA = 2;
    public static final int PEDRA = 3;
    public static final int LAGARTO = 4;
    public static final int SPOCK = 5;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Escolha uma das opções [ 1-PAPEL, 2-TESOURA, 3-PEDRA, 4-LAGARTO, 5-SPOCK ]\n");
        int jogada = in.nextInt();
        int computador = new Random().nextInt(3) + 1;
        System.out.println("O COMPUTADOR escolheu: " + computador);

        if (jogada == PAPEL && computador == PAPEL) {
            System.out.println("Empate. Papel empata com papel!");
        } else if (jogada == PAPEL && computador == TESOURA) {
            System.out.println("Perdeu! Tesoura corta o papel!");
        } else if (jogada == PAPEL && computador == PEDRA) {
            System.out.println("Ganhou! Pedra embrulha o papel!");
        } else if (jogada == PAPEL && computador == LAGARTO) {
            System.out.println("Perdeu! Lagarto come o papel!");
        } else if (jogada == PAPEL) {
            System.out.println("Ganhou! Papel refuta o Spock!");
        } else if (jogada == TESOURA && computador == PAPEL) {
            System.out.println("Ganhou. Tesoura corta o papel!");
        } else if (jogada == TESOURA && computador == TESOURA) {
            System.out.println("Empate! Tesoura empata com tesoura!");
        } else if (jogada == TESOURA && computador == PEDRA) {
            System.out.println("Perdeu! Pedra quebra a tesoura!");
        } else if (jogada == TESOURA && computador == LAGARTO) {
            System.out.println("Ganhou! Tesoura decapta o lagarto!");
        } else if (jogada == TESOURA) {
            System.out.println("Perdeu! Spock derrete a tesoura!");
        } else if (jogada == PEDRA && computador == PAPEL) {
            System.out.println("Perdeu. Papel embrulha a pedar!");
        } else if (jogada == PEDRA && computador == TESOURA) {
            System.out.println("Empate! Tesoura empata com tesoura!");
        } else if (jogada == PEDRA && computador == PEDRA) {
            System.out.println("Empatou! Pedra empata com pedra!");
        } else if (jogada == PEDRA && computador == LAGARTO) {
            System.out.println("Ganhou! Pedra esmaga o lagarto!");
        } else if (jogada == PEDRA) {
            System.out.println("Perdeu! Spock vaporiza a apedra!");
        } else if (jogada == LAGARTO && computador == PAPEL) {
            System.out.println("Ganhou. Lagarto come papel!");
        } else if (jogada == LAGARTO && computador == TESOURA) {
            System.out.println("Perdeu! Tesoura decapta o lagarto!");
        } else if (jogada == LAGARTO && computador == PEDRA) {
            System.out.println("Perdeu! Pedra esmaga lagarto!");
        } else if (jogada == LAGARTO && computador == LAGARTO) {
            System.out.println("Empatou! Lagarto empata com lagarto!");
        } else if (jogada == LAGARTO) {
            System.out.println("Ganhou! Lagarto envenena o Spock!");
        } else if (jogada == SPOCK && computador == PAPEL) {
            System.out.println("Perdeu. Papel refuta Spock!");
        } else if (jogada == SPOCK && computador == TESOURA) {
            System.out.println("Ganhou! Spock derrete a tesoura!");
        } else if (jogada == SPOCK && computador == PEDRA) {
            System.out.println("Ganhou! Spock vaporiza a pedra!");
        } else if (jogada == SPOCK && computador == LAGARTO) {
            System.out.println("Perdeu! Lagarto envenena Spock!");
        } else if (jogada == SPOCK) {
            System.out.println("Empatou! Spock empata com Spock!");
        }

        in.close();
    }

}
