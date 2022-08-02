package trabalhodcc196.analisador.model;

import java.util.List;

public class ListWords {
	private List<Word> words;
	private Integer tagsRecognize;
	
	public ListWords(List<Word> words) {
		this.setWords(words);
		this.tagsRecognize = 0;
	}

	public Integer getTagsRecognize() {
		return tagsRecognize;
	}

	public void setTagsRecognize(Integer tagsRecognize) {
		this.tagsRecognize = tagsRecognize;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
	
	public void incrementTags() {
		this.tagsRecognize++;
	}
}
