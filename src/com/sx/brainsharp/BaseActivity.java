package com.sx.brainsharp;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	protected void click(String appId){
		if ("10001".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第1个应用", Toast.LENGTH_SHORT).show();
		}else if ("10002".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第2个应用", Toast.LENGTH_SHORT).show();
		}else if ("10003".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第3个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10004".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第4个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10005".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第5个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10006".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第6个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10007".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第7个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10008".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第8个应用", Toast.LENGTH_SHORT).show();
		}
		else if ("10009".equals(appId)) {
			Toast.makeText(BaseActivity.this, "第9个应用", Toast.LENGTH_SHORT).show();
		}
	}

}
