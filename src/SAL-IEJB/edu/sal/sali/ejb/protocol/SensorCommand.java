package edu.sal.sali.ejb.protocol;

import java.util.Hashtable;

public class SensorCommand {
	
	private String sid;
	private int cid;
	
	private Hashtable<String, String> args;
	
	public SensorCommand(String sid, int cid){
		this.sid = sid;
		this.cid = cid;
		args = new Hashtable<String, String>();
	}
	
	public void addArg(String name, String parameter){
		args.put(name, parameter);
	}
	
	public String getArg(String name){
		return args.get(name);
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
}
