package trabalhodcc196.analisador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import trabalhodcc196.analisador.utils.FileUtils;
import trabalhodcc196.analisador.utils.IOUtils;

public class Main {
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static FileUtils fileCli;

	public static void main(String[] args) {
		cli.info("Digite o nome do arquivo, que deverá estar dentro da pasta files do projeto.");
		String stringAnalise = "";
		while(true) {
			
			try {
				stringAnalise = fileCli.criarStringDoArquivo(cli.read());
				cli.info("Carregada a string: "+stringAnalise);

			} catch (FileNotFoundException e) {
				 cli.error("Arquivo não localizado.");
				 break;
			} catch (Exception e) {
				cli.error(e.getMessage());
				break;
			}
		}
		
		
		cli.info("Fim do programa");
	}

}
