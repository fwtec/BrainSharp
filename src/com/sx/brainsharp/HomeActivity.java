package com.sx.brainsharp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;

import com.chinaMobile.MobileAgent;
import com.sx.adapter.HomeEditappAdapter;
import com.sx.model.AppInfo;
import com.sx.sql.DatabaseHelper;
import com.sx.sql.DbMananger;
import com.sx.util.CommonUtil;
import com.sx.util.DateUtil;

public class HomeActivity extends BaseActivity {

	private GridView app_gridview;
	public static List<AppInfo> allApps = new ArrayList<AppInfo>();
	public static LinkedList<AppInfo> addAppInfos = new LinkedList<AppInfo>();
	protected DatabaseHelper sqlHelper;
	private boolean isShowDelete;
	private HomeEditappAdapter editappAdapter;
	private Vibrator mVibrator;
	private long firstime = 0;

	private String appId = "300008447500";
	private String channelId = "channe_mm";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		MobileAgent.init(this, appId, channelId);
		
		AdManager.getInstance(this).init("815f3f14c802f7f1",
				"7a2354124217599b", false);
		// 加载插播资源
		SpotManager.getInstance(this).loadSpotAds();

		// 初始化接口，应用启动的时候调用
		// 参数：appId, appSecret, 调试模式
//		AdManager.getInstance(this).init("cfdbdd2786ea88ea ","d8edde7d10dd0073", false);
		
		// 如果使用积分广告，请务必调用积分广告的初始化接口:
		OffersManager.getInstance(this).onAppLaunch();
		
		// (可选)开启用户数据统计服务,默认不开启，传入false值也不开启，只有传入true才会调用
		AdManager.getInstance(this).setUserDataCollect(true);
		
		// (可选)注册积分监听-随时随地获得积分的变动情况
//		PointsManager.getInstance(this).registerNotify(this);
		
		DbMananger db = new DbMananger(this);
		// boolean hasAwardPoint = db.queryLog(time);


		// 获得数据库
		// sqlHelper = getHelper();
		if(DateUtil.isTime()){
			showad();
		}
		initView();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, HomeAddappActivity.class);
				startActivity(intent); // 启动一个新的Activity
				finish();
			}
		};
		timer.schedule(task, 1000 * 5); // 10秒后
	}
	
	public void showad(){
		// 展示插播广告，可以不调用loadSpot独立使用
		SpotManager.getInstance(HomeActivity.this).showSpotAds(
				HomeActivity.this, new SpotDialogListener() {
					@Override
					public void onShowSuccess() {
						Log.i("YoumiAdDemo", "展示成功");
					}

					@Override
					public void onShowFailed() {
						Log.i("YoumiAdDemo", "展示失败");
					}

				}); // //
	}

	public void initView() {
		// mVibrator = (Vibrator) HomeActivity.this
		// .getSystemService(Context.VIBRATOR_SERVICE);
		// app_gridview=(GridView) findViewById(R.id.gridview);
		initAppInfos();
		editappAdapter = new HomeEditappAdapter(HomeActivity.this);
		editappAdapter.setData(addAppInfos);
		// app_gridview.setAdapter(editappAdapter);

	}

	// 初始化应用图标
	private void initAppInfos() {
		// Map<String,String> homeAppMap = getHomeAppMapFromSql();
		for (int i = 0; i < CommonUtil.MainAppApp.length; i++) {
			AppInfo appInfo = (CommonUtil.getApp(CommonUtil.MainAppApp[i]));
			appInfo.setId(i + 1);
			allApps.add(appInfo);
		}
		AppInfo _appInfo = (CommonUtil.getApp(CommonUtil.appId10));
		addAppInfos.add(_appInfo);
	}

	// protected DatabaseHelper getHelper() {
	// if(sqlHelper==null){
	// sqlHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
	// }
	// return sqlHelper;
	// }

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		editappAdapter.notifyDataSetChanged();
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (isShowDelete && keyCode == KeyEvent.KEYCODE_BACK) {
			isShowDelete = false;
			editappAdapter.setIsShowDelete(isShowDelete);
			editappAdapter.notifyDataSetChanged();
			return true;
		} else {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				long secondtime = System.currentTimeMillis();
				if (secondtime - firstime > 3000) {
					Toast.makeText(HomeActivity.this, "再按一次返回键退出",
							Toast.LENGTH_SHORT).show();
					firstime = System.currentTimeMillis();
					return true;
				} else {
					finish();
					System.exit(0);
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
