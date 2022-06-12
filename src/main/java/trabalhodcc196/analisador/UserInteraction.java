package trabalhodcc196.analisador;

import java.util.Scanner;

import trabalhodcc196.analisador.model.ListaTags;
import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class UserInteraction {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileUtils = new FileUtils();
	
	// Módulos e Funções de cada comando
	
	public static void readInput(String input) throws Exception{
		String [] comand = cli.getInput(input);
		ListaTags listaTags = new ListaTags();
		if(comand[0].charAt(0) == ':') {
			
			// Faz um switch entre os comandos
			
			switch(comand[0]) {
				case ":d":
					cli.warning("Realiza a divisao em tags da string do arquivo informado"); // :d input.txt
					//String leitura = fileUtils.criarStringDoArquivo(comand[1]);
					cli.warning("Comando ainda nao implementado.");
					break;
//				case ":c":
//					cli.warning("Carrega um arquivo com definicoes de tags"); // :c tags.lex
//					List<Tag> string = fileUtils.lerArquivoDefinicaoDeTags(comand[1]);
//					for(int i=0; i<string.size(); i++) 
//					{
//						System.out.println(string.get(i));
//					}
//					break;
				case ":o":
					cli.warning("Especifica o caminho do arquivo de saída para a divisão de tags"); // :o output.txt
//					fileUtils.definirCaminhoSaida(comand[1]);
//					fileUtils.escreverArquivo("Teste");
//					cli.info("Arquivo criado dentro da pasta 'files'.");
					cli.warning("Comando ainda nao implementado.");
					break;
				case ":p":
					cli.warning("Realiza a divisão em tags da entrada informada"); // :p x=1037
					cli.warning("Comando ainda nao implementado.");
					break;
//				case ":a":
//					cli.warning("Lista as definições formais dos autômatos em memória"); // :a
//					break;
//				case ":l":
//					cli.warning("Lista as definições de tag válidas"); // :l
//					break;
				case ":s":
					cli.warning("Salvar as tags"); // :s file.txt
					fileUtils.definirCaminhoSaida("tags.txt");
					fileUtils.salvarTags(listaTags.lsTags);
					cli.info("Arquivo criado dentro da pasta 'files'.");
					break;
				default:
					cli.warning("Comando nao encontrado");
			}
		}
		// Validar regras para definição da tag - Seção 2.1 da Especificação
		else {
			cli.warning("Definindo Tag");
		}
		
	}
}
