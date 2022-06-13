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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	public void escreverArquivo(String divisaoTags) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + this.output));
		writer.write(divisaoTags);
		writer.close();
	}

	public void definirCaminhoSaida(String output) throws IOException {
		this.output = output;
	}
	
//	public List<Tag> lerArquivoDefinicaoDeTags(String input) throws ClassNotFoundException, IOException{
//		List<Tag> lista = new ArrayList();
//		File file = new File("files/"+input);
//		
//		if (file.exists()) {
//			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
//			lista = (List<Tag>) objInput.readObject();
//			objInput.close();
//		}	
//
//		return (lista);
//	}
	
	public void salvarTags (HashMap<String,String> listaTags) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("files/" + this.output));
		
		for (Map.Entry<String, String> tags : listaTags.entrySet()) {
		     writer.write(tags.getKey()+": "+tags.getValue()+"\n");
		}
		writer.close();
	}
	
}
