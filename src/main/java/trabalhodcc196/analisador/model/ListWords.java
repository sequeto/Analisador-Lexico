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
