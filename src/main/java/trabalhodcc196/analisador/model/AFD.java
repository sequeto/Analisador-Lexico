package trabalhodcc196.analisador.model;

import java.util.ArrayList;
import java.util.List;

public class AFD extends Automato {
	private List<Character> alfabeto = new ArrayList<>();
	private Estado estadoInicial;
	
	public AFD() {
		
	}

	public AFD(List<Estado> estados, List<Transicao> transicoes, List<Estado> estadosFinais, List<Character> alfabeto, List<Character> alfabeto1, Estado estadoInicial) {
		super(estados, transicoes, estadosFinais, alfabeto);
		this.alfabeto = alfabeto1;
		this.estadoInicial = estadoInicial;
	}

	public AFD(String expressaoRegular) {
		
	}

	public List<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(List<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	
	public AFD minimizaAFD(AFD AFD) {
		AFD afdMinimo = new AFD();
		
		return afdMinimo;
	}
	
	public AFD converteExpressaoRegularParaAFD(String expressaoRegular) {
		AFD afd = new AFD();
		
		return afd;
	}

	@Override
	public boolean isInicial(Estado estado) {
		return this.estadoInicial.equals(estado);
	}
	
	public boolean recognizeWord(String word) {
		List<Transicao> transicoes = this.getTransicoesByOrigem(estadoInicial);
		Estado estadoFinalizandoProcessamento = null;
		boolean foundTransition = false;
		
		for(int i = 0; i < word.length(); i++) {
			foundTransition = false;
			for(int j = 0; j < transicoes.size(); j++) {
				if(transicoes.get(j).getCaracter().equals(Character.toString(word.charAt(i)))) {
					foundTransition = true;
					estadoFinalizandoProcessamento = transicoes.get(j).getDestino();
					transicoes = this.getTransicoesByOrigem(transicoes.get(j).getDestino());
					break;
				}
			}
			
			if(!foundTransition) {
				return false;
			}
		}
		
		if(this.isFinal(estadoFinalizandoProcessamento)) {
			return true;
		}
		
		else {
			return false;
		}
	}

	@Override
	public void mostrarAutomato() {
		System.out.println("========AFD========:");
		System.out.println("Estados:");
		getEstados().forEach(estado -> System.out.println(estado.getRotulo() + " "));
		System.out.println("Estado inicial:");
		System.out.println(estadoInicial.getRotulo());
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
