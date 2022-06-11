package trabalhodcc196.analisador.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileUtils {
	
	private String output;

    public static String criarStringDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("files/"+nomeArquivo));
        String stringLida = "";
        String readLine = reader.readLine();
        while(readLine != null){
            stringLida = stringLida + readLine + "\n";
            readLine = reader.readLine();
        }
        if(!stringLida.equals("")) {
        	stringLida = stringLida.substring(0, stringLida.lastIndexOf('\n'));
        }
        reader.close();
        return stringLida;
    }
    
    public void escreverEmArquivo(String content) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("output/"+this.output));
    	writer.write(content);
    	writer.close();
    }
    
    public void definirCaminhoSaida(String output){
    	this.output = output;
    }
}
