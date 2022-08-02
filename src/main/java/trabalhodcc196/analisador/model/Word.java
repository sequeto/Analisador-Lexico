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

public class Word {
	private String word;
	private String tag;
	private boolean recognize;
	
	public Word(String word) {
		this.setWord(word);
		this.setTag(null);
		this.setRecognize(false);
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
