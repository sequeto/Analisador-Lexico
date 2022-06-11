package trabalhodcc196.analisador;

import java.util.Scanner;

import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class UserInteraction {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileUtils = new FileUtils();
	
	public static void readInput(String input) throws Exception{
		// Construir Módulos e Funções para cada Comando
		if(input.charAt(0) == ':') {
			switch(input.substring(0, 1)) {
				case ":d":
					cli.warning("Realiza a divisão em tags da string do arquivo informado"); // :d input.txt
					cli.warning("Comando ainda não implementado."); 
					break;
				case ":c":
					cli.warning("Carrega um arquivo com definições de tags"); // :c tags.lex
					fileUtils.lerListaDeTagsDeArquivo(input.substring(2).trim());
					break;
				case ":o":
					cli.warning("Especifica o caminho do arquivo de saída para a divisão de tags"); // :o output.txt
					fileUtils.definirCaminhoSaida(input.substring(2).trim());
					break;
				case ":p":
					cli.warning("Realiza a divisão em tags da entrada informada"); // :p x=1037
					cli.warning("Comando ainda não implementado.");
					break;
				case ":a":
					cli.warning("Lista as definições formais dos autômatos em memória"); // :a
					break;
				case ":l":
					cli.warning("Lista as definições de tag válidas"); // :l
					break;
				case ":s":
					cli.warning("Salvar as tags"); // :s file.txt
					break;
				default:
					cli.warning("Comando não encontrado");
			}
		}
		
		// Validar regras para definição da tag - Seção 2.1 da Especificação
		else {
			cli.warning("Definindo Tag");
		}
		
	}
}
