package com.icesoft.libgdx.utils;

import java.text.DecimalFormat;

public class MemoryUtils {
	public static String getMemoryUnitString(long size,String format) {
		 DecimalFormat df = new DecimalFormat(format);
	        String fileSizeString = "";
	        if (size < 1024) {
	            fileSizeString = df.format((double) size) + "B";
	        } else if (size < 1048576) {
	            fileSizeString = df.format((double) size / 1024) + "K";
	        } else if (size < 1073741824) {
	            fileSizeString = df.format((double) size / 1048576) + "M";
	        } else {
	            fileSizeString = df.format((double) size / 1073741824) + "G";
	        }
	        return fileSizeString;
	}
	public static String getMemoryUnitString(long size) {
		return getMemoryUnitString(size,"#.00");
	}
	public static long getFreeMemory(){
		return Runtime.getRuntime().freeMemory();
	}
	public static long getTotalMemory(){
		return Runtime.getRuntime().totalMemory();
	}
	public static long getMaxMemory(){
		return Runtime.getRuntime().maxMemory();
	}
	public static String getMemoryUsageString(){
		return "Total:" + getMemoryUnitString(getTotalMemory()) + 
				"  Max:" + getMemoryUnitString(getMaxMemory()) + 
				"  Free:" + getMemoryUnitString(getFreeMemory());
	}
	public static long getUsedMemory(){
		return getTotalMemory() - getFreeMemory();
	}
}
