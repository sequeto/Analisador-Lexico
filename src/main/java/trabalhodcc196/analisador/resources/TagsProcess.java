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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.model.Regex;
import trabalhodcc196.analisador.utils.IOUtils;

public class TagsProcess {
	public static List<Regex> tags = new ArrayList<>();
	private Regex expression = null;
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	
	public List<Regex> getTags() {
		return tags;
	}

	public void setTags(List<Regex> tags) {
		TagsProcess.tags = tags;
	}

	public void saveTags(String[] comand, HashMap<String, String> listaTags) throws Exception {
		if(comand.length > 2) {
			throw new InputErrorException("Processamento impossível para a entrada informada");
		}
		if (listaTags.isEmpty()) {
			try {
				expression = new Regex(comand[1], comand[0].replace(":", ""));
				if(!expression.getAFD().equals(null)) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);					
				}
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
				Regex expression = new Regex(comand[1], comand[0].replace(":", ""));
				if(!expression.getAFD().equals(null)) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);					
				}
				else {
					throw new InputErrorException("Tag Inválida");
				}
			}
			catch(InputErrorException e) {
				throw new InputErrorException("Tag Inválida");
			}
			catch (Exception e) {
				throw new InputErrorException();
			}
		}
	}
	
//	public void processInput(String [] input) {
//		String word;
//		boolean recognizeWord  = false;
//		
//		for(int i = 1; i < input.length; i++) {
//			word = input[i];
//			recognizeWord  = false;
//			
//			for(int j = 0; j < TagsProcess.tags.size(); j++) {
//				recognizeWord = TagsProcess.tags.get(j).getAFD().recognizeWord(word);
//				
//				if(recognizeWord) {
//					cli.write(TagsProcess.tags.get(j).getLabel());
//					break;
//				}
//			}
//			
//			if(!recognizeWord) {
//				cli.info("Palavra não reconhecida por nenhuma tag salva!");
//			}
//		}
//	}
	
	public void processInput(String input) {
		String processado = "";
		boolean recognizeWord  = false;
		
		for(int i = 0; i < input.length(); i++) {
			processado = processado + Character.toString(input.charAt(i));
			recognizeWord  = false;
			
			for(int j = 0; j < TagsProcess.tags.size(); j++) {
				recognizeWord = TagsProcess.tags.get(j).getAFD().recognizeWord(processado);
				
				if(recognizeWord) {
					cli.write(TagsProcess.tags.get(j).getLabel());
					processado = "";
					break;
				}
			}
		}
	}
}
