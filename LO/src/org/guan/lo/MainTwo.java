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
	             /*  创建一个文件写入序列化树。  */  
	            FileOutputStream ostream  =   new  FileOutputStream( " tree.dat " ); 
	             /*  创建输出流  */  
	            ObjectOutputStream p  =   new  ObjectOutputStream(ostream); 

	             /*  创建一个二层的树。  */  
	            SarpTable base  =   new SarpTable(); 

	            System.out.println(base.toString());
	            p.writeObject(base);  //  将树写入流中。  
	            p.flush(); 
	            ostream.close();     //  关闭文件。  
	 
	        }   catch  (Exception ex)  { 
	            ex.printStackTrace(); 
	        }  
	}

}
