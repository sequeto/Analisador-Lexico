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
		while(true) {
			input = cli.read();
			cli.info("Especifique uma TAG ou digite um comando:");
			if(input.equals(":q")) {
				break;				
			}
			else {
				try {
					UserInteraction.readInput(input, listaTags);
				} catch (Exception e) {
					cli.error(e.getMessage());
				}
			}
			
		}
		
		cli.info("Fim do Programa");
	}

}
