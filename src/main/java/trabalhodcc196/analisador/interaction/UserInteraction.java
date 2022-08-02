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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.model.AFD;
import trabalhodcc196.analisador.model.ListaTags;
import trabalhodcc196.analisador.resources.TagsProcess;
import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class UserInteraction {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileUtils = new FileUtils();
	public static List<AFD> afds = new ArrayList<>();
	
	// Módulos e Funções de cada comando
	
	public static void readInput(String input, ListaTags listaTags) throws Exception{
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
					//String leitura = fileUtils.criarStringDoArquivo(comand[1]);
					cli.warning("Comando ainda nao implementado.");
					break;
				case ":c":
					cli.warning("Carrega um arquivo com definicoes de tags"); // :c tags.lex
					fileUtils.lerArquivoLex(listaTags.lsTags, comand[1]);
					cli.info("Arquivo lido e carregado para a lista de tags.");
					break;
				case ":o":
					cli.warning("Especifica o caminho do arquivo de saída para a divisão de tags"); // :o output.txt
//					fileUtils.escreverArquivo("Teste", comand[1]);
//					cli.info("Arquivo criado dentro da pasta 'files'.");
					cli.warning("Comando ainda nao implementado.");
					break;
				case ":p":
					cli.info("Realiza a divisão em tags da entrada informada"); // :p x = 1037
//					process.processInput(comand);
					process.processInput(comand[1]);
					break;
				case ":a":
					cli.warning("Lista as definições formais dos autômatos em memória"); // :a
					cli.warning("Comando ainda nao implementado.");
					break;
				case ":l":
					cli.info("Lista as definiçoes de tag validas"); // :l
					listaTags.imprimirLista();	
					break;
				case ":s":
					// validar as tags
					cli.warning("Salvar as tags"); // :s file.txt
					fileUtils.salvarTags(listaTags.lsTags, comand[1]);
					cli.info("Arquivo criado dentro da pasta 'files'.");
					break;
				default:
					cli.error("Comando nao encontrado");
			}
		}
		
		else {
			cli.info("Definindo Tag:");
			process.saveTags(comand, listaTags.lsTags);
		}
		
	}
}
