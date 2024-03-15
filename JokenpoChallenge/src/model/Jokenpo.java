package model;

public class Jokenpo {

    private Algoritmo algoritmo;
    public void setAlgoritmo(Algoritmo algoritmo) {
        this.algoritmo = algoritmo;
    }

    public void jogar(Tipo tipo) {
        this.algoritmo.executar(tipo);
    }

}
