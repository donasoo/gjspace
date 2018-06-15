/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guan.ex.JFMXL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;

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
    
    //must
    @FXML
    private TextField tfColNum;
    
    @FXML
    private TextField tfStartPos;
    
    //left 
    @FXML 
    private ListView<String> lvTemplate;
    
    //right
    @FXML
    private ListView<String> lvData;
    
    
    //Tail block
    @FXML
    private CheckBox cbTail;
    @FXML
    private Label labelTailPrefix;
    @FXML
    private Label labelTailPos;
    @FXML 
    private TextField tfTailPos;
    @FXML 
    private TextField tfTailPrefix;
    
    //Name block
    @FXML
    private CheckBox cbName;
    @FXML
    private Label labelNamePrefix;
    @FXML
    private Label labelNamePos;
    @FXML 
    private TextField tfNamePos;
    @FXML 
    private TextField tfNamePrefix;
    
  //Code block
    @FXML
    private CheckBox cbCode;
    @FXML
    private Label labelCodePrefix;
    @FXML
    private Label labelCodePos;
    @FXML 
    private TextField tfCodePos;
    @FXML 
    private TextField tfCodePrefix;
    
    @FXML
    private BorderPane pane;
    
    @FXML
    private void checkTail(ActionEvent even) {
    	System.out.println("You clicked tail!");
    	
    	 
        if(cbTail.isSelected()) {
        	tfTailPos.setVisible(true);
        	tfTailPrefix.setVisible(true);
        	labelTailPos.setVisible(true);
        	labelTailPrefix.setVisible(true);
        }else {
        	tfTailPos.setVisible(false);
        	tfTailPrefix.setVisible(false);
        	labelTailPos.setVisible(false);
        	labelTailPrefix.setVisible(false);
        }
    }
    
    @FXML
    private void checkName(ActionEvent event) {
        System.out.println("You clicked name!");
        
        if(cbName.isSelected()) {
        	tfNamePos.setVisible(true);
        	tfNamePrefix.setVisible(true);
        	labelNamePos.setVisible(true);
        	labelNamePrefix.setVisible(true);
        }else {
        	tfNamePos.setVisible(false);
        	tfNamePrefix.setVisible(false);
        	labelNamePos.setVisible(false);
        	labelNamePrefix.setVisible(false);
        }
        
    }
    
    @FXML
    private void checkCode(ActionEvent event) {
        System.out.println("You clicked name!");
        
        if(cbCode.isSelected()) {
        	tfCodePos.setVisible(true);
        	tfCodePrefix.setVisible(true);
        	labelCodePos.setVisible(true);
        	labelCodePrefix.setVisible(true);
        }else {
        	tfCodePos.setVisible(false);
        	tfCodePrefix.setVisible(false);
        	labelCodePos.setVisible(false);
        	labelCodePrefix.setVisible(false);
        }
        
    }
    
    @FXML
    private void bigDeal(ActionEvent event) {
        taMessage.appendText("\n来把大的");
        
        pgBar.setProgress(.6);
        
        Service<String> service=new Service<String>() {

			@Override
			protected Task<String> createTask() {
				// TODO Auto-generated method stub
				return new TestTask();
			}
        	
        };
        
        taMessage.textProperty().bind(service.messageProperty());
        service.start();
        
        
        try {
			Image image = new Image(new FileInputStream("R3.png"));
			ImageView iv=new ImageView();
	        iv.setImage(image);
	        
	        Alert alert = new Alert(AlertType.INFORMATION, null);
	        alert.getDialogPane().setContentText(null);
	        alert.getDialogPane().setHeaderText(null);
	        alert.getDialogPane().setGraphic(iv);
	        alert.getDialogPane().setHeader(null);
	        alert.getDialogPane().setMaxWidth(512);
	        alert.showAndWait().filter(response -> response == ButtonType.OK);
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        
    }
    
    @FXML
    private void chooseSheet(ActionEvent event) {
        System.out.println("You clicked chooseSheet!");
        
    }
    
    @FXML
    private void chooseFile(ActionEvent event) {
        System.out.println("You clicked chooseFile!");
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	fillTemplates();
    	fillDatafiles();
    }

	private void fillTemplates() {
		// TODO Auto-generated method stub
		File dirTemplate=new File("Templates");
		ObservableList<String> items =FXCollections.observableArrayList (
			    dirTemplate.list());
		lvTemplate.setItems(items);
		
	}

	private void fillDatafiles() {
		// TODO Auto-generated method stub
		File dirData=new File("Datafiles");
		ObservableList<String> items =FXCollections.observableArrayList (
				dirData.list());
		lvData.setItems(items);
		
	}    
    
}
