package org.guan.ex;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class EXFile {
	
	private String fixname="单位 ：";
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
	
	private ArrayList<TableHeadCell> thlist;
	
	private int firstData;
	private int nTableHead;
	private int colnum;
	private int rownum;
	private String firstcell;
	private int firstrow;
	private int firstcol;
	private String tablename;
	private boolean pdf=true;
	private boolean first=false;
	
	private ArrayList<String> namelist;
	private EXPdf expdf;
	
	public EXFile(){
		init();
		setup();
		readData();
	}
	
	private void setup(){
		
		thlist.add(new TableHeadCell("", 1, "A6", ""));
		
		tablename="M301-1";
		firstData=2;
		firstcell="C9";
		colnum=6;
		rownum=19;
		nTableHead=1;
		pdf=true;
		first=true;
		
		firstcol=2;
		firstrow=8;
		System.out.println(firstcol+"  "+firstrow);
	}
	
	private void init(){
		data=new String[750];
		unitName=new StringBuffer();
		namelist=new ArrayList<String>();
		expdf=new EXPdf();
		thlist=new ArrayList<TableHeadCell>(5);
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
	
	/**
	 *  处理一行
	 * @param tempString
	 */
	private void process(String tempString) {
		
		//分解一行
		data=tempString.split(",");
		
		//构建文件名
		fileName=(data[0]+" "+data[1]).trim();
		
		System.out.println(unitName.toString());
		if(data[0].substring(6, 8).endsWith("00")){
			excel();
		}
		
	}


	private void fitTableHead() {
		// TODO Auto-generated method stu
		for(int i=0; i<nTableHead; i++){
			TableHeadCell thc =thlist.get(i);
			thc.setContent(data[thc.getSerial()].trim());
			row=ds.getRow(thc.getRow());
			cell=row.getCell(thc.getCol());
			if(cell == null){
				cell=row.createCell(thc.getCol());
			}
			cell.setCellValue(thc.getFixcontent());
		}
		
		
	}
	
	private void excel(){
		try {
			pkg= OPCPackage.open(template, PackageAccess.READ_WRITE);
			wb = new XSSFWorkbook(pkg);
			ds=wb.getSheetAt(0); 
			
			//填写综合机关名称
			fitTableHead();
			
			double realvalue=0.0;
			long intvalue=0;
			
			for(int i=0; i<rownum; i++){
				row=ds.getRow(i+firstrow);
				if(row == null){
					row=ds.createRow(i+firstrow);
				}
				
				for(int j=0; j<colnum; j++){
					
					XSSFCell cell=row.getCell(j+firstcol);
					if(cell == null){
						cell=row.createCell(j+firstcol);
					}
					
					if(i <  rownum-1){
						cell.getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_THIN);
					}else{
						cell.getCellStyle().setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
					}
					cell.getCellStyle().setBorderLeft(HSSFCellStyle.BORDER_THIN);
					
					if(first && j==0){
						cell.setCellValue(data[firstData+i*colnum+j]);
						cell.getCellStyle().setBorderLeft(HSSFCellStyle.BORDER_NONE);
						continue;
					}
					
					if(data[firstData+i*colnum+j].isEmpty()){
					}else if(data[firstData+i*colnum+j].contains(".")){
						
						realvalue=Double.parseDouble(data[firstData+i*colnum+j]);
						if(realvalue > 0.01){
							cell.setCellValue(realvalue);
						}
					}else{
						intvalue=Long.parseLong(data[firstData+i*colnum+j]);
						if(intvalue != 0){
							cell.setCellValue(intvalue);
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
