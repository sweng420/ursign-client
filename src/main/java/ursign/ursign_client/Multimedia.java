package ursign.ursign_client;

public class Multimedia {
	private String type;
	private String filelocation;
	private String style;
	
	Multimedia(String type, String filelocation, String style){
		this.type=type;
		this.filelocation=filelocation;
		this.style = style;
	}
	
	public String getType(){
		return type;
	}
	
	public String getFilelocation(){
		return filelocation;
	}
	
	public void setType(String type){
		this.type=type;
	}
	public void setFilelocation(String filelocation){
		this.filelocation=filelocation;
	}
	public String toString() {
		return "Multimedia {Type: "+type+"\nLocation:"+filelocation+"\nStyle:"+style+"}";
	}
	
}
	