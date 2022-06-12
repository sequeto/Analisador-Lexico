package trabalhodcc196.analisador.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import trabalhodcc196.analisador.utils.IOUtils;


public class ListaTags {
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public HashMap<String,String> lsTags;

	public ListaTags()
	{
		lsTags = new HashMap<>();
	}
	
	public void imprimirLista(HashMap<String,String> listaTags)
	{
		for (Map.Entry<String, String> tags : listaTags.entrySet()) {
		     cli.write(tags.getKey()+": "+tags.getValue());
		}
	}
}
