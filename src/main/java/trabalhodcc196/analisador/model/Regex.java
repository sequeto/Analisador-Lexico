package trabalhodcc196.analisador.model;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import java.util.Stack;

public class Regex {
	private String label;
	private String expression;
	private AFD AFD;
	Stack<AFN> processStack;

	public Regex(String regex, String label) throws InputErrorException {
		this.label = label;
		this.expression = regex;
		try {
			this.setAFD(this.generateAFD());			
		}catch(Exception e) {
			this.setAFD(null);
		}
	
	}
	
	public AFD getAFD() {
		return AFD;
	}

	public void setAFD(AFD aFD) {
		AFD = aFD;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRegex() {
		return expression;
	}

	public void setRegex(String regex) {
		this.expression = regex;
	}
	
	public AFN processingConcatenation(AFN primeiraPosicao, AFN segundaPosicao) throws InputErrorException {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.concatenarAutomatos(segundaPosicao);			
		}
		catch(Exception e) {
			throw new InputErrorException(String.format("Erro na Concatenação de Automatos"));
		}
		
		return afn;
	}
	
	private AFN processingUnion(AFN primeiraPosicao, AFN segundaPosicao) throws InputErrorException {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.unirAutomatos(segundaPosicao);			
		}
		catch(Exception e) {
			throw new InputErrorException(String.format("Erro na União de Automatos"));
		}
		
		return afn;
	}
	
	private AFN adicionandoFechoDeKleene(AFN primeiraPosicao) throws InputErrorException {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.adicionandoFechoDeKleene();			
		}
		catch(Exception e) {
			throw new InputErrorException(String.format("Erro na Adição do Fecho de Kleene"));
		}
		
		return afn;
	}
	
	public AFD generateAFD() throws InputErrorException {
		try {
			AFN afn = this.regexToAfn();
			afn = afn.afnLambdaToAfn();
			afn.definicaoFormal();
			AFD afd = afn.toAFD();
			afd.definicaoFormal();
			
			return afd;
		}
		catch(InputErrorException e) {
			throw new InputErrorException(String.format("Erro na definição da tag! %s", e.getMessage() !=null ? e.getMessage() : ""));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new InputErrorException(String.format("Erro na definição da tag! %s", e.getMessage() !=null ? e.getMessage() : ""));
		}	
	}
		
	public AFN regexToAfn() throws InputErrorException {
		this.processStack = new Stack<AFN>();
		AFN primeiraPosicao = new AFN();
		AFN segundaPosicao = new AFN();
		
		for(int i = 0; i < this.expression.length(); i++) {
			
			if(this.expression.charAt(i) == '+') {
				if(processStack.size() < 2) {
					throw new InputErrorException("Tag Inválida - União");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processStack.push(processingUnion(primeiraPosicao, segundaPosicao));
					processStack.peek().definicaoFormal(); // Teste
				}
			}
			
			else if(this.expression.charAt(i) == '.') {
				if(processStack.size() < 2) {
					throw new InputErrorException("Tag Inválida - Concatenação");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processStack.push(processingConcatenation(primeiraPosicao, segundaPosicao));
					processStack.peek().definicaoFormal(); // Teste
				}
			}
			
			else if(this.expression.charAt(i) == '*') {
				if(processStack.size() == 0) {
					throw new InputErrorException("Tag Inválida - Fecho de Kleene");
				}
				
				else {
					primeiraPosicao = processStack.pop(); 
					processStack.push(adicionandoFechoDeKleene(primeiraPosicao));
					processStack.peek().definicaoFormal(); // Teste
				}
			}
			
			else {
				processStack.push(new AFN(this.expression.charAt(i)));
				processStack.peek().definicaoFormal(); // Teste
			}
			
		}
		
		if(processStack.size() != 1) {
			throw new InputErrorException("Tag Inválida");
		}
		
		else {			
			return processStack.pop();
		}
	}

	
}
