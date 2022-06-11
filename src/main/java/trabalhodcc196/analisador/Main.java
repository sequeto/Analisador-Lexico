package trabalhodcc196.analisador;

import java.util.Scanner;

import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class Main {
	
	public static FileUtils fileCli;
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static Scanner scanner = new Scanner(System.in);
	public static UserInteraction userInteraction = new UserInteraction();

	public static void main(String[] args) {
		String input;
		// Criando Loop de Interação do Usuário
		while(true) {
			cli.info("Especifique uma TAG ou digite um comando:");
			input = cli.read();
			
			if(input.equals(":q")) {
				break;				
			}
			
			else {
				UserInteraction.readInput(input);
			}
			
		}
		
		cli.info("Fim do Programa");
	}

}
