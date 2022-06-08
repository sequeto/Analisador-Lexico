package trabalhodcc196.analisador;

import java.io.IOException;
import java.util.Scanner;

import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class Main {
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileCli;

	public static void main(String[] args) {
		cli.write("Digite o nome do arquivo, que deverá estar dentro da pasta files do projeto.");
		String stringAnalise = "";
		try {
			stringAnalise = fileCli.criarStringdoArquivo(cli.read());

		} catch (IOException ioe) {
			 cli.error(ioe.getMessage());
		}
		
		cli.write("Fim do programa");
	}

}
