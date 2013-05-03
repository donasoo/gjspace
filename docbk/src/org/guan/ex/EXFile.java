package org.guan.ex;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXFile {
	
	private final String fixname="综合机关名称：";
	private StringBuffer unitName;
	private String fileName="000";
	private File template;
	private File datafile;
	private FileOutputStream fos;
	private String[] data;
	
	private OPCPackage pkg;
	private XSSFWorkbook wb;
	private XSSFSheet ds ; 
	private XSSFRow row;
	private XSSFCell	cell;
	
	private int colnum=3;
	private int rownum=231;
	private String firstcell="D9";
	private int firstrow;
	private int firstcol;
	private String tablename="M304";
	private boolean pdf=true;
	
	private ArrayList<String> namelist;
	private EXPdf expdf;
	
	public EXFile(){
		init();
		readData();
		
	}
	
	private void init(){
		data=new String[750];
		unitName=new StringBuffer();
		namelist=new ArrayList<String>();
		expdf=new EXPdf();
		
		firstcol=firstcell.charAt(0)-'A';
		firstrow=firstcell.charAt(1)-'1';
		System.out.println(firstcol+"  "+firstrow);
	}
	
	private void readData() {
		template=new File(".\\files\\Template "+tablename+".xlsx");
        datafile = new File(".\\files\\"+tablename+".txt");
        BufferedReader reader = null;
        try {
        	
            reader = new BufferedReader(new FileReader(datafile));
            String tempString = null;
            
            while ((tempString = reader.readLine()).length()>10) {
            	process(tempString);
            }
            if(pdf){
            	expdf.create(namelist, tablename);
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    
                } catch (IOException e1) {
                }
            }
        }
    }
	
	
	private void process(String tempString) {
		// TODO Auto-generated method stub
		data=tempString.split(",");
		fileName=(data[0]+" "+data[1]).trim();
		
		unitName=new StringBuffer(fixname+data[1]);
		
		System.out.println(unitName.toString());
		if(data[0].substring(6, 8).endsWith("00")){
			excel();
		}
		
	}


	
	private void excel(){
		try {
			pkg= OPCPackage.open(template, PackageAccess.READ_WRITE);
			wb = new XSSFWorkbook(pkg);
			ds=wb.getSheetAt(0); 
			
			//填写综合机关名称
			row=ds.getRow(3);
			cell=row.getCell(0);
			if(cell == null){
				cell=row.createCell(0);
			}
			cell.setCellValue(unitName.toString().trim());
			double realvalue=0.0;
			long intvalue=0;
			
			for(int i=0; i<rownum; i++){
				row=ds.getRow(i+firstrow);
				
				for(int j=0; j<colnum; j++){
					if(data[2+i*colnum+j].contains(".")){
						realvalue=Double.parseDouble(data[2+i*colnum+j]);
						if(realvalue > 0.01){
							row.getCell(j+firstcol).setCellValue(realvalue);
						}
						
					}else{
						intvalue=Long.parseLong(data[2+i*colnum+j]);
						if(intvalue != 0){
							row.getCell(j+firstcol).setCellValue(intvalue);
						}
					}
				}
				
			}
			
			
			//保存文档
			fos=new FileOutputStream(".\\tempfiles"+"\\"+fileName+".xlsx");
			
			wb.write(fos);
			pkg.revert();
			
			namelist.add("E:\\GitJ\\docbk\\tempfiles\\"+fileName+".xlsx");
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
	    
	}
	
	

}
