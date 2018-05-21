package org.guan.ex;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("start");
		//EXFile ef=new EXFile();

		PrintPdf pp=new PrintPdf();
		MergePdf mp=new MergePdf();
		pp.addObserver(mp);
		//pp.dealDirAll("files", "xx001");
		pp.PrintPdfs("out", 9);
		System.out.println("end");
	}
	
	

}
