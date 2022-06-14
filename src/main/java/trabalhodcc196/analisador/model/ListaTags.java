/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */

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
	
	public void imprimirLista()
	{
		if(this.lsTags.isEmpty()) {
			cli.write("Lista vazia.");
		} else {
			for (Map.Entry<String, String> tags : this.lsTags.entrySet()) {
			     cli.write(tags.getKey()+": "+tags.getValue());
			}
		}
	}
}
