/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guan.ex;

import java.io.File;
import java.util.regex.Matcher;

/**
 *
 * @author LGY
 */
public class Test {
    
    public static void main(String[] args) {
       
    	String str="-гн";
    	
    	System.out.println(str.contains("-"));
    	System.out.println(str.contains("гн"));
    	System.out.println(str.contains("-|гн"));
    	
    	str.matches("");
    }
    
}
