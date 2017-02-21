package com.icesoft.libgdx.log;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;

public class LogEntry {
	private String text;
	private LogLevel level;
	private long time;
	
	public LogEntry(){
		this.time = TimeUtils.millis();
	}
	
	public LogEntry(String msg, LogLevel level) {
		this.text = msg;
		this.level = level;
		this.time = TimeUtils.millis();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Color getColor() {
		if(level == null){
			return Color.WHITE;
		}else{
			return level.getColor();
		}
	}
	
	@Override
	public String toString() {
		return time + ": " + level.getIdentifier() + ">" + text;
	}
}