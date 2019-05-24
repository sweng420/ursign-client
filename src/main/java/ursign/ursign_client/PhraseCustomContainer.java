package ursign.ursign_client;

public class PhraseCustomContainer {
	
	private int index = -1;
	private int similarity = 0;
	private int position = 0;
	
	PhraseCustomContainer(int index, int similarity, int position){
		this.index = index;
		this.similarity = similarity;
		this.position = position;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getSimilarity(){
		return similarity;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public void setSimilarity(int similarity){
		this.similarity = similarity;
	}
	
	public void setPosition(int position){
		this.position = position;
	}

}
