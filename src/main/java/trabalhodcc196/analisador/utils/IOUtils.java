package trabalhodcc196.analisador.utils;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class IOUtils {
	private Scanner input;
	private PrintStream output;	
	
	public IOUtils(Scanner input, PrintStream output) {
		this.input = input;
		this.output = output;
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
	
	public Integer waitInt() {
		return this.input.nextInt();
	}
	
	public String[] getInput(String input){
		String [] inputString = input.split(" ");		
		return inputString;	
	}
	
	public String ask(String question, String[] options) {
		write(question);
		for (int i = 0; i < options.length; i++) {
			info(i + 1 + " : " + options[i]);
		}	
		return read();
	}
	
	
}
