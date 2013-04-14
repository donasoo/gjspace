package org.guan.lo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class Read {
	
	

	public static void readByByte(String fileName) {
        File file = new File(fileName);
        InputStream in = null;

        try {
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
            // һ�ζ�һ���ֽ�  
            in = new FileInputStream(file);
            int tempbyte;
            int i=0;
            while ((tempbyte = in.read()) != -1) {
                System.out.println(i+": "+tempbyte +"  HEX: "+Integer.toHexString(tempbyte));
                i++;
                if( i>1000) break; 
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
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ֽ�
            byte[] tempbytes = new byte[length];
            int byteread = 0;
            in = new FileInputStream(fileName);
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
            byteread = in.read(tempbytes);
            ByteBuffer bb=ByteBuffer.wrap(tempbytes);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            
            System.out.println(bb.getShort(0));
            System.out.println(bb.getChar(40));
            System.out.println(bb.get(17));
            //System.out.println(bb.getInt(407));
            
            for(int i=0; i<5; i++){
            	System.out.println("data : "+i+": "+bb.getDouble(i*8+65));
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
	
	private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("��ǰ�ֽ��������е��ֽ���Ϊ:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
