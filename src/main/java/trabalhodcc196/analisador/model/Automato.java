package trabalhodcc196.analisador.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Automato {
    private List<Estado> estados;
    private List<Transicao> transicoes;
    private List<Estado> estadosFinais;
    private List<Character> alfabeto;


    public Automato(List<Estado> estados, List<Transicao> transicoes, List<Estado> estadosFinais, List<Character> alfabeto) {
        this.estados = estados;
        this.transicoes = transicoes;
        this.estadosFinais = estadosFinais;
        this.alfabeto = alfabeto;
    }

    public Automato() {
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public List<Estado> getEstadosFinais() {
        return estadosFinais;
    }

    public void setEstadosFinais(List<Estado> estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    public List<Character> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(List<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }
    public Estado getEstadoByRotulo(String r){
        return estados.stream().filter(estado -> r.equals(estado.getRotulo()))
                .findFirst().orElse(null);
    }

    public List<Estado> getDestinosByOrigemByCaracter(Estado origem, Character caracter) {
        return transicoes.stream()
                .filter(transicao -> {return caracter.toString().equals(transicao.getCaracter()) &&
                        origem.getRotulo().equals(transicao.getOrigem().getRotulo())
                        ;})
                .map(Transicao::getDestino)
                .collect(Collectors.toList());
    }

    public List<Estado> getDestinosByOrigem(Estado origem) {
        return transicoes.stream()
                .filter(transicao -> {return origem.getRotulo().equals(transicao.getOrigem().getRotulo());})
                .map(Transicao::getDestino)
                .collect(Collectors.toList());
    }

    public Transicao getTransicaoByOrigemByDestino(Estado origem, Estado destino){
        return transicoes.stream().filter(transicao -> {
            return transicao.getOrigem().equals(origem) && transicao.getDestino().equals(destino);})
                .findFirst().orElse(null);
    }

    public List<Estado> getOrigensByDestino(Estado destino) {
        return transicoes.stream()
                .filter(transicao -> {return destino.getRotulo().equals(transicao.getDestino().getRotulo());})
                .map(Transicao::getDestino)
                .collect(Collectors.toList());
    }

    public void adicionarTransicao(Transicao transicao) {
        this.transicoes.add(transicao);
    }

    public void removerTransicao(Transicao transicao) {
        this.transicoes.remove(transicao);
    }

    public void adicionarEstado(Estado estado) {
        this.estados.add(estado);
    }

    public abstract void mostrarAutomato();

    public abstract boolean isInicial(Estado estado);

}
