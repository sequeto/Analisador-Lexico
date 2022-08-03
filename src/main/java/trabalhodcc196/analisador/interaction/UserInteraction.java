/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */

package trabalhodcc196.analisador.interaction;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.model.ListaAutomatos;
import trabalhodcc196.analisador.model.ListaTags;
import trabalhodcc196.analisador.resources.TagsProcess;
import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class UserInteraction {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileUtils = new FileUtils();
	
	// Módulos e Funções de cada comando
	
	public static void readInput(String input, ListaTags listaTags, ListaAutomatos listaAutomatos) throws Exception{
		String [] comand = cli.getInput(input);
		TagsProcess process = new TagsProcess();
		if(comand[0].equals("")){
			throw new InputErrorException("Insira um comando ou tag!");
		}
		if(comand[0].charAt(0) == ':') {
			// Faz um switch entre os comandos
			cli.info("Definindo comando:");
			switch(comand[0]) {
				case ":d":
					cli.warning("Realiza a divisao em tags da string do arquivo informado"); // :d input.txt
					String leitura = fileUtils.lerArquivoTxt(comand[1]);
					fileUtils.salvarArquivoTxt(process.processInput(leitura));
					break;
				case ":c":
					cli.warning("Carrega um arquivo com definicoes de tags"); // :c tags.lex
					List<String[]> fromLex = fileUtils.lerArquivoLex(listaTags.lsTags, comand[1]);
					for (String[] comando : fromLex) {
						try {
							cli.info("Acessando tag: "+comando[0]);
							process.saveTags(comando, listaTags.lsTags, listaAutomatos.lsAutomatos);
						} catch (Exception e) {
							cli.error(e.getMessage());
						}
					}
					cli.info("Arquivo lido e carregado para a lista de tags.");
					break;
				case ":o":
					cli.warning("Especifica o caminho do arquivo de saída para a divisão de tags"); // :o output.txt
					fileUtils.setOutput(comand[1]);
					break;
				case ":p":
					cli.info("Realiza a divisão em tags da entrada informada"); // :p x = 1037
					fileUtils.salvarArquivoTxt(process.processInput(comand[1]));
					break;
				case ":a":
					cli.warning("Lista as definições formais dos autômatos em memória"); // :a
					listaAutomatos.imprimirLista();
					break;
				case ":l":
					cli.info("Lista as definiçoes de tag validas"); // :l
					listaTags.imprimirLista();	
					break;
				case ":s":
					cli.warning("Salvar as tags"); // :s file.txt
					fileUtils.salvarTags(listaTags.lsTags, comand[1]);
					cli.info("Arquivo criado dentro da pasta 'files'.");
					break;
				default:
					throw new InputErrorException("Comando nao encontrado");
			}
		}
		
		else {
			cli.info("Definindo Tag:");
			process.saveTags(comand, listaTags.lsTags, listaAutomatos.lsAutomatos);
		}
		
	}
}
