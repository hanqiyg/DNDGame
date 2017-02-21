package com.icesoft.libgdx.log;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class ConsoleLog{
	PipedInputStream piOut;
    PipedInputStream piErr;
    PipedOutputStream poOut;
    PipedOutputStream poErr;
    
    boolean read = true;
    
    public static final int MAX_LINES = 100; 
    
    List<String> lines = new ArrayList<String>();
  
    public ConsoleLog() {
    	try{
	        // Set up System.out
    		piOut = new PipedInputStream();
	    	BufferedReader brOut = new BufferedReader(new InputStreamReader(piOut));
	        poOut = new PipedOutputStream(piOut);
	        System.setOut(new PrintStream(poOut, true));
	        
	        // Set up System.err
	        piErr = new PipedInputStream();
	        BufferedReader brErr = new BufferedReader(new InputStreamReader(piErr));
	        poErr = new PipedOutputStream(piErr);
	        System.setErr(new PrintStream(poErr, true));
	        // Create reader threads
	        new ReaderThread(brOut).start();
	        new ReaderThread(brErr).start();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    public List<String> getLines(){
    	return lines;
    }
    
    class ReaderThread extends Thread {
    	BufferedReader br;        
        ReaderThread(BufferedReader br) {
            this.br = br;
        }        
        public void run() {
        	while(read){
        		if(br != null){
        			try{
	                	String s = br.readLine();
	                	if(s != null && !s.equals("")){
	                		lines.add(s);
	                		if(lines.size() > MAX_LINES){
	                			lines.remove(0);
	                		}
	                	}
	                }catch(IOException e){
	                	e.printStackTrace();
	                }
        		}
        	}
        
        }
    }
}
