package com.icesoft.libgdx.log;


import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

public class Log {
	public static final String T = "Log";
	public static final String PREF_NAME = "DNDGameLog";
	public static final String PREF_OFFSET = "DNDGameLogCount";
	public static final String PREF_PREFIX = "LINE";
	public static final int CACHE_COUNT = 50;
	private int count;
	private Json json;
	private int offset = 0;
	private Preferences prefs;	

	public Log() {
		prefs = Gdx.app.getPreferences(PREF_NAME);
		offset = prefs.getInteger(PREF_OFFSET, 0);
		json = new Json();
	}
	public int getOffset(){
		return offset;
	}
	public void addEntry(String msg, LogLevel level) {
		Gdx.app.debug(T, "addEntry [" + offset + "]");
		saveEntry(new LogEntry(msg, level));
	}
	private void saveEntry(LogEntry o) {
		Gdx.app.debug(T, "saveEntry");
		if(o != null){
			String str = json.toJson(o, LogEntry.class);
			offset++;
			count++;
			prefs.putString(PREF_PREFIX + String.valueOf(offset), str);
			prefs.putInteger(PREF_OFFSET, offset);
			Gdx.app.debug(T, "saveEntry[" + offset +"]:" + str);
		}
		if(count > CACHE_COUNT){
			flush();
		}
	}
	public void flush(){
		prefs.flush();
		count = 0;
	}
	public List<LogEntry> getList(int count,int offset){
		List<LogEntry> logs = new ArrayList<LogEntry>();
		if(offset + count <= this.offset){
			for(int i=offset;i<offset+count;i++){
				LogEntry l = getEntry(i);
				if(l != null){
					logs.add(l);
				}
			}
		}else if(offset <= this.offset){
			for(int i=offset;i<this.offset;i++){
				LogEntry l = getEntry(i);
				if(l != null){
					logs.add(l);
				}
			}
		}
		return logs;
	}
	private LogEntry getEntry(int offset){
		if(offset > 0 && offset <= this.offset){
			String str = prefs.getString(PREF_PREFIX + String.valueOf(offset), "");
			LogEntry log = json.fromJson(LogEntry.class, str);
			if(log != null){
				return log;
			}
		}
		return null;
	}
}
