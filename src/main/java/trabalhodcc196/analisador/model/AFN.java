package trabalhodcc196.analisador.model;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;


public class AFN extends Automato implements Cloneable {
	private List<Estado> estadosIniciais = new ArrayList<>();
 	private List<Character> alfabeto = new ArrayList<>();

    public AFN() {
    }

    public AFN(List<Estado> estados, List<Transicao> transicoes, List<Estado> estadosFinais, List<Character> alfabeto, List<Estado> estadosIniciais, List<Character> alfabeto1) {
        super(estados, transicoes, estadosFinais, alfabeto);
        this.estadosIniciais = estadosIniciais;
        this.alfabeto = alfabeto1;
    }
    
    public AFN(char caracter) {
    	super();
    	this.estadosIniciais = new ArrayList<>();
    	this.alfabeto = new ArrayList<>();
    	
        Estado estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        Transicao transicao = new Transicao(Character.toString(caracter), estadoInicial, estadoFinal);
        
        this.getEstadosIniciais().add(estadoInicial);
        this.getEstadosFinais().add(estadoFinal);
        this.getEstados().add(estadoInicial);
        this.getEstados().add(estadoFinal);
        this.getTransicoes().add(transicao);
        this.getAlfabeto().add(caracter);
    }

    public List<Estado> getEstadosIniciais() {
        return estadosIniciais;
    }

    public void setEstadosIniciais(List<Estado> estadosIniciais) {
        this.estadosIniciais = estadosIniciais;
    }

    @Override
    public List<Character> getAlfabeto() {
        return alfabeto;
    }

    @Override
    public void setAlfabeto(List<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public AFD toAFD(){
        AFD afd = new AFD();
        afd.setAlfabeto(this.alfabeto);
        Estado inicialAFD = montarEstadoAFD(estadosIniciais);
        afd.adicionarEstado(inicialAFD);
        afd.setEstadoInicial(inicialAFD);
        List<Estado> processados = new ArrayList<>();
        Queue<Estado> aProcessar = new LinkedBlockingDeque<Estado>();
        aProcessar.add(inicialAFD);
        Estado estadoAtual = null;
        while(!aProcessar.isEmpty()) {
            estadoAtual = aProcessar.poll();
            processados.add(estadoAtual);
            Set<Estado> origens = Arrays.stream(estadoAtual.getRotulo().split(","))
                    .map(str -> new Estado(str)).
                    collect(Collectors.toSet());

            Boolean isFinal = false;
            for(Character caracter: this.alfabeto) {
                List<Estado> destinos = new ArrayList<>();
                for (Estado origem : origens) {
                    destinos.addAll(getDestinosByOrigemByCaracter(origem, caracter));
                    if(isFinal(origem)){
                    	isFinal = true;
                	}
                }
                if(isFinal && !afd.getEstadosFinais().contains(estadoAtual)){
                    afd.adicionarFinal(estadoAtual);
                }
                
                destinos = destinos.stream().distinct().collect(Collectors.toList());
                
                if(destinos.size()!= 0){
                    Estado novoEstado = montarEstadoAFD(destinos);
                    Transicao novaTransicao = new Transicao(caracter.toString(),estadoAtual,novoEstado);
                    if(!afd.getTransicoes().contains(novaTransicao)){
                    	afd.adicionarTransicao(novaTransicao);
                    }

                    if (!estadoAtual.equals(novoEstado) && (!processados.contains(novoEstado)) && (!aProcessar.contains(novoEstado))){
                        aProcessar.add(novoEstado);
                    }
                    if(!afd.getEstados().contains(novoEstado)){
                    	afd.adicionarEstado(novoEstado);
                    }


                }
            };
        }
        
        afd.setTransicoes(afd.getTransicoes()
                .stream()
                .sorted(Comparator.comparing(transicao -> {return transicao.getOrigem().getRotulo();}))
                .collect(Collectors.toList()));
        
        return afd;
    }

    private Estado montarEstadoAFD(List<Estado> estadosAFN){
        String rotulo = "";
        estadosAFN = estadosAFN.stream()
                .sorted(Comparator.comparing(Estado::getRotulo))
                .collect(Collectors.toList());
        for (Estado estado : estadosAFN) {
            rotulo = rotulo + estado.getRotulo() + ",";
        }
        rotulo = rotulo.substring(0,rotulo.lastIndexOf(","));
        return new Estado(rotulo);
    }

    public AFN afnLambdaToAfn() throws CloneNotSupportedException {
        AFN afn = (AFN) this.clone();
        List<Transicao> toRemove = new ArrayList<>();
        afn.getEstados().forEach(pivot -> {

            Queue<Transicao> filaProcessamento = new LinkedBlockingDeque(getTransicoesByOrigem(pivot));


            while (!filaProcessamento.isEmpty()) {
                Transicao transicao = filaProcessamento.poll();
                if(transicao.getCaracter().equals("\u03BB")) {
                    List<Transicao> proximas = getTransicoesByOrigem(transicao.getDestino());
                    proximas.forEach(proxima -> {
                        filaProcessamento.add(proxima);
                    });
                    if(afn.isFinal(transicao.getDestino()) && !afn.getEstadosFinais().contains(pivot)) {
                        afn.adicionarFinal(pivot);
                    }
                    toRemove.add(transicao);
                } else {
                    Transicao nova = new Transicao(transicao.getCaracter(),pivot,transicao.getDestino());
                    if(!afn.getTransicoes().contains(nova)){afn.adicionarTransicao(nova);};
                }
            };
        });
        afn.getTransicoes().removeAll(toRemove);
        afn.removerInacessiveis();
        afn.removerInuteis();

        return afn;
    }

    @Override
    public boolean isInicial(Estado estado) {
        return this.estadosIniciais.contains(estado);
    }

    public void adicionarInicial(Estado estado) {
        this.estadosIniciais.add(estado);
    }

    public AFN concatenarAutomatos(AFN afn2) throws CloneNotSupportedException {
        AFN afnConcatenado = (AFN) this.clone();
        afnConcatenado.getEstados().addAll(afn2.getEstados());
        afnConcatenado.getTransicoes().addAll(afn2.getTransicoes());
        afnConcatenado.getAlfabeto().addAll(afn2.getAlfabeto());
        afnConcatenado.getEstadosFinais().forEach(estadoFinal -> {
            afn2.getEstadosIniciais().forEach(inicial -> {
                Transicao transicao = new Transicao("\u03BB",estadoFinal,inicial);
                afnConcatenado.adicionarTransicao(transicao);
            });
        });
        
        afnConcatenado.getEstadosFinais().clear();
        afnConcatenado.getEstadosFinais().addAll(afn2.getEstadosFinais());
        
        return afnConcatenado;
    }
    
    public AFN unirAutomatos(AFN afn2) throws CloneNotSupportedException {
        AFN afnUnido = (AFN) this.clone();
        
        Estado estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        
        afnUnido.getEstadosIniciais().forEach(inicial -> {
        	Transicao transicao = new Transicao("\u03BB",estadoInicial,inicial);
        	afnUnido.adicionarTransicao(transicao);
        });
        
        afn2.getEstadosIniciais().forEach(inicial -> {
        	Transicao transicao = new Transicao("\u03BB",estadoInicial,inicial);
        	afnUnido.adicionarTransicao(transicao);
        });
        
        afnUnido.getEstadosFinais().forEach(last ->{
        	Transicao transicao = new Transicao("\u03BB",last, estadoFinal);
        	afnUnido.adicionarTransicao(transicao);
        });
        
        afn2.getEstadosFinais().forEach(last ->{
        	Transicao transicao = new Transicao("\u03BB",last, estadoFinal);
        	afnUnido.adicionarTransicao(transicao);
        });
        
        afnUnido.getEstados().addAll(afn2.getEstados());
        afnUnido.getTransicoes().addAll(afn2.getTransicoes());
        afnUnido.getAlfabeto().addAll(afn2.getAlfabeto());
        
        afnUnido.getEstadosIniciais().clear();
        afnUnido.getEstados().add(estadoInicial);
        afnUnido.getEstadosIniciais().add(estadoInicial);
        
        afnUnido.getEstadosFinais().clear();
        afnUnido.getEstados().add(estadoFinal);
        afnUnido.getEstadosFinais().add(estadoFinal);
        
        return afnUnido;
    }
    
    public AFN adicionandoFechoDeKleene() throws CloneNotSupportedException{
    	AFN afnKleene= (AFN) this.clone();
    	
    	afnKleene.getEstadosFinais().forEach(last ->{
    		afnKleene.getEstadosIniciais().forEach(inicial -> {
                Transicao transicao = new Transicao("\u03BB",last,inicial);
                afnKleene.adicionarTransicao(transicao);
            });
        });
    	
    	afnKleene.getEstadosFinais().clear();
    	afnKleene.getEstadosFinais().addAll(afnKleene.getEstadosIniciais());
    	
    	return afnKleene;
    }

    @Override
    public void definicaoFormal() {
        if(this.getTransicoes().stream().anyMatch(transicao -> {
                    return transicao.getCaracter().equals("λ");
                })){
            System.out.println("========AFN \u03BB========:");
        } else {System.out.println("========AFN========:");}
        String formato = "Definição formal: ({";
        for (Estado estado : getEstados()) {
            formato = formato +" "+estado.getRotulo();
        }
        formato = formato +" }, {";
        for (Character character : alfabeto) {
            formato = formato +" |"+character+"|";
        }
        formato = formato +" }, \u03B4, {";
        for (Estado inicial : getEstadosIniciais()) {
            formato = formato +" "+inicial.getRotulo();
        }
        formato = formato +" }, {";
        for (Estado terminal : getEstadosFinais()) {
            formato = formato +" "+terminal.getRotulo();
        }
        formato = formato+" })";
        System.out.println(formato);
        System.out.println("Transições:");
        getTransicoes().forEach(transicao -> System.out.println(
                transicao.getOrigem().getRotulo() + " >>===== " +
                        transicao.getCaracter() + " =====>> " +
                        transicao.getDestino().getRotulo()
        ));
    }


}
