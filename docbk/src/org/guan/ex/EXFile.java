package org.guan.ex;

import java.io.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXFile {
	
	private final String fixname="�ۺϻ������ƣ�";
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
        	pkg= OPCPackage.open(template);
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            //while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
            tempString = reader.readLine();
                process(tempString);
                tempString = reader.readLine();
                process(tempString);
                line++;
           // }
            
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (reader != null) {
                try {
                    reader.close();
                    pkg.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	
	
	private void process(String tempString) {
		// TODO Auto-generated method stub
		data=tempString.split(",");
		fileName=data[0]+" "+data[1];
		unitName=new StringBuffer(fixname+data[1]);
		System.out.println(unitName.toString());
		excel();
	}


	
	private void excel(){
		try {
			wb = new XSSFWorkbook(pkg);
			ds=wb.getSheetAt(0); 
			
			//��д�ۺϻ�������
			row=ds.getRow(3);
			cell=row.getCell(0);
			cell.setCellValue(unitName.toString().trim());
			
			
			//�����ĵ�
			fos=new FileOutputStream(".\\files\\"+fileName+".xlsx");
			wb.write(fos);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	

}
