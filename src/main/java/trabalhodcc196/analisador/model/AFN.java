package trabalhodcc196.analisador.model;

import java.util.*;
import java.util.stream.Collectors;

public class AFN extends Automato {
	List<Estado> estadosIniciais;
	List<Character> alfabeto;

    public AFN(List<Estado> estados, List<Transicao> transicoes, List<Estado> estadosFinais, List<Character> alfabeto, List<Estado> estadosIniciais, List<Character> alfabeto1) {
        super(estados, transicoes, estadosFinais, alfabeto);
        this.estadosIniciais = estadosIniciais;
        this.alfabeto = alfabeto1;
    }

    public AFD toAFD(){
        AFD afd = new AFD();
        Set<Estado> estadosAFD = new HashSet<>();
        Estado inicialAFD = montarEstadoAFD(estadosIniciais);
        estadosAFD.add(inicialAFD);
        for (Estado estado : estadosAFD) {

            List<Estado> origens = Arrays.stream(estado.getRotulo().split(","))
                    .map(str -> new Estado(str)).
                    collect(Collectors.toList());

            alfabeto.forEach(caracter -> {
                List<Estado> destinos = new ArrayList<>();
                origens.forEach(origem ->{
                    destinos.addAll(getDestinosByOrigemByCaracter(origem,caracter));
                });
                if(destinos.size()!=0){
                    Estado novoEstado = montarEstadoAFD(destinos);
                    Transicao novaTransicao = new Transicao(caracter.toString(),estado,novoEstado);
                    afd.adicionarEstado(novoEstado);
                    afd.adicionarTransicao(novaTransicao);
                    estadosAFD.add(novoEstado);
                }
            });
        }

        return afd;
    }

    public Estado montarEstadoAFD(List<Estado> estadosAFN){
        String rotulo = "";
        for (Estado estado : estadosAFN) {
            rotulo = rotulo + estado.getRotulo() + ",";
        }
        rotulo = rotulo.substring(0,rotulo.lastIndexOf(","));
        return new Estado(rotulo);
    }
}
