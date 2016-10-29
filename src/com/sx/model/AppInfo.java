package com.sx.model;

public class AppInfo {
	private int id;
	private String appId;
	private String appName;
	private boolean state;
	private int icon;
	
	private String  statType;
	public AppInfo(String appId, String appName, int icon,String statType) {
		this.appId = appId;
		this.appName = appName;
		this.icon = icon;
		this.statType=statType;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
}
