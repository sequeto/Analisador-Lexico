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

import trabalhodcc196.analisador.exceptions.InputErrorException;

public class TagsProcess {

	public void saveTags(String[] comand, HashMap<String, String> listaTags) throws Exception {
		if(comand.length >=2) {
			throw new InputErrorException("Processamento impossível para a entrada informada");
		}
		if (listaTags.isEmpty()) {
			try {
				listaTags.put(comand[0].replace(":", ""), comand[1]);
			} catch (Exception e) {
				throw new InputErrorException("Processamento impossível para a entrada informada");
			}

		} else {
			for (Map.Entry<String, String> tags : listaTags.entrySet()) {

				if (tags.getKey().equalsIgnoreCase(comand[0].replace(":", ""))) {
					throw new Exception("Tag já existente.");
				}

			}

			try {
				listaTags.put(comand[0].replace(":", ""), comand[1]);
			} catch (Exception e) {
				throw new InputErrorException("Processamento impossível para a entrada informada");
			}
		}
	}
}
