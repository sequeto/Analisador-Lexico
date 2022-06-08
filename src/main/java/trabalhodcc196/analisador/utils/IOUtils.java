package trabalhodcc196.analisador.utils;

import java.io.PrintStream;
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
	
	public String read() {	
		String content = this.input.nextLine();
		return content;
	}
	
	public Integer waitInt() {
		return this.input.nextInt();
	}
	
	public String ask(String question, String[] options) {
		write(question);
		for (int i = 0; i < options.length; i++) {
			write(i + 1 + " : " + options[i]);
		}	
		return read();
	}
	
	public void error(String error) { 
		this.output.println("Erro! " + error);
	}
}
