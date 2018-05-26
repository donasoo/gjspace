/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guan.ex.JFMXL;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.guan.ex.*;

/**
 *
 * @author LGY
 */
public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ProgressBar pgBar;
    
    
    @FXML
    private TextArea taMessage;
    
    @FXML
    private CheckBox cbCode;
    
    @FXML
    private Label 
    
    
    @FXML
    private void checkCode(ActionEvent event) {
        System.out.println("You clicked code!");
        
        if(cbCode.isSelected()) {
        	
        }
        
    }
    
    @FXML
    private void checkName(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void bigDeal(ActionEvent event) {
        taMessage.appendText("\n浣犺鏉ヤ釜澶т拱鍗� 锛�");

        pgBar.setProgress(.6);
        TaskConfig tf=new TaskConfig();
        FillXlsx fx=new FillXlsx(tf);
    }
    
    @FXML
    private void chooseSheet(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @FXML
    private void chooseFile(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
