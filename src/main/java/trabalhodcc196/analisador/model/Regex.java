package trabalhodcc196.analisador.model;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import java.util.Stack;

public class Regex {
	private String expression;
	Stack<AFN> processStack;

	public Regex(String regex) {
		super();
		this.expression = regex;
	}

	public String getRegex() {
		return expression;
	}

	public void setRegex(String regex) {
		this.expression = regex;
	}
	
	public AFN processingConcatenation(AFN afnInicial, AFN afnFinal) {
		AFN afn = new AFN();
		
		
		return afn;
		
	}
	
	private void processingUnion(AFN primeiraPosicao, AFN segundaPosicao) {
		// TODO Auto-generated method stub
		
	}
		
	public AFN regexToAfn() throws InputErrorException {
		this.processStack = new Stack<AFN>();
		AFN afn = new AFN();
		AFN primeiraPosicao = new AFN();
		AFN segundaPosicao = new AFN();
		
		for(int i = 0; i < this.expression.length(); i++) {
			if(this.expression.charAt(i) == '+' || this.expression.charAt(i) == '.') {
				if(processStack.size() < 2) {
					throw new InputErrorException("Tag Inválida - União");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processingUnion(primeiraPosicao, segundaPosicao);
				}
			}
			processStack.push(afn);
			
			afn = processStack.pop();
		}
		
		return afn;
	}

	
}
