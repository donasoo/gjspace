import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;



public class MergePDF {

	/**
	 * this class
	 * {@link }
	 * @param args
	 */
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		PDFMergerUtility ut = new PDFMergerUtility();
		ut.addSource("E:\\gjspace\\testjacob\\EglinAFB.pdf");
		ut.addSource("E:\\gjspace\\testjacob\\glossary.pdf");
		//ut.addSource("");
		ut.setDestinationFileName("E:\\gjspace\\testjacob\\eg.pdf");
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

}
