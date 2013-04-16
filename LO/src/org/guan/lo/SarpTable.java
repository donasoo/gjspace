package org.guan.lo;

import java.io.Serializable;

public class SarpTable implements Serializable{

	private short indexData=52;
	private final short empty1=0;
	private final byte[] empty2={51, 46, 48, 48, 70};
	private short countRow=6;
	private short countColumn=3;
	private final short empty3=0;
	private short widthData=10;
	private byte grade=4+48;
	private byte widthID=2+48;
	/**
	 * @return the indexData
	 */
	public short getIndexData() {
		return indexData;
	}
	/**
	 * @param indexData the indexData to set
	 */
	public void setIndexData(short indexData) {
		this.indexData = indexData;
	}
	/**
	 * @return the empty1
	 */
	public short getEmpty1() {
		return empty1;
	}
	/**
	 * @return the empty2
	 */
	public byte[] getEmpty2() {
		return empty2;
	}
	/**
	 * @return the countRow
	 */
	public short getCountRow() {
		return countRow;
	}
	/**
	 * @param countRow the countRow to set
	 */
	public void setCountRow(short countRow) {
		this.countRow = countRow;
	}
	/**
	 * @return the countColumn
	 */
	public short getCountColumn() {
		return countColumn;
	}
	/**
	 * @param countColumn the countColumn to set
	 */
	public void setCountColumn(short countColumn) {
		this.countColumn = countColumn;
	}
	/**
	 * @return the empty3
	 */
	public short getEmpty3() {
		return empty3;
	}
	/**
	 * @return the widthData
	 */
	public short getWidthData() {
		return widthData;
	}
	/**
	 * @param widthData the widthData to set
	 */
	public void setWidthData(short widthData) {
		this.widthData = widthData;
	}
	/**
	 * @return the grade
	 */
	public byte getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	/**
	 * @return the widthID
	 */
	public byte getWidthID() {
		return widthID;
	}
	/**
	 * @param widthID the widthID to set
	 */
	public void setWidthID(byte widthID) {
		this.widthID = widthID;
	}
	

}
