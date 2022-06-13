/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */

package trabalhodcc196.analisador.resources;

import java.util.HashMap;
import java.util.Map;

public class TagsProcess {

	public void saveTags(String[] comand, HashMap<String, String> listaTags) throws Exception {
		if(listaTags.isEmpty()) {
			listaTags.put(comand[0].replace(":", ""), comand[1]);
		}else {
			for (Map.Entry<String, String> tags : listaTags.entrySet()) {
			     
				if(tags.getKey().equalsIgnoreCase(comand[0].replace(":", "")))
			     {
			    	 
			    	 throw new Exception ("Tag ja existente.");
			     }
			}
			listaTags.put(comand[0].replace(":", ""), comand[1]);
		}
	}
}
