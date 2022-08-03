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

import java.util.ArrayList;
import java.util.List;

public class Word {
	private String word;
	private String tag;
	private boolean recognize;
	
	private boolean warning;
	private List<String> tags;
	
	public Word(String word) {
		this.setWord(word);
		this.setTag(null);
		this.setRecognize(false);
		this.setWarning(false);
		this.tags = new ArrayList<>();
	}
	
	public void setWarningTag(String tag) {
		this.tags.add(tag);
	}

	public boolean isWarning() {
		return warning;
	}

	public void setWarning(boolean warning) {
		this.warning = warning;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public boolean isRecognize() {
		return recognize;
	}
	public void setRecognize(boolean recognize) {
		this.recognize = recognize;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
}