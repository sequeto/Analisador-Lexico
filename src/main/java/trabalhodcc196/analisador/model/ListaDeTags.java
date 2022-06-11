package trabalhodcc196.analisador.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListaDeTags implements Serializable {
	private List<Tag> lsTags;
}
