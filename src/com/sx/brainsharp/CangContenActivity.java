package com.sx.brainsharp;

import com.chinaMobile.MobileAgent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CangContenActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cang_content);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobileAgent.onResume(this);
	}
	@Override
	protected void onPause() {

		// TODO 自动生成方法存根
		super.onPause();
		MobileAgent.onPause(this);
	}
	
}
