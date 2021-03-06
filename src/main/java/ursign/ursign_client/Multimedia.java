package ursign.ursign_client;

public class Multimedia {
	enum MultimediaType {
		image,
		video,
		graphic,
		text,
		audio
	}
	private MultimediaType type;
	private String filelocation;
	private String style;
	private int rowInfo;
	
	Multimedia(MultimediaType type, String filelocation, String style, int row){
		this.type=type;
		this.filelocation=filelocation;
		this.style = style;
		this.rowInfo = row;
	}
	
	public MultimediaType getType(){
		return type;
	}
	
	public String getFilelocation(){
		return filelocation;
	}
	public String getStyle(){
		return style;
	}
	public int getRowInfo(){
		return rowInfo;
	}
	public void setType(MultimediaType type){
		this.type=type;
	}
	public void setFilelocation(String filelocation){
		this.filelocation=filelocation;
	}
	public boolean isText() {
		return this.type == MultimediaType.text;
	}
	public String toString() {
		return "Multimedia {Type: "+type+" Location:"+filelocation+" Style:"+style+" Row:"+rowInfo+"}";
	}
	
}
	