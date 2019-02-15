package ursign.ursign_client;

public class Collection {
	private Integer id;
	private String title;
	private String xmldata;
	
	Collection(int id, String title, String xmldata){
		this.id=id;
		this.title=title;
		this.xmldata=xmldata;
	}
	
	public void updateId(int newid){
		this.id=newid;
	}
}
