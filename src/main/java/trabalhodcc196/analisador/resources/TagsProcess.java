/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */

package trabalhodcc196.analisador.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.model.AFD;
import trabalhodcc196.analisador.model.AFN;
import trabalhodcc196.analisador.model.Regex;
import trabalhodcc196.analisador.utils.IOUtils;

public class TagsProcess {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);

	public void saveTags(String[] comand, HashMap<String, String> listaTags) throws Exception {
		if(comand.length > 2) {
			throw new InputErrorException("Processamento impossível para a entrada informada");
		}
		if (listaTags.isEmpty()) {
			try {
//				validarExpressaoRegular(comand[1]);
//				gerarAFD(comand[1]);
				listaTags.put(comand[0].replace(":", ""), comand[1]);
			} catch (Exception e) {
				throw new InputErrorException("Processamento impossível para a entrada informada");
			}

		} else {
			for (Map.Entry<String, String> tags : listaTags.entrySet()) {

				if (tags.getKey().equalsIgnoreCase(comand[0].replace(":", ""))) {
					throw new Exception("Tag já existente.");
				}

			}

			try {
//				validarExpressaoRegular(comand[1]);
//				gerarAFD(comand[1]);
				listaTags.put(comand[0].replace(":", ""), comand[1]);
			} catch (Exception e) {
				throw new InputErrorException();
			}
		}
	}

//	public Boolean validarExpressaoRegular(String tag){
//		Stack<String> pilha = new Stack<String>();
//		Stack<Character> pilhaProcessamento = new Stack<Character>();
//		for(char c : tag.toCharArray()){
//			if(c == '*') {
//
//
//			} else if (c == '+') {
//				if(pilhaProcessamento.size() > 2) return false;
//				Character char1 = pilhaProcessamento.pop();
//				if(pilhaProcessamento.size() == 0) {
//					pilha.push(String.valueOf(char1));
//				} else {
//					Character char2 = pilhaProcessamento.pop();
//					pilha.push(char2 + "+" + char1);
//				}
//			} else {
//				pilhaProcessamento.push(c);
//			}
//		}
//
//		return null;
//	}
	
	public AFD gerarAFD(String tag) throws InputErrorException, CloneNotSupportedException{
		try {
			Regex expression = new Regex(tag);
			cli.info(expression.getRegex());
			AFN afn = expression.regexToAfn();
			afn = afn.afnLambdaToAfn();
			afn.mostrarAutomato();
//			AFD afd = afn.toAFD();
//			afd.mostrarAutomato();
//			return afd;
		}
		catch(InputErrorException e) {
			cli.error(String.format("Erro na definição da tag! %s", e.getMessage() !=null ? e.getMessage() : ""));
			return null;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
		
	}
	
}
