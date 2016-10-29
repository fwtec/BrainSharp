package com.sx.brainsharp;

import java.util.List;

import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.chinaMobile.MobileAgent;
import com.sx.adapter.HomeAddappAdapter;
import com.sx.chartcart.ChartActivity;
import com.sx.model.AppInfo;
import com.sx.util.CustomDialog;
import com.sx.util.DateUtil;

public class HomeAddappActivity extends Activity {
	
	private ListView listView;
	private HomeAddappAdapter addappAdapter;
	private List<AppInfo> appInfos;
	private Button confim_btn;
	private Button btn_gd;
//	private DatabaseHelper sqlHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

	private long firstime = 0;
	private Button btn_cang;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_add_app);
		
		
		
		btn_cang=(Button) this.findViewById(R.id.Button_left_cang);
		btn_cang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 Intent intent=new Intent();
			     intent.setClass(HomeAddappActivity.this, ChartActivity.class);
			     startActivity(intent); //启动一个新的Activity
			}
		});
		
		
		initView();
		
		btn_gd=(Button) this.findViewById(R.id.home_add_app_btn_f);
		
		if(DateUtil.isTime()){
			btn_gd.setText("关于");
		}
		
		btn_gd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//展示全屏的积分墙界面
				
				if(DateUtil.isTime()){

					int jifencount=getjifencount();
					if(jifencount>100){
						jifenqiang();
					}else{
						//通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
		                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAddappActivity.this);
		                //    设置Title的图标
		                builder.setIcon(R.drawable.ic_launcher);
		                //    设置Title的内容
		                builder.setTitle("温馨提示");
		                //    设置Content来显示一个信息
		                builder.setMessage("您的积分为"+jifencount+" , 亲爱的小伙伴，当你的积分大于100时，可以直接进入，无广告模式，还等什么，赶快去获取积分吧。");
		                //    设置一个PositiveButton
		                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		                {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                    	jifenqiang();
		                    }
		                });
		                //    设置一个NegativeButton
		                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		                {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                        Toast.makeText(HomeAddappActivity.this, "取消", Toast.LENGTH_SHORT).show();
		                    }
		                });
		                //    显示出该对话框
		                builder.show();	
					}
				}else{
					 Intent intent=new Intent();
				     intent.setClass(HomeAddappActivity.this, OtherActivity.class);
				     startActivity(intent); //启动一个新的Activity
				}
				
			}
		});
	}
	
	public int getjifencount(){
		return  PointsManager.getInstance(this).queryPoints();// 查询积分余额
	}
	
	public void jifenqiang(){
		OffersManager.getInstance(this).showOffersWall();
	}
	
	public void initView(){
		listView=(ListView) findViewById(R.id.home_add_app_listview);
		//完成
//		confim_btn=(Button) findViewById(R.id.home_add_app_btn);
//		confim_btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				//TODO
//				//对数据库进行操作 
////				updateHomeApp();
////				finish();
//			}
//		});
		addappAdapter=new HomeAddappAdapter(HomeAddappActivity.this);
		addappAdapter.setData(HomeActivity.allApps);
		listView.setAdapter(addappAdapter);
	}
	
	private void updateHomeApp(){
//		ArrayList<HomeLastData> hldList = new ArrayList<HomeLastData>();
//		for(AppInfo appinfo:HomeActivity.allApps){
//			HomeLastData hld = new HomeLastData();
//			hld.setId(appinfo.getId());
//			hld.setData(appinfo.getAppId());
//			hldList.add(hld);
//		}
//		try {
//			Dao<HomeLastData,Integer> dao = sqlHelper.getHomeLastDataDao();
//			dao.delete(hldList);
//			for(AppInfo appinfo:HomeActivity.addAppInfos){
//				if(!appinfo.getAppId().equals(CommonUtil.appId10)){
//					HomeLastData _hld = new HomeLastData();
//					_hld.setId(appinfo.getId());
//					_hld.setData(appinfo.getAppId());
//					dao.createOrUpdate(_hld);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
//	
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			Intent intent = new Intent(HomeAddappActivity.this, HomeActivity.class);
//			setResult(RESULT_OK, intent);
//			finish();
//			return false;
//		}
//		return false;
//	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (isShowDelete && keyCode == KeyEvent.KEYCODE_BACK) {
////			isShowDelete = false;
////			editappAdapter.setIsShowDelete(isShowDelete);
////			editappAdapter.notifyDataSetChanged();
////			return true;
//		}else{
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				long secondtime = System.currentTimeMillis();
				if (secondtime - firstime > 3000) {
					Toast.makeText(HomeAddappActivity.this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
					firstime = System.currentTimeMillis();
					return true;
				} else {
					finish();
					System.exit(0);
				}
		}
//	}
		return super.onKeyDown(keyCode, event);
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
