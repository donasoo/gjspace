package org.guan.ex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class EXPdf {
	private static final int flag = 57;

	public PDFMergerUtility ut;

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
		ut.setDestinationFileName("E:\\GitJ\\docbk\\files\\" + mergename
				+ ".pdf");
		try {
			ut.mergeDocuments();
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			ut.addSource(fullname + ".pdf");
		}
		System.out.println("Merge pdf : "+tablename+"...");
		merge(tablename);
	}

}
