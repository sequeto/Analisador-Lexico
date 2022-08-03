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
import java.nio.file.InvalidPathException;
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
	
	public List<String[]>  lerArquivoLex(HashMap<String, String> listaTags, String input) throws Exception {
		try {
			Path path = Paths.get("files/" + input);
			IOUtils cli = new IOUtils();
			List<String> linhasArquivo = Files.readAllLines(path);
			List<String[]> tags = new ArrayList<>();
			for (int i = 0; i < linhasArquivo.size(); i++) {
				String [] linha = cli.getInput(linhasArquivo.get(i));
				tags.add(linha);
			}
			return tags;
		} catch (Exception e) {
			throw new IOException("Não foi possível a leitura do arquivo .lex.");
		}
	}

	public String lerArquivoTxt(String input) throws IOException {

		try {
			Path path = Paths.get("files/" + input);
			IOUtils cli = new IOUtils();

			return Files.readString(path);
		} catch (IOException | InvalidPathException e) {
			throw new IOException("Não foi possível carregar string do arquivo .txt.");
		}
	}
	
	public void salvarTags (HashMap<String,String> listaTags, String output) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + output));

			for (Map.Entry<String, String> tags : listaTags.entrySet()) {
				 writer.write(tags.getKey()+": "+tags.getValue()+"\n");
			}
			writer.close();
		} catch (IOException | InvalidPathException e) {
			throw new IOException("Erro ao criar arquivo com as tags");
		}
	}

	public void salvarArquivoTxt(String processInput) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + this.output));
			writer.write(processInput);
			writer.close();
		} catch (IOException | InvalidPathException e) {
			throw new IOException("Erro ao salvar arquivo txt.");
		}
	}

}
