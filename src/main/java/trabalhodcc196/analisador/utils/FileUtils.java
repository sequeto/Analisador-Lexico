package trabalhodcc196.analisador.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import trabalhodcc196.analisador.model.Tag;

@NoArgsConstructor
public class FileUtils {

	private String output;

	public static String criarStringDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader("files/" + nomeArquivo));
		String stringLida = "";
		String readLine = reader.readLine();
		while (readLine != null) {
			stringLida = stringLida + readLine + "\n";
			readLine = reader.readLine();
		}
		if (!stringLida.equals("")) {
			stringLida = stringLida.substring(0, stringLida.lastIndexOf('\n'));
		}
		reader.close();
		return stringLida;
	}

	public void escreverEmArquivo(String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("output/" + this.output));
		writer.write(content);
		writer.close();
	}

	public void definirCaminhoSaida(String output) {
		this.output = output;
	}
	
	public List<Tag> lerListaDeTagsDeArquivo(String input) throws ClassNotFoundException, IOException{
		List<Tag> lista = new ArrayList();
		File file = new File(input);
		
		if (file.exists()) {
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
			lista = (List<Tag>) objInput.readObject();
			objInput.close();
		}	

		return (lista);
	}
	
}
