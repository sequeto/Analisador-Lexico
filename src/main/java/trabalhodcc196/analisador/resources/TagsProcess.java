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

import trabalhodcc196.analisador.exceptions.AutomataProcessingException;
import trabalhodcc196.analisador.model.AFD;
import trabalhodcc196.analisador.model.Regex;
import trabalhodcc196.analisador.model.Word;
import trabalhodcc196.analisador.utils.IOUtils;

import java.util.*;

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

	public void saveTags(String[] comand, HashMap<String, String> listaTags, List<AFD> afds) throws Exception {

		if (listaTags.isEmpty()) {
			try {
				expression = new Regex(comand[1], comand[0].replace(":", ""));
				AFD afd = expression.getAFD();
				if(afd != null) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);
					afds.add(afd);
				} else {
					throw new AutomataProcessingException("Tag Inválida: "+comand[0].replace(":", ""));
				}
			} catch (AutomataProcessingException e) {
				throw new AutomataProcessingException("Processamento impossível para a entrada informada");
			} catch (Exception e) {
				throw new Exception();
			}

		} else {
			for (Map.Entry<String, String> tags : listaTags.entrySet()) {
				if (tags.getKey().equalsIgnoreCase(comand[0].replace(":", ""))) {
					throw new Exception("Tag já existente: "+comand[0].replace(":", ""));
				}
			}

			try {
				Regex expression = new Regex(comand[1], comand[0].replace(":", ""));
				AFD afd = expression.getAFD();
				if(afd != null) {
					tags.add(expression);
					listaTags.put(comand[0].replace(":", ""), comand[1]);
					afds.add(afd);
				} else {
					throw new AutomataProcessingException("Tag Inválida: "+comand[0].replace(":", ""));
				}
			}
			catch(AutomataProcessingException e) {
				throw new AutomataProcessingException(e.getMessage());
			}
			catch (Exception e) {
				throw new Exception();
			}
		}
	}
	
	public String processInput(String input) {
		String saida = "";
		if(!input.isEmpty()) {
			List<Word> listWords = new ArrayList<>();
			String processado = "";
			Word reconhecido = null;
			Word naoReconhecido = null;
			
			boolean recognize = false;
			
			while(!input.isEmpty()) {
				reconhecido = new Word("");
				reconhecido.setTag(null);
				
				for(int i = 0; i<input.length(); i++) {
					processado = processado + Character.toString(input.charAt(i));
					for(int j = 0; j < TagsProcess.tags.size(); j++) {
						if(TagsProcess.tags.get(j).getAFD().recognizeWord(processado)) {
							recognize = true;
							reconhecido = new Word(processado);
							reconhecido.setTag(TagsProcess.tags.get(j).getLabel());
							break;
						}
					}
					
					if(!recognize) {
						reconhecido.setWord(processado);
						recognize = true;
					}
				}
				
				
				if(reconhecido.getTag() == null) {
					if(!listWords.isEmpty() && listWords.get((listWords.size()-1)).getTag() == null) {
						naoReconhecido = new Word(listWords.get((listWords.size()-1)).getWord() + reconhecido.getWord());
						naoReconhecido.setTag(null);
						listWords.remove((listWords.size()-1));
						listWords.add(naoReconhecido);
					}
					
					else {
						listWords.add(reconhecido);
					}
				}
				
				else {
					listWords.add(reconhecido);					
				}
				
				input = input.substring(reconhecido.getWord().length());
				processado = "";
				recognize = false;
			}
			
			for (Word word : listWords){
				if(word.getTag() != null) {
					saida = saida + word.getWord() + ": " + word.getTag()+"\n";
					System.out.print(word.getWord());
					System.out.print(": ");
					System.out.println(word.getTag());
				}
				
				else {
					saida = saida + word.getWord() + ": "+ "Palavra Não Reconhecida\n";
					System.out.print(word.getWord());
					System.out.print(": ");
					System.out.println("Palavra Não Reconhecida");
				}
			};
		
		
		}
		return saida;
	}
	
}
