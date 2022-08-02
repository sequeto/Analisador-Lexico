package trabalhodcc196.analisador.model;

import java.util.List;

public class ListWords {
	private List<Word> words;
	
	public ListWords(List<Word> words) {
		this.setWords(words);
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}
