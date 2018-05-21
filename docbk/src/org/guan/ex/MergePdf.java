package org.guan.ex;

import java.util.Observable;
import java.util.Observer;

public class MergePdf implements Observer {
	private EXPdf pdf;
	public MergePdf(){
		pdf =new EXPdf();
		pdf.setDestinationDir("merge-out\\");
	}
	
	public void setDestinationDir(String dir){
		pdf.setDestinationDir(dir);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Message mes=(Message)arg;
		//System.out.println("update o: "+o.toString());
		//System.out.println("update type: "+mes.getType());
		//System.out.println("update type: "+mes.getName());
		if(mes.getType()==0){
			pdf.addsource(mes.getName());
		}else{
			pdf.merge(mes.getName());
		}
		
	}

}
