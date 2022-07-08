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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	private String output;
	private String input;


	public void escreverArquivo(String divisaoTags) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + this.output));
		writer.write(divisaoTags);
		writer.close();
	}

	public void definirCaminhoSaida(String output) throws IOException {
		this.output = output;
	}
	
	public void definirCaminhoEntrada(String input) throws IOException {
		this.input = input;
	}
	
	public void lerArquivoLex(HashMap<String, String> listaTags) throws Exception {
		Path path = Paths.get("files/" + this.input);
		IOUtils cli = new IOUtils();
        List<String> linhasArquivo = Files.readAllLines(path);
        for (int i = 0; i < linhasArquivo.size(); i++) {
        	String [] linha = cli.getInput(linhasArquivo.get(i));
        	listaTags.put(linha[0].replace(":", ""), linha[1]);
		}
        
	}
	
	public void salvarTags (HashMap<String,String> listaTags, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + output));
		
		for (Map.Entry<String, String> tags : listaTags.entrySet()) {
		     writer.write(tags.getKey()+": "+tags.getValue()+"\n");
		}
		writer.close();
	}
	
}
