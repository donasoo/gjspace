/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guan.ex;

import java.io.File;

/**
 *
 * @author LGY
 */
public class TaskConfig {
    
    public TaskConfig(){
        templateFile=new File("E:\\GitJ\\JFXML\\A303.xlsx");
        dataFile=new File("data.csv");
    }
    
    public boolean codeNeed = false;
    public String codeCell="A1";
    public String codePrefix="";
    
    public boolean nameNeed = false;
    public String nameCell = "A2";
    public String namePrefix = "";
    
    public String startCell = "C9";
    public int colNum = 2;
    
    public boolean tailNeed = false;
    public String tailCell = "A3";
    public String tailStr = "";
    
    public boolean xlsKeep = true;
    public boolean pdfKeep = true;
    public boolean xlsMerge = true;
    public boolean pdfMerge = true;
    
    public File templateFile;
    public File dataFile;
    
}
