/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */


package trabalhodcc196.analisador.main;

import trabalhodcc196.analisador.exceptions.AutomataProcessingException;
import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.interaction.UserInteraction;
import trabalhodcc196.analisador.model.ListaAutomatos;
import trabalhodcc196.analisador.model.ListaTags;
import trabalhodcc196.analisador.utils.IOUtils;

import java.util.Scanner;

public class Main {
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static UserInteraction userInteraction = new UserInteraction();

	public static void main(String[] args) {
		String input;
		ListaTags listaTags = new ListaTags();
		ListaAutomatos listaAutomatos = new ListaAutomatos();
		// Criando Loop de Interação do Usuário
		while(true) {
			cli.write("Especifique uma TAG ou digite um comando:");
			input = cli.read();
			if(input.equals(":q")) {
				break;				
			}
			else {
				try {
					UserInteraction.readInput(input, listaTags, listaAutomatos);
					
				} catch (InputErrorException e) {
					cli.error(String.format("Erro na entrada digitada! %s", e.getMessage() !=null ? e.getMessage() : ""));
				} catch (AutomataProcessingException e) {
					cli.error("Erro de criação de autômato, "+e.getMessage());
				} catch (ArrayIndexOutOfBoundsException e) {
					cli.error("Comando inválido.");
				} catch (Exception e) {
					cli.error(e.getMessage());
				}
			}
		}
		
		cli.info("Fim do Programa");
	}

}
