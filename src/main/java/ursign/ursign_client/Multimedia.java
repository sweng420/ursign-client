package ursign.ursign_client;

public class Multimedia {
	private String type;
	private String filelocation;
	
	Multimedia(String type, String filelocation){
		this.type=type;
		this.filelocation=filelocation;
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
		return type+" "+filelocation;
	}
	
}
	