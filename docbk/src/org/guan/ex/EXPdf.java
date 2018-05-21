package org.guan.ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.util.zip.*;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class EXPdf {
	private static final int flag = 57;
	
	private String destinationDir;

	public PDFMergerUtility ut;
	
	public EXPdf(){
		ut = new PDFMergerUtility();
	}

	public void saveaspdf(String fullname) {
		ComThread.InitSTA();

		ActiveXComponent xl = new ActiveXComponent("Excel.Application");
		xl.setProperty("Visible", new Variant(false));// Excel显示或者隐藏

		try {
			// System.out.println("version="+xl.getProperty("Version"));
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();

			Dispatch workbook = Dispatch.invoke(workbooks,
					"Open", // 打开Excel
					Dispatch.Method,
					new Object[] { fullname, new Variant(false),// Excel的位置
							new Variant(false) }, new int[1]).toDispatch();

			Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {
					fullname + ".pdf", new Variant(flag) }, new int[1]);

			Variant f = new Variant(false);
			Dispatch.call(workbook, "Close", f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			xl.invoke("Quit", new Variant[] {});
			ComThread.Release();
		}
	}

	public void merge(String mergename) {
		ut.setDestinationFileName(destinationDir + mergename
				+ ".pdf");
		System.out.println("merge-out : " +mergename);
		try {
			ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ut = new PDFMergerUtility();
	}

	public void create(ArrayList<String> namelist, String tablename) {
		// TODO Auto-generated method stub
		ut = new PDFMergerUtility();
		Collections.sort(namelist);
		Iterator<String> itr = namelist.iterator();
		String fullname;
		while (itr.hasNext()) {
			fullname = itr.next();
			System.out.println("create pdf: " + fullname);
			saveaspdf(fullname);
			try {
				ut.addSource(fullname + ".pdf");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("out");
			}
		}
		System.out.println("Merge pdf : "+tablename+"...");
		merge(tablename);
	}
	
	public void rcm() {
		// TODO Auto-generated method stub
		ut = new PDFMergerUtility();
		String filepath="E:\\gitR\\document\\out\\";
		File dir=new File(filepath);
		ArrayList<String> namelist=new ArrayList<String>(40000);
		Collections.addAll(namelist, dir.list());
		Collections.sort(namelist);
		String fullname="";
		String townname="";
		String filename="";
		Iterator<String> itr = namelist.iterator();
		while (itr.hasNext()){
			filename=itr.next();
			
			fullname=filepath+filename;
			saveaspdf(fullname);
			
			if(townname.isEmpty()||townname.equals(filename.substring(0, 9))){
				
				addsource(fullname);
			}else{
				merge(townname);
				System.out.println(townname);
				ut = new PDFMergerUtility();
				addsource(fullname);
			}
			townname=filename.substring(0, 9);
		}
		merge(townname); 
	}
	
	public void addsource(String fullname){
		try {
			ut.addSource(fullname + ".pdf");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("out");
		}
	}
	
	public void testzip(){
		
	}

	public String getDestinationDir() {
		return destinationDir;
	}

	public void setDestinationDir(String destinationDir) {
		this.destinationDir = destinationDir;
	}

}
