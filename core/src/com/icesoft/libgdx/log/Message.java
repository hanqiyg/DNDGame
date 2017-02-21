package com.icesoft.libgdx.log;

import com.badlogic.gdx.utils.TimeUtils;

public class Message {
	public enum MessageType{
		SYSTEM,QUEST,OBJECT
	}
	private String text;
	private long time;
	private MessageType type;
	public Message(){}
	public Message(String text, MessageType type){
		this.time = TimeUtils.millis();
		this.text = text;
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}	
}
