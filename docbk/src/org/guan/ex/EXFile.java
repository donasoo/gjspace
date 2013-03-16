package org.guan.ex;

import java.io.*;

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
	private final File template=new File(".\\files\\Template M304.xlsx");
	private FileOutputStream fos;
	private String[] data;
	
	private OPCPackage pkg;
	private XSSFWorkbook wb;
	private XSSFSheet ds ; 
	private XSSFRow row;
	private XSSFCell	cell;
	
	public EXFile(){
		init();
		readData();
		//readData();
	}
	
	private void init(){
		data=new String[750];
		unitName=new StringBuffer();
		
	}
	
	private void readData() {
        File file = new File(".\\files\\M304.txt");
        BufferedReader reader = null;
        try {
        	
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            
            // 一次读入一行，直到读入null为文件结束
            //while ((tempString = reader.readLine()) != null) {
                // 显示行号
            tempString = reader.readLine();
                process(tempString);
                tempString = reader.readLine();
                process(tempString);
                line++;
           // }
            
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
		excel();
	}


	
	private void excel(){
		try {
			pkg= OPCPackage.open(template, PackageAccess.READ_WRITE);
			wb = new XSSFWorkbook(pkg);
			ds=wb.getSheetAt(0); 
			
			//填写综合机关名称
			row=ds.getRow(3);
			cell=row.getCell(0);
			cell.setCellValue(unitName.toString().trim());
			double productd=0.0;
			double productl=0;
			double price=0.0;
			long value=0;
			
			for(int i=0; i<237; i++){
				row=ds.getRow(i+7);
				
				price=Double.parseDouble(data[i*3+3]);
				value=Long.parseLong(data[i*3+4]);
				if(data[i*3+2].contains(".")){
					productd=Double.parseDouble(data[i*3+2]);
					if(productd > 0.01){
						row.getCell(3).setCellValue(productd);
					}
					
				}else{
					productl=Long.parseLong(data[i*3+2]);
					if(productl != 0){
						row.getCell(3).setCellValue(productl);
					}
				}
				if(price > 0.01){
					row.getCell(4).setCellValue(price);
				}
				if(value != 0){
					row.getCell(5).setCellValue(value);
				}
			}
			
			
			//保存文档
			fos=new FileOutputStream(".\\files\\"+fileName+".xlsx");
			
			wb.write(fos);
			//pkg.save(fos);
			pkg.revert();
			//pkg.close();
		
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
