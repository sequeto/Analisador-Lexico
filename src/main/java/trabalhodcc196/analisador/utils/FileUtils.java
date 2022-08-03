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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	private String output = "output.txt";

	public void setOutput(String output) {
		this.output = output;
	}

	public void escreverArquivo(String divisaoTags, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + output));
		writer.write(divisaoTags);
		writer.close();
	}
	
	public List<String[]>  lerArquivoLex(HashMap<String, String> listaTags, String input) throws Exception {
		Path path = Paths.get("files/" + input);
		IOUtils cli = new IOUtils();
        List<String> linhasArquivo = Files.readAllLines(path);
		List<String[]> tags = new ArrayList<>();
        for (int i = 0; i < linhasArquivo.size(); i++) {
        	String [] linha = cli.getInput(linhasArquivo.get(i));
			tags.add(linha);
		}
		return tags;
	}

	public String lerArquivoTxt(String input) throws Exception {
		Path path = Paths.get("files/" + input);
		IOUtils cli = new IOUtils();

		return Files.readString(path);
	}
	
	public void salvarTags (HashMap<String,String> listaTags, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + output));
		
		for (Map.Entry<String, String> tags : listaTags.entrySet()) {
		     writer.write(tags.getKey()+": "+tags.getValue()+"\n");
		}
		writer.close();
	}

	public void salvarArquivoTxt(String processInput) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + this.output));
		writer.write(processInput);
		writer.close();
	}

}
