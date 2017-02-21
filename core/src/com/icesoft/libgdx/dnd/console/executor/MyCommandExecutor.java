package com.icesoft.libgdx.dnd.console.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;
import com.icesoft.libgdx.dnd.DNDGame;
import com.icesoft.libgdx.dnd.character.GameChar;
import com.icesoft.libgdx.log.LogLevel;
import com.strongjoshua.console.CommandExecutor;


public class MyCommandExecutor extends CommandExecutor{
	private DNDGame game;
	
	public MyCommandExecutor(DNDGame game){
		this.game = game;
	}
	
	public void setLogs(int level,String msg){
		LogLevel l;
		if(LogLevel.values().length > level){
			l = LogLevel.values()[level];
		}else{
			l = LogLevel.INFO;
		}
		game.addLog(null, l, msg);
	}
	public void writeRandomPrefs(String name,int c){
		console.log("writeRandomPrefs:" + c + " time(s).");
		Preferences prefs = Gdx.app.getPreferences(name);
		long s = TimeUtils.millis();
		for(int i=0;i<c;i++){
			prefs.putInteger(String.valueOf(i), i);
		}
		long e = TimeUtils.millis();
		long t = e - s;
		console.log("timecost:" + t + "ms." + " Avg:" + t/c + "ms.");
		s = TimeUtils.millis();
		prefs.flush();
		e = TimeUtils.millis();
		t = e - s;
		console.log("timecost:" + t + "ms." + " Avg:" + t/c + "ms.");		
	}
	
	public void setpint(String name,int value){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		prefs.putInteger(name, value);
		prefs.flush();
	}
	public void setpfloat(String name,float value){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		prefs.putFloat(name, value);
		prefs.flush();
	}
	public void setpstring(String name,String value){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		prefs.putString(name, value);
		prefs.flush();
	}
	public void getpint(String name){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		console.log(String.valueOf(prefs.getInteger(name,0)));
	}
	public void getpfloat(String name){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		console.log(String.valueOf(prefs.getFloat(name,0)));
	}
	public void getpstring(String name){
		Preferences prefs = Gdx.app.getPreferences(DNDGame.P);
		console.log(prefs.getString(name,"NULL"));
	}

	public void list(){
		Method[] method =game.character.getClass().getMethods();
		for(Method m : method){			
			console.log(getMethodString(m).toString());
		}		
	}
	public String getMethodString(Method m){
		String modifier = Modifier.toString(m.getModifiers());
		Class<?> para[]= m.getParameterTypes();
		StringBuffer sb = new StringBuffer();
		sb.append(modifier + " " + m.getReturnType().getSimpleName() + "  " + m.getName() + "(");
		if(para.length > 0){
			for(int i=0;i<para.length;i++){
				if(i != (para.length - 1)){
					sb.append(para[i].getSimpleName());
					sb.append(",");
				}else{
					sb.append(para[i].getSimpleName());
					sb.append(")");
				}
			}
		}else{
			sb.append(")");
		}
		return sb.toString();
	}
	public void setint(String name,String method,int value){
		console.log("cmd:" + "setint " + name + " " + method + " " + value);
		if(name.equals("char")){
			console.log("character");
			try {
				Method m = GameChar.class.getMethod(method, int.class);	
				m.invoke(game.character,value);
				console.log("Invoked:[" + getMethodString(m)+"]");
			} catch (SecurityException e) {
				console.log(e.getMessage());
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				console.log(e.getMessage());				
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				console.log(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				console.log(e.getMessage());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				console.log(e.getMessage());
				e.printStackTrace();
			}
			return;
		}
	}
	public void setstr(String name,String method,String value){
		if(name.equals("char")){
			try {
				Method m = GameChar.class.getClass().getMethod(method, String.class);
				m.invoke(game.character,value);
			} catch (SecurityException e) {
				console.log(e.getMessage());
			} catch (NoSuchMethodException e) {
				console.log(e.getMessage());
			} catch (IllegalArgumentException e) {
				console.log(e.getMessage());
			} catch (IllegalAccessException e) {
				console.log(e.getMessage());
			} catch (InvocationTargetException e) {
				console.log(e.getMessage());
			}
			return;
		}
	}
	public void charexec(String name){
		try {
			Method m = game.character.getClass().getMethod(name, null);
			Object s = m.invoke(game.character);
			console.log(s.toString());
		} catch (SecurityException e) {
			console.log(e.getMessage());
		} catch (NoSuchMethodException e) {
			console.log(e.getMessage());
		} catch (IllegalArgumentException e) {
			console.log(e.getMessage());
		} catch (IllegalAccessException e) {
			console.log(e.getMessage());
		} catch (InvocationTargetException e) {
			console.log(e.getMessage());
		}
	}
	public void exit(){
		console.setVisible(false);
	}
}
