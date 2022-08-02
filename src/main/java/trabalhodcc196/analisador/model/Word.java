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
