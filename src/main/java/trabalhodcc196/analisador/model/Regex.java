package trabalhodcc196.analisador.model;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;



public class Regex {
	private String expression;
	Stack<AFN> processStack;
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);

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
	
	public AFN processingConcatenation(AFN primeiraPosicao, AFN segundaPosicao) {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.concatenarAutomatos(segundaPosicao);			
		}
		catch(Exception e) {
			cli.error(String.format("Erro na Concatenação de Automatos"));
		}
		
		return afn;
	}
	
	private AFN processingUnion(AFN primeiraPosicao, AFN segundaPosicao) {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.unirAutomatos(segundaPosicao);			
		}
		catch(Exception e) {
			cli.error(String.format("Erro na União de Automatos"));
		}
		
		return afn;
	}
	
	private AFN adicionandoFechoDeKleene(AFN primeiraPosicao) {
		AFN afn = primeiraPosicao;
		
		try {
			afn = primeiraPosicao.adicionandoFechoDeKleene();			
		}
		catch(Exception e) {
			cli.error(String.format("Erro na Adição do Fecho de Kleene"));
		}
		
		return afn;
	}
		
	public AFN regexToAfn() throws InputErrorException {
		this.processStack = new Stack<AFN>();
		AFN afn = new AFN();
		AFN primeiraPosicao = new AFN();
		AFN segundaPosicao = new AFN();
		
		for(int i = 0; i < this.expression.length(); i++) {
			
			if(this.expression.charAt(i) == '+') {
				if(processStack.size() != 2) {
					throw new InputErrorException("Tag Inválida - União");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processStack.push(processingUnion(primeiraPosicao, segundaPosicao));
				}
			}
			
			else if(this.expression.charAt(i) == '.') {
				if(processStack.size() != 2) {
					throw new InputErrorException("Tag Inválida - Concatenação");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processStack.push(processingConcatenation(primeiraPosicao, segundaPosicao));
				}
			}
			
			else if(this.expression.charAt(i) == '*') {
				if(processStack.size() != 1) {
					throw new InputErrorException("Tag Inválida - Fecho de Kleene");
				}
				
				else {
					segundaPosicao = processStack.pop();
					primeiraPosicao = processStack.pop(); 
					processStack.push(adicionandoFechoDeKleene(primeiraPosicao));
				}
			}
			
			else {
				if(processStack.size() > 2) {
					throw new InputErrorException("Tag Inválida - Ordem de Operandos Inválidos");
				}
				
				else {
					processStack.push(new AFN(this.expression.charAt(i)));
				}
			}
			
		}
		
		return processStack.pop();
	}

	
}
