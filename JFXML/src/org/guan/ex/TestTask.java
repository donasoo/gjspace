package org.guan.ex;

import javafx.concurrent.Task;

public class TestTask extends Task<String> {

	@Override
	protected String call() throws Exception {
		// TODO Auto-generated method stub
		updateMessage("gogogo ");
		for(int i=0; i<10; i++) {
			Thread.sleep(500);
			this.updateMessage("line "+i);
			
		}
		
		
		this.updateMessage("loop end");
		return "OK";
	}
	
}
