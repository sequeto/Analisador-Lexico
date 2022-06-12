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
