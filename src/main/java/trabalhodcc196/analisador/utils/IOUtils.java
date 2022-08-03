/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */

package trabalhodcc196.analisador.utils;

import java.io.PrintStream;
import java.util.Scanner;

import trabalhodcc196.analisador.exceptions.InputErrorException;

public class IOUtils {
	private Scanner input;
	private PrintStream output;

	
	public IOUtils(Scanner input, PrintStream output) {
		this.input = input;
		this.output = output;
	}
	
	public IOUtils() {
		// construtor vazio
	}
	
	public void write(String content) {
		this.output.println(content);
	}
	
	public void info(String info) {
		this.output.println("[INFO]: "+info);
	}
	
	public void error(String error) { 
		this.output.println("[ERRO]: " + error);
	}
	
	public void warning(String warning) { 
		this.output.println("[AVISO]: " + warning);
	}
	
	public String read() {	
		String content = this.input.nextLine();
		return content;
	}

	public String[] getInput(String input) throws Exception{
		try {
			String [] inputString = input.split(" ",2);
			return inputString;	
		} catch (Exception e) {
			throw new InputErrorException ("Não foi possível processar a entrada. \n"+e.getMessage());
		}	
	}

}
