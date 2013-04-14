package org.guan.lo;

public class MainTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Short.MAX_VALUE);
        String table="A001";
        System.out.println(table);
		String name="E:\\work\\Sarp 2012 ny\\"+table+".Dny";
		Read.readManyBytes(name, 1000);
		   
		Read.readByByte(name);

	}

}
