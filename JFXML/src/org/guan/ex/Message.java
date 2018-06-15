package org.guan.ex;

public class Message {
	
	public Message(String path){
		type=0;
		name=path;
	}
	
	public Message(int x, String filename){
		type=1;
		name=filename;
	}
	
	private int type;
	private String name;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
