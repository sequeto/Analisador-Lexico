package trabalhodcc196.analisador.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	public static BufferedReader criarBufferedReader(String pathArquivo) throws FileNotFoundException {
        try {   
            return new BufferedReader(new FileReader(pathArquivo));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        }     
    }

    public static String criarStringDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = criarBufferedReader("files/"+nomeArquivo);
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
    
    public static void escreverEmArquivo(String content, String fileName) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("output/"+fileName));
    	writer.write(content);
    	writer.close();
    }
}
