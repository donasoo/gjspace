package org.guan.ex;

public class TableHeadCell {
	
	public TableHeadCell(){
	}
	

	public TableHeadCell(String con, int index, String pos){
		setContent(con);
		setSerial(index);
		setPosition(pos);
		pos2num();
	}
	
	public TableHeadCell(String con, int index, String pos, String fix){
		setContent(con);
		setSerial(index);
		setPosition(pos);
		pos2num();
		this.fix=fix;
	}
	
	public int getRow(){
		return row;
	}

	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public int getSerial() {
		return serial;
	}


	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}
	
	private void pos2num(){
		col=position.charAt(0)-'A';
		row=position.charAt(1)-'1';
	}
	
	

	public String getFixcontent() {
		return fix+content;
	}


	public void setFix(String fix) {
		this.fix = fix;
	}



	private String content;
	private int serial;
	private int row;
	private int col;
	private String  position;
	private String fix;
}
