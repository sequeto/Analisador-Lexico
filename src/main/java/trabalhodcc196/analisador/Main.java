/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */


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
			cli.write("Especifique uma TAG ou digite um comando:");
			input = cli.read();
			if(input.equals(":q")) {
				break;				
			}
			else {
				try {
					UserInteraction.readInput(input, listaTags);
				} catch (Exception e) {
					cli.error(e.getMessage());
					e.printStackTrace();
				}
			}
			
		}
		
		cli.info("Fim do Programa");
	}

}
