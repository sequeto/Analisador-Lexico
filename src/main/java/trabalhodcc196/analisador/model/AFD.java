package trabalhodcc196.analisador.model;

import java.util.List;

public class AFD extends Automato {
	private List<Character> alfabeto;
	private Estado estadoInicial;
	
	public AFD() {
		
	}
	
	public AFD(
			List<Estado> estados, 
			List<Estado> estadosFinais, 
			List<Transicao> transicoes, 
			List<Character> alfabeto, 
			Estado estadoInicial)
	{
		this.alfabeto = alfabeto;
	}
	
	public AFD(String expressaoRegular) {
		
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Estado> getEstadosFinais() {
		return estadosFinais;
	}

	public void setEstadosFinais(List<Estado> estadosFinais) {
		this.estadosFinais = estadosFinais;
	}

	public List<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<Transicao> transicoes) {
		this.transicoes = transicoes;
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
	
	
}
