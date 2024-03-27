import java.util.Random;
import java.util.Scanner;

import model.Algoritmo;
import model.Tipo;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Escolha uma das opções [ 1-PAPEL, 2-TESOURA, 3-PEDRA, 4-LAGARTO, 5-SPOCK ]\n");
        int jogada = in.nextInt();

        //TODO: Converter jogada de usuario em Enum

        int computador = new Random().nextInt(3) + 1;

        //TODO: Converter jogada do computador em Enum
        System.out.println("O COMPUTADOR escolheu: " + computador);

        //TODO: Executar o algoritmo(contexto).

        in.close();
    }
    public static Algoritmo getAlgoritmo(Tipo tipo) {
        //TODO: Implementar a lógica para criar os algoritmos com base no Tipo.
        return null;
    }

}
