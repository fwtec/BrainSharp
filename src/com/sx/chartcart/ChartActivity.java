/*
 * 仿微信公众号消息界面
 * 作者：秦元培
 * 时间：2013年12月30日
 * 这个程序的原理是重写适配器，然后绑定列表。我觉得微信的实现原理应该使用的ScrollView吧
 * 这个程序目前的缺点有：
 * 1、Card和BaseCard两个类还需要完善
 * 2、滚动条不是在屏幕边缘，而是在卡片边缘，也就是说这个方法本身有问题.[解决方法：scrollbarStyle="outsideOverlay]
 * 3、两个卡片间的间距问题无法解决，尝试着用了divider和dividerHeight属性，发现有一定的色差[解决方法：android:divider="@null"]
 * 4、如果要实现微信的那个通知，需要增加一个布局、一个类型判断
 * 5、当图片较多时解决内存消耗的问题
 */
package com.sx.chartcart;

import java.util.ArrayList;
import java.util.List;

import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;

import com.chinaMobile.MobileAgent;
import com.sx.brainsharp.HomeActivity;
import com.sx.brainsharp.HomeAddappActivity;
import com.sx.brainsharp.R;
import com.sx.model.Canginfo;
import com.sx.sql.DbMananger;
import com.sx.util.CommonUtil;
import com.sx.util.DateUtil;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

public class ChartActivity extends Activity {

	private ListView mListView;
	private CardAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_main);
		mListView=(ListView)findViewById(R.id.ListView);
		mAdapter=new CardAdapter(this,getItems());
		mListView.setAdapter(mAdapter); 
		
//		if(getjifencount()<100){
		if(DateUtil.isTime()){
			chabo();
		}
//		}
	}
	
	public int getjifencount(){
		return  PointsManager.getInstance(this).queryPoints();// 查询积分余额
	}

	private List<Card> getItems() 
	{
		List<Card> mCards=new ArrayList<Card>();
		final DbMananger db = new DbMananger(this);
		List<Canginfo> canginfo = db.getScrollData();
//		Toast.makeText(HomeAddappActivity.this, "你已收藏"+canginfo.size()+"条", Toast.LENGTH_SHORT).show();
		if(canginfo.size()==0){
			
			Card mCard=new Card(R.drawable.pic_0,"亲你还没有收藏哟");
			BaseCard mBaseCard1=new BaseCard(R.drawable.pic_0,"");
			mCard.AppendCard(mBaseCard1);
			mCards.add(mCard);
		}else{
			for(int i=0;i<canginfo.size();i++){
				String content=canginfo.get(i).getAppcontent();
				String type=canginfo.get(i).getApptype();
				String[] tcontent=content.split("#");
				Card mCard=new Card(R.drawable.pic_0,CommonUtil.getApp(type).getAppName());
				BaseCard mBaseCard1=new BaseCard(R.drawable.pic_0,tcontent[0]+"\n答案 :"+tcontent[1]);
				mCard.AppendCard(mBaseCard1);
				mCards.add(mCard);
			}
			
		}
			
		return mCards;
	}
	
	public void chabo(){
		// 展示插播广告，可以不调用loadSpot独立使用
				SpotManager.getInstance(ChartActivity.this).showSpotAds(
						ChartActivity.this, new SpotDialogListener() {
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
	
	public String typeString(String type){
		String contnet="";
		if(type.equals("10001")){
			contnet="搞笑类型";
		}else if(type.equals("10002")){
			contnet="幽默类型";
		}else if(type.equals("10003")){
			contnet="趣味类型";
		}else if(type.equals("10004")){
			contnet="动物类型";
		}else if(type.equals("10005")){
			contnet="经典类型";
		}else if(type.equals("10006")){
			contnet="整人类型";
		}else if(type.equals("10007")){
			contnet="综合类型";
		}else if(type.equals("10008")){
			contnet="特色类型";
		}else if(type.equals("10009")){
			contnet="风趣类型";
		}
		else{
			contnet="脑筋急转弯";
		}
		return contnet;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
