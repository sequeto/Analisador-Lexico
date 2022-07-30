package trabalhodcc196.analisador.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public abstract class Automato implements Cloneable{
    private List<Estado> estados = new ArrayList<>();
    private List<Transicao> transicoes = new ArrayList<>();
    private List<Estado> estadosFinais = new ArrayList<>();
    private List<Character> alfabeto = new ArrayList<>();


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

    public List <Transicao> getTransicoesByOrigemByDestino(Estado origem, Estado destino){
        return transicoes.stream().filter(transicao -> {
            return transicao.getOrigem().equals(origem) && transicao.getDestino().equals(destino);})
                .collect(Collectors.toList());
    }

    public List <Transicao> getTransicoesByOrigem(Estado origem){
        return transicoes.stream().filter(transicao -> {
                    return transicao.getOrigem().equals(origem);})
                .collect(Collectors.toList());
    }

    public List<Estado> getOrigensByDestino(Estado destino) {
        return transicoes.stream()
                .filter(transicao -> {return destino.getRotulo().equals(transicao.getDestino().getRotulo());})
                .map(Transicao::getOrigem)
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

    public void adicionarFinal(Estado estado) {
        this.estadosFinais.add(estado);
    }

    public Boolean isFinal(Estado estado) {
        return this.estadosFinais.contains(estado);
    }

    public Boolean existeCaminho(Estado inicial, Estado terminal) {
        System.out.println(String.format("Buscando terminal %s por %s", terminal.getRotulo(), inicial.getRotulo()));

        if(inicial.equals(terminal)) return true;
        List<Estado> processados = new ArrayList<>();
        Stack<Estado> estadosBusca = new Stack<>();
        estadosBusca.addAll(getDestinosByOrigem(inicial));
        while (!estadosBusca.isEmpty()) {
            Estado proximo = estadosBusca.pop();
            processados.add(proximo);
            if (proximo.equals(terminal)) {
                return true;
            }
            List<Estado> aProcessar = getDestinosByOrigem(proximo);
            aProcessar.forEach(estado -> {
                if (!processados.contains(estado) && !estadosBusca.contains(estado)) {
                    estadosBusca.add(estado);
                }
            });
        }

        return false;
    }

    public void removerInacessiveis() {
        List<Estado> listaIniciais = new ArrayList<>();
        if (this instanceof AFD){
            listaIniciais.add(((AFD) this).getEstadoInicial());
        }
        if (this instanceof AFN){
            listaIniciais.addAll(((AFN) this).getEstadosIniciais());
        }
        List<Estado> estadosCopia = new ArrayList<>(estados);
        estadosCopia.forEach(estado -> {
            Boolean alcança = false;
            for (Estado inicial : listaIniciais) {
                if(existeCaminho(inicial,estado)) alcança = true;
            };
            if(!alcança) removerEstado(estado);
        });
    }

    public void removerInuteis() {
        List<Estado> listaTerminais = new ArrayList<>();

        listaTerminais.addAll(estadosFinais);
        List<Estado> estados = new ArrayList<>(this.getEstados());
        estados.forEach(estado -> {
            listaTerminais.forEach(inicial -> {
                Boolean alcança = false;
                for (Estado terminal : listaTerminais) {
                    if(existeCaminho(estado,terminal)) alcança = true;
                };
                if(!alcança) removerEstado(estado);
            });
        });
    }

    private void removerEstado(Estado estado) {
        this.getEstados().remove(estado);
        estadosFinais = estadosFinais.stream()
                .filter(terminal -> {return !terminal.equals(estado);})
                .collect(Collectors.toList());
        if (this instanceof AFD){
            ((AFD) this).setEstadoInicial(null);
        }
        if (this instanceof AFN){
            ((AFN) this).setEstadosIniciais(((AFN) this).getEstadosIniciais().stream()
                    .filter(terminal -> {return !terminal.equals(estado);})
                    .collect(Collectors.toList()));
        }

        transicoes = transicoes.stream()
                .filter(transicao -> {return !transicao.getOrigem().equals(estado);})
                .collect(Collectors.toList());

        transicoes = transicoes.stream()
                .filter(transicao -> {return !transicao.getDestino().equals(estado);})
                .collect(Collectors.toList());

    }


}
