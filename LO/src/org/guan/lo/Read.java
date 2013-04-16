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
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ֽ�
            byte[] tempbytes = new byte[length];
            int byteread = 0;
            in = new FileInputStream(fileName);
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
            byteread = in.read(tempbytes);
            ByteBuffer bb=ByteBuffer.wrap(tempbytes);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            byte[] bs=new byte[30];
            bb.position(75);
            bb.get(bs);
            //System.out.println("��04λshort"+bb.getShort(4));
            //System.out.println("75char:"+new String(bs));
           // System.out.println("test:"+bb.get(0));
           // System.out.println("��67λint��"+bb.getInt(71));
            
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
