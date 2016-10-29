package com.sx.sql;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 首页本地存储
 * 
 * @author jzf
 */
@DatabaseTable(tableName = "HOME_LAST_DATA")
public class HomeLastData {
	
	@DatabaseField(id=true)
	private int id;

	@DatabaseField
	private String data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
