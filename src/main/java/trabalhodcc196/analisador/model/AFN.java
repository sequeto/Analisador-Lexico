package trabalhodcc196.analisador.model;

import java.util.*;
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
        Set<Estado> processados = new HashSet<>();
        processados.add(inicialAFD);
        Estado estadoAtual = null;
        while(!processados.isEmpty()) {
            estadoAtual = processados.stream().findFirst().orElse(null);
            processados.add(estadoAtual);
            Set<Estado> origens = Arrays.stream(estadoAtual.getRotulo().split(","))
                    .map(str -> new Estado(str)).
                    collect(Collectors.toSet());
   
            
            for(Character caracter: this.alfabeto) {
                List<Estado> destinos = new ArrayList<>();
                Boolean isFinal = false;
                for (Estado origem : origens) {
                    destinos.addAll(getDestinosByOrigemByCaracter(origem, caracter));
                    if(isFinal(origem)){
                    	isFinal = true;
                	}
                    
                    if(isFinal(origem) && !afd.getEstadosFinais().contains(origem)){
                    	afd.adicionarFinal(origem);
                	}
                }
                
                destinos = destinos.stream().distinct().collect(Collectors.toList());
                
                if(destinos.size()!= 0){
                    Estado novoEstado = montarEstadoAFD(destinos);
                    Transicao novaTransicao = new Transicao(caracter.toString(),estadoAtual,novoEstado);
                    if(!afd.getTransicoes().contains(novaTransicao)){
                    	afd.adicionarTransicao(novaTransicao);
                    }
                    if (!estadoAtual.equals(novoEstado) && (!processados.contains(novoEstado))){
                        processados.add(novoEstado);
                    }
                    if(!afd.getEstados().contains(novoEstado)){
                    	afd.adicionarEstado(novoEstado);
                    }
                    
                    if(isFinal && !afd.getEstadosFinais().contains(novoEstado)){
                        afd.adicionarFinal(novoEstado);
                    }
                }
            };

            processados.remove(estadoAtual);
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
        List<Transicao> transicoesLambda = afn.getTransicoes().stream()
                .filter(transicao -> transicao.getCaracter().equals("\u03BB"))
                .collect(Collectors.toList());
        
        transicoesLambda.forEach(transicaoLambda -> {
        	
        	if(isInicial(transicaoLambda.getOrigem())){
                adicionarInicial(transicaoLambda.getDestino());
            }
        	
            List<Estado> origens = getOrigensByDestino(transicaoLambda.getOrigem());
            
            origens.forEach(origem -> {
                List<Transicao> transicoesAnteriores= getTransicoesByOrigemByDestino(origem,transicaoLambda.getOrigem());
                for (Transicao anterior : transicoesAnteriores){
                    Transicao nova = new Transicao(anterior.getCaracter(), anterior.getOrigem(),transicaoLambda.getDestino());
                    adicionarTransicao(nova);
                    removerTransicao(anterior);
                }
            });
            
            removerTransicao(transicaoLambda);
        });
        
        afn.setTransicoes(afn.getTransicoes()
                .stream()
                .sorted(Comparator.comparing(transicao -> {return transicao.getOrigem().getRotulo();}))
                .collect(Collectors.toList()));
        
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
    public void mostrarAutomato() {
        System.out.println("========AFN========:");
        System.out.println("Estados:");
        getEstados().forEach(estado -> System.out.println(estado.getRotulo() + " "));
        System.out.println("Estados iniciais:");
        estadosIniciais.forEach(iniciais -> System.out.println(iniciais.getRotulo() + " "));
        System.out.println("Estados finais:");
        getEstadosFinais().forEach(finais -> System.out.println(finais.getRotulo() + " "));
        System.out.println("Transições:");
        getTransicoes().forEach(transicao -> System.out.println(
                transicao.getOrigem().getRotulo() + " >>===== " +
                        transicao.getCaracter() + " =====>> " +
                        transicao.getDestino().getRotulo()
        ));
    }


}
