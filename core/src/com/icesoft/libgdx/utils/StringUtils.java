package com.icesoft.libgdx.utils;

import java.util.List;

public class StringUtils {	
	public static String removeDuplicate(List<String> source){
		String target = new String();
		String tmp;
        for (int i = 0; i < source.size(); i++) {
           for(int j=0;j<source.get(i).length();j++){
        	   tmp = source.get(i).substring(j,j+1);
        	   if(!target.contains(tmp)){
        		   target = target +  tmp;
        	   }
           }
        }
        return target;
	}
	public static String removeDuplicate(String source){
		String target = new String();
		String tmp;
		for(int j=0;j<source.length();j++){
    	   tmp = source.substring(j,j+1);
    	   if(!target.contains(tmp)){
    		   target = target +  tmp;
    	   }          
        }
        return target;
	}
}
