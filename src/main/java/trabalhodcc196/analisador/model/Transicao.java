package trabalhodcc196.analisador.model;

import java.util.List;

public class Transicao {
    private String caracter;
    private Estado origem;
    private Estado destino;

    public Transicao(String caracter, Estado origem, Estado destino) {
        this.caracter = caracter;
        this.origem = origem;
        this.destino = destino;
    }

    public Transicao() {
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public Estado getOrigem() {
        return origem;
    }

    public void setOrigem(Estado origem) {
        this.origem = origem;
    }

    public Estado getDestino() {
        return destino;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }


}
