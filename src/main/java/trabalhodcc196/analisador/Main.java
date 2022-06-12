package trabalhodcc196.analisador;

import java.util.Scanner;

import trabalhodcc196.analisador.model.ListaTags;
import trabalhodcc196.analisador.utils.IOUtils;

public class Main {
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static UserInteraction userInteraction = new UserInteraction();

	public static void main(String[] args) {
		String input;
		ListaTags listaTags = new ListaTags();
		// Criando Loop de Interação do Usuário
		cli.info("Especifique uma TAG ou digite um comando:");
		while(true) {
			input = cli.read();
			
			if(input.equals(":q")) {
				break;				
			}
			else {
				try {
					UserInteraction.readInput(input, listaTags.lsTags);
				} catch (Exception e) {
					cli.error("Digite uma tag ou um comando:");
				}
			}
			
		}
		
		cli.info("Fim do Programa");
	}

}
