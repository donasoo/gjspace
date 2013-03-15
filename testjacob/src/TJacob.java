import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class TJacob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//for(int i=0; i<62; i++){
			saveas(57);
		//}

	}
	
	private static void saveas(int flag){
		ComThread.InitSTA();

	    ActiveXComponent xl = new ActiveXComponent("Excel.Application");
	    xl.setProperty("Visible", new Variant(false));//Excel显示或者隐藏 

	    try {
	      System.out.println("version="+xl.getProperty("Version"));
	      Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
	      
	      Dispatch workbook=Dispatch.invoke(workbooks, "Open", //打开Excel 
	    		  Dispatch.Method, 
	    		  new Object[] { "E:\\gjspace\\testjacob\\a.xlsx", new Variant(false),//Excel的位置 
	    		                  new Variant(false) },    
	    		          new int[1]).toDispatch(); 
	      
	      
	      Dispatch sheets= Dispatch.get(workbook,"Worksheets").toDispatch();//获得所有的Sheet 
	      int SheetCount=Dispatch.get(sheets,"Count").getInt();//获得有多少个sheet 
	      
	      System.out.println(SheetCount);
	      
	      String filename="E:\\gjspace\\testjacob\\b"+String.valueOf(flag)+".pdf";
	      //for(int i=0; i<20; i++){
	    	  Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {    
                  filename, new Variant(flag) }, new int[1]);  
	      //}

		  
	      
	      Variant f = new Variant(false);
	      Dispatch.call(workbook, "Close", f);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      xl.invoke("Quit", new Variant[] {});
	      ComThread.Release();
	    }
	}

}
