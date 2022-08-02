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
import trabalhodcc196.analisador.model.AFD;
import trabalhodcc196.analisador.model.ListWords;
import trabalhodcc196.analisador.model.Regex;
import trabalhodcc196.analisador.model.Word;
import trabalhodcc196.analisador.utils.IOUtils;

public class TagsProcess {
	public static List<Regex> tags = new ArrayList<>();
	private Regex expression = null;
	public static List<ListWords> listWords = new ArrayList<>();
	public static String [] wordsAux;
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	
	public List<Regex> getTags() {
		return tags;
	}

	public void setTags(List<Regex> tags) {
		TagsProcess.tags = tags;
	}

	public void saveTags(String[] comand, HashMap<String, String> listaTags, List<AFD> afds) throws Exception {
		if(comand.length > 2) {
			throw new InputErrorException("Processamento impossível para a entrada informada");
		}
		if (listaTags.isEmpty()) {
			try {
				expression = new Regex(comand[1], comand[0].replace(":", ""));
				AFD afd = expression.getAFD();
				if(!afd.equals(null)) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);
					afds.add(afd);
				}
			} catch (Exception e) {
				throw new InputErrorException("Processamento impossível para a entrada informada");
			}

		} else {
			for (Map.Entry<String, String> tags : listaTags.entrySet()) {
				if (tags.getKey().equalsIgnoreCase(comand[0].replace(":", ""))) {
					throw new Exception("Tag já existente: "+comand[0]);
				}
			}

			try {
				Regex expression = new Regex(comand[1], comand[0].replace(":", ""));
				AFD afd = expression.getAFD();
				if(!afd.equals(null)) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);
					afds.add(afd);
				}
				else {
					throw new InputErrorException("Tag Inválida: "+comand[0]);
				}
			}
			catch(InputErrorException e) {
				throw new InputErrorException("Tag Inválida: "+comand[0]);
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
	
	public void processInput(String input) throws Exception {
		TagsProcess.listWords =  new ArrayList<>();
		ListWords tagsDefined = null;
		Integer index = 0;
		
		getAllSubtrings(input, 0, "", TagsProcess.wordsAux);
		
		TagsProcess.listWords.forEach(list ->{ // Lista de Palavras
			list.getWords().forEach(word -> {
				for(int j = 0; j < TagsProcess.tags.size(); j++) {
					
					if(TagsProcess.tags.get(j).getAFD().recognizeWord(word.getWord())) {
						list.incrementTags();
						word.setTag(TagsProcess.tags.get(j).getLabel());
						break;
					}
				}
			});
		});
		
		for(int j= 0; j < TagsProcess.listWords.size(); j++) {
			if(TagsProcess.listWords.get(j).getTagsRecognize().equals(TagsProcess.listWords.get(j).getWords().size())) {
				if(TagsProcess.listWords.get(index).getTagsRecognize().equals(TagsProcess.listWords.get(index).getWords().size())) {
					if(TagsProcess.listWords.get(j).getTagsRecognize() < TagsProcess.listWords.get(index).getTagsRecognize()) {
						index = j;
					}
				}
				
				else {
					index = j;
				}
			}
			
			else {
				if(!TagsProcess.listWords.get(index).getTagsRecognize().equals(TagsProcess.listWords.get(index).getWords().size())) {
					if(TagsProcess.listWords.get(j).getTagsRecognize() > TagsProcess.listWords.get(index).getTagsRecognize()) {
						index = j;
					}
				}
			}
		}
		
		
		tagsDefined = TagsProcess.listWords.get(index);
		
		tagsDefined.getWords().forEach(word -> {
			if(word.getTag() != null) {
				System.out.print(word.getWord());
				System.out.print(": ");
				System.out.println(word.getTag());				
			}
			
			else {
				System.out.print(word.getWord());
				System.out.print(": ");
				System.out.println("Palavra Não Reconhecida");
			}
		});
	}
	
	public static void getAllSubtrings(String s, int i, String out, String [] wordsAux) throws Exception
	{
		// base case
		if (s == null || s.length() == 0) {
			return;
		}

		if (i == s.length()) {
			wordsAux = out.split(" ");
			List<Word> wordsList = new ArrayList<>();
			
			for(int j=0; j< wordsAux.length; j++) {
				wordsList.add(new Word(wordsAux[j]));
			}
			ListWords wordlistList = new ListWords(wordsList);
			listWords.add(wordlistList);
		}

		for (int j = s.length() - 1; j >= i; j--)
		{
			String substr = s.substring(i, j + 1) + " ";
			getAllSubtrings(s, j + 1, out + substr, wordsAux);
		}
	}
	
}
