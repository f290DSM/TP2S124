import java.util.Random;
import java.util.Scanner;

import model.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Escolha uma das opções [ 1-PAPEL, 2-TESOURA, 3-PEDRA, 4-LAGARTO, 5-SPOCK ]\n");
        int jogada = in.nextInt();

        //TODO: Converter jogada de usuario em Enum
        TipoJogada jogadaUsuario = TipoJogada.getTipo(jogada);

        int computador = new Random().nextInt(5) + 1;

        //TODO: Converter jogada do computador em Enum
        TipoJogada jogadaComputador = TipoJogada.getTipo(computador);
        System.out.println("O COMPUTADOR escolheu: " + jogadaComputador);

        //TODO: Executar o algoritmo(contexto).
        Algoritmo algoritmo = getAlgoritmo(jogadaUsuario);
        Jokenpo jokenpo = new Jokenpo();
        jokenpo.setAlgoritmo(algoritmo);

        jokenpo.jogar(jogadaComputador);

        in.close();
    }
    public static Algoritmo getAlgoritmo(TipoJogada tipoJogada) {
        //TODO: Implementar a lógica para criar os algoritmos com base no Tipo.
        return switch (tipoJogada){
            case PAPEL -> new Papel();
            case TESOURA -> new Tesoura();
            default -> null;
        };
    }

}
