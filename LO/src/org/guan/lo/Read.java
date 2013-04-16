package org.guan.lo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class Read {
	
	

	public static void readByByte(String fileName) {
        File file = new File(fileName);
        InputStream in = null;

        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节  
            in = new FileInputStream(file);
            int tempbyte;
            int i=0;
            while ((tempbyte = in.read()) != -1) {
                System.out.println(i+": "+tempbyte +"  HEX: "+Integer.toHexString(tempbyte));
                i++;
                if( i>20000) break; 
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
	
	public static void readManyBytes(String fileName, int length){
		
		File file = new File(fileName);
        InputStream in = null;
		try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[length];
            int byteread = 0;
            in = new FileInputStream(fileName);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            byteread = in.read(tempbytes);
            ByteBuffer bb=ByteBuffer.wrap(tempbytes);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            byte[] bs=new byte[30];
            bb.position(75);
            bb.get(bs);
            //System.out.println("第04位short"+bb.getShort(4));
            //System.out.println("75char:"+new String(bs));
           // System.out.println("test:"+bb.get(0));
           // System.out.println("第67位int："+bb.getInt(71));
            
            for(int i=0; i<6; i++){
            	System.out.println("data "+i+": "+bb.getInt(i*4+103));
            }
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	

}
