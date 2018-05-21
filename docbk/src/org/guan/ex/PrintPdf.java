package org.guan.ex;

import java.io.File;
import java.util.Observable;

/**
 * @author LGY
 *
 */
public class PrintPdf extends Observable {
	
	private int lengthMerge=0;
	private String tempname;
	private EXPdf pdf;
	
	public PrintPdf(){
		pdf=new EXPdf();
		System.out.println(pdf.getDestinationDir());
	}

	
	/**
	 * 按名字前几位分别全为一个pdf
	 * @param str
	 * @param length
	 */
	public void PrintPdfs(String str, int length){
		File dir = new File(str);
		File fs[] = dir.listFiles();
		tempname=fs[0].getName().substring(0, length);
		for(int i=0; i<fs.length; i++) {
			if(!tempname.equals(fs[i].getName().substring(0, length))){
				this.setChanged();
				this.notifyObservers(new Message(1, tempname));			
			}
			tempname=fs[i].getName().substring(0, length);
			printone(fs[i]);
		}
		this.setChanged();
		this.notifyObservers(new Message(1, tempname));	
	}
	
/**
 * 直接合为1个pdf
 * @param strdir
 * @param mergename
 */
	public void dealDirAll(String strdir, String mergename){
		File dir = new File(strdir);
		File fs[] = dir.listFiles();

		for(int i=0; i<fs.length; i++) {
			printone(fs[i]);
		}
		this.setChanged();
		this.notifyObservers(new Message(1, mergename));
		
	}


	private void printone(File file) {
		// TODO Auto-generated method stub
		pdf.saveaspdf(file.getAbsolutePath());
		this.setChanged();
		this.notifyObservers(new Message(file.getAbsolutePath()));
	}

	public int getLengthMerge() {
		return lengthMerge;
	}

	public void setLengthMerge(int lengthMerge) {
		this.lengthMerge = lengthMerge;
	}

}
