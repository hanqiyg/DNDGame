package com.icesoft.libgdx.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class IConsoleLog {
	public static final String FILENAME = "Message.dat";
	public static final String CHARSET = "UTF-8";
	private FileHandle file = Gdx.files.local(FILENAME);
	private Json json = new Json();
	public void save(Message msg){
		if(msg != null){
			String jsonMessage = json.toJson(msg) + System.getProperty("line.separator");			
			if(file.exists()){
				if(file.isDirectory()){
					System.out.println("Error: File[" + FILENAME + "] is a exist directory." );
				}else{
					file.writeString(jsonMessage, true, "UTF-8");
				}
			}else{
				file.writeString(jsonMessage, false, CHARSET);
			}
		}		
	}
	public List<Message> getMessages(int offset,int count){
		List<Message> messages = new ArrayList<Message>();
		Reader is = file.reader();
		BufferedReader br = new BufferedReader(is);
		for(int i = 0; i < offset + count; i++){
			try {
				String s = br.readLine();
				if(s != null){
					if(i >= offset){
						Message m = json.fromJson(Message.class, s);
						if(m != null){
							messages.add(m);
						}
					}
				}
			} catch (IOException e) {
				 break;
			}
		}
		return messages;
	}
	public void test(){
		for(int i=0;i<5;i++){
			save(new Message(String.valueOf(i), Message.MessageType.SYSTEM));
		}
		List<Message> messages = getMessages(5,10);
		for(Message m : messages){
			System.out.println("1" + json.toJson(m));
		}
	}
}
