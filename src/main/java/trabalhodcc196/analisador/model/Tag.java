package trabalhodcc196.analisador.model;

import java.util.List;

public class Tag {
	private String tag;
	private String correspondingText;
	
	Tag () {
		this.tag = "";
		this.correspondingText = "";
	}
	
	Tag (String tag, String correspondingText) {
		this.tag = tag;
		this.correspondingText = correspondingText;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getCorrespondingText() {
		return correspondingText;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public void setCorrespondingText(String correspondingText) {
		this.correspondingText = correspondingText;
	}
}
