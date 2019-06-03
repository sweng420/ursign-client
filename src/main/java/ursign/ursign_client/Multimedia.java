package ursign.ursign_client;

public class Multimedia {
	private String type;
	private String filelocation;
	private String style;
	private int rowInfo;
	
	Multimedia(String type, String filelocation, String style, int row){
		this.type=type;
		this.filelocation=filelocation;
		this.style = style;
		this.rowInfo = row;
	}
	
	public String getType(){
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
	public void setType(String type){
		this.type=type;
	}
	public void setFilelocation(String filelocation){
		this.filelocation=filelocation;
	}

	public String toString() {
		return "Multimedia {Type: "+type+" Location:"+filelocation+" Style:"+style+" Row:"+rowInfo+"}";
	}
	
}
	