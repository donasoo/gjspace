package org.guan.lo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainTwo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(Integer.MAX_VALUE);
        String table="A01.Dte";
        System.out.println(table);
		String name="E:\\report\\Sarp te\\"+table;
		
		
		long start = System.currentTimeMillis();  
        //System.out.println("md5:"+FileMD5.getFileMD5String(name));  
        long end = System.currentTimeMillis();  
        System.out.println("Consume " + (end - start) + "ms");  
		
		Read.readManyBytes(name, 2000);
		   
		Read.readByByte(name);

		tryS();
	}
	
	public static void tryS(){
		  try   { 
	             /*  ����һ���ļ�д�����л�����  */  
	            FileOutputStream ostream  =   new  FileOutputStream( " tree.dat " ); 
	             /*  ���������  */  
	            ObjectOutputStream p  =   new  ObjectOutputStream(ostream); 

	             /*  ����һ�����������  */  
	            SarpTable base  =   new SarpTable(); 

	            System.out.println(base.toString());
	            p.writeObject(base);  //  ����д�����С�  
	            p.flush(); 
	            ostream.close();     //  �ر��ļ���  
	 
	        }   catch  (Exception ex)  { 
	            ex.printStackTrace(); 
	        }  
	}

}
