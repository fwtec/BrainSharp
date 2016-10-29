package com.sx.brainsharp;

import net.youmi.android.offers.OffersAdSize;
import net.youmi.android.offers.OffersBanner;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinaMobile.MobileAgent;
import com.sx.model.Canginfo;
import com.sx.sql.DbMananger;
import com.sx.util.ContentData;
import com.sx.util.DateUtil;
import com.sx.util.SharedPreferencesForLogin;

public class ContentActivity extends Activity implements OnClickListener {

	private TextView tx_content;
	private Button btn_daan;
	private TextView tx_title;
	private Button content_shoucang_app;
	String type="10001";
	
	/**
	 * 积分 Banner
	 */
	private OffersBanner mBanner;
	
	private SharedPreferencesForLogin index_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_content);
		
		index_info = new SharedPreferencesForLogin(ContentActivity.this,"pageindex");
		
		Intent intent=getIntent();
		type=intent.getStringExtra("typedate");
		Log.i("type", type);
		
		tx_content=(TextView) this.findViewById(R.id.btn_content);
		btn_daan=(Button) this.findViewById(R.id.btn_daan);
		tx_title=(TextView)this.findViewById(R.id.content_title);
		init();
		setTitle(type);
		
		if(DateUtil.isTime()){
			initad();
		}
	}
	
	public void initad(){
		// (可选)使用积分Banner-一个新的积分墙入口点，随时随地让用户关注新的积分广告
			mBanner = new OffersBanner(this, OffersAdSize.SIZE_MATCH_SCREENx60);
			RelativeLayout layoutOffersBanner = (RelativeLayout) findViewById(R.id.offersBannerLayout);
			layoutOffersBanner.addView(mBanner);
	}
	
	public void setTitle(String type){
		if(type.equals("10001")){
			tx_title.setText("搞笑类型");
		}else if(type.equals("10002")){
			tx_title.setText("幽默类型");
		}else if(type.equals("10003")){
			tx_title.setText("趣味类型");
		}else if(type.equals("10004")){
			tx_title.setText("动物类型");
		}else if(type.equals("10005")){
			tx_title.setText("经典类型");
		}else if(type.equals("10006")){
			tx_title.setText("整人类型");
		}else if(type.equals("10007")){
			tx_title.setText("综合类型");
		}else if(type.equals("10008")){
			tx_title.setText("特色类型");
		}else if(type.equals("10009")){
			tx_title.setText("风趣类型");
		}
		else{
			tx_title.setText("脑筋急转弯");
		}
	}
	public void init(){
		
		 int x=getTypeIndex(type);  //得到下标
		 String[] typecontent=getTypeData(type);
		 String content=typecontent[x];
		 String[] valuedata=content.split("#");  //得到某一个，问题和答案
		 tx_content.setText(valuedata[0]);
		
	     findViewById(R.id.btn_left).setOnClickListener(this);
	     findViewById(R.id.btn_right).setOnClickListener(this);
	     findViewById(R.id.btn_content).setOnClickListener(this);
	     findViewById(R.id.fuzhi).setOnClickListener(this);
	     findViewById(R.id.fenxiang).setOnClickListener(this);
	     findViewById(R.id.btn_daan).setOnClickListener(this);
	     findViewById(R.id.content_shoucang_app).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id=v.getId();
		switch (id) {
		case R.id.btn_left:
			int x=getTypeIndex(type);  //得到下标
			
			if(getjifencount()<100){
				if(x%7==0){
					if(DateUtil.isTime()){
						chabo();
					}
				}
			}
			
			getContentleft(type,x); //设置类型的下标
			
			int new_x=getTypeIndex(type); //新的下标
			
			String[] typecontent=getTypeData(type);
			
			String content=typecontent[new_x];
			
			String[] valuedata=content.split("#");  //得到某一个，问题和答案
			
			tx_content.setText(valuedata[0]);
			
			btn_daan.setText("查看答案");
			
			break;
		case R.id.btn_right:
			
			int y=getTypeIndex(type);
			
			if(getjifencount()<100){
				if(y%7==0){
					if(DateUtil.isTime()){
						chabo();
					}
				}
			}
			
			getContentright(type,y); //设置类型的下标
			
			int new_y=getTypeIndex(type); //新的下标
		
			String[] rtypecontent=getTypeData(type);
			
			String rcontent=rtypecontent[new_y];
			
			String[] rvaluedata=rcontent.split("#");  //得到某一个，问题和答案
			
			tx_content.setText(rvaluedata[0]); //设置内容
			
			btn_daan.setText("查看答案");
			
			break;
		case R.id.btn_daan:
			int z=getTypeIndex(type);  //得到下标
			
			String[] ztypecontent=getTypeData(type);
			
			String zcontent=ztypecontent[z];
			
			String[] zvaluedata=zcontent.split("#");  //得到某一个，问题和答案
			
			btn_daan.setText(zvaluedata[1]);
		break;
		case R.id.fenxiang:
			
			 int xx=getTypeIndex(type);  //得到下标
			 String[] xxtypecontent=getTypeData(type);
			 String xxcontent=xxtypecontent[xx];
			 String[] xxvaluedata=xxcontent.split("#");  //得到某一个，问题和答案
			
			Intent intent=new Intent(Intent.ACTION_SEND);   
	        intent.setType("text/plain");
//	        intent.setType("image/png");
	        //添加图片
//	        File f = new File(Environment.getExternalStorageDirectory() + "/Pictures/2.png");
//	        Uri u = Uri.fromFile(f);
//	        intent.putExtra(Intent.EXTRA_STREAM, R.drawable.ic_launcher);
	        
	        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");   
	        intent.putExtra(Intent.EXTRA_TEXT, xxvaluedata[0]+"\n答案是 ："+xxvaluedata[1]);    
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	        startActivity(Intent.createChooser(intent, getTitle()));
			break;
		case R.id.content_shoucang_app: //收藏
			
			int k=getTypeIndex(type);  //得到下标
			String[] ktypecontent=getTypeData(type);
			String kcontent=ktypecontent[k];
			
			DbMananger db=new DbMananger(this);
			long count=db.getCount(type, String.valueOf(k));
			if(count==0){
				Canginfo cang=new Canginfo();
				cang.setAppcontent(kcontent);
				cang.setAppindex(String.valueOf(k));
				cang.setApptype(type);
				db.insertState(cang);
				Toast.makeText(ContentActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(ContentActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
			}
			
//			String[] kvaluedata=kcontent.split("#");  //得到某一个，问题和答案
//			String cangdata=index_info.getValue("shoucangData");
//			String[] ccang=cangdata.split("&");
//			for(int i=0;i<ccang.length;i++){
//				if(ccang[i].equals(kcontent)){
//					
//				}
//			}
//			String cangcontent=cangdata+"&"+kcontent;
//			 
//			index_info.putValue("shoucangData", cangcontent);
			break;
		case R.id.fuzhi:
			 int yy=getTypeIndex(type);  //得到下标
			 String[] yytypecontent=getTypeData(type);
			 String yycontent=yytypecontent[yy];
			 String[] yyvaluedata=yycontent.split("#");  //得到某一个，问题和答案
			ClipboardManager cm =(ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
			//将文本数据复制到剪贴板
			cm.setText(yyvaluedata[0]+"答案是："+yyvaluedata[1]);
			Toast.makeText(ContentActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	public int getjifencount(){
		return  PointsManager.getInstance(this).queryPoints();// 查询积分余额
	}
	
	public void chabo(){
		// 展示插播广告，可以不调用loadSpot独立使用
				SpotManager.getInstance(ContentActivity.this).showSpotAds(
						ContentActivity.this, new SpotDialogListener() {
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
	
	//得到类型内容
	public String[] getTypeData(String s){
		ContentData content=new ContentData();
		String[] result=null;
		if(s.equals("10001")){
			result=content.getGaoxiaodata();
		}else if(s.equals("10002")){
			result=content.getYoumodata();
		}else if(s.equals("10003")){
			result=content.getQuweidata();
		}else if(s.equals("10004")){
			result=content.getDongwudata();
		}else if(s.equals("10005")){
			result=content.getJindiandata();
		}else if(s.equals("10006")){
			result=content.getZhengrendata();
		}else if(s.equals("10007")){
			result=content.getZonghedata();
		}else if(s.equals("10008")){
			result=content.getTesedata();
		}else if(s.equals("10009")){
			result=content.getFengqudata();
		}
		return result;
	}
	
	//设置类型下标(下一个)
	public void getContentright(String type,int i){
		if(getTypeData(type).length==i+1){
			i=0;
		}
		if(type.equals("10001")){
			index_info.putValue("gaoxiao_index", String.valueOf(i+1));
		}
		if(type.equals("10002")){
			index_info.putValue("youmo_index", String.valueOf(i+1));
		}
		if(type.equals("10003")){
			index_info.putValue("quwei_index", String.valueOf(i+1));
		}
		if(type.equals("10004")){
			index_info.putValue("dongwu_index", String.valueOf(i+1));
		}
		if(type.equals("10005")){
			index_info.putValue("jindian_index", String.valueOf(i+1));
		}
		if(type.equals("10006")){
			index_info.putValue("zhengren_index", String.valueOf(i+1));
		}
		if(type.equals("10007")){
			index_info.putValue("zonghe_index", String.valueOf(i+1));
		}
		if(type.equals("10008")){
			index_info.putValue("tese_index", String.valueOf(i+1));
		}
		if(type.equals("10009")){
			index_info.putValue("fengqu_index", String.valueOf(i+1));
		}
	}
	//设置类型下标(上一个)
	public void getContentleft(String type,int i){
		if(type.equals("10001")){
			if(i==0){
				index_info.putValue("gaoxiao_index", String.valueOf(0));
			}else{
				index_info.putValue("gaoxiao_index", String.valueOf(i-1));
			}
		}else if(type.equals("10002")){
			if(i==0){
				index_info.putValue("youmo_index", String.valueOf(0));
			}else{
				index_info.putValue("youmo_index", String.valueOf(i-1));
			}
		}else if(type.equals("10003")){
			if(i==0){
				index_info.putValue("quwei_index", String.valueOf(0));
			}else{
				index_info.putValue("quwei_index", String.valueOf(i-1));
			}
		}else if(type.equals("10004")){
			if(i==0){
				index_info.putValue("dongwu_index", String.valueOf(0));
			}else{
				index_info.putValue("dongwu_index", String.valueOf(i-1));
			}
		}else if(type.equals("10005")){
			if(i==0){
				index_info.putValue("jindian_index", String.valueOf(0));
			}else{
				index_info.putValue("jindian_index", String.valueOf(i-1));
			}
		}else if(type.equals("10006")){
			if(i==0){
				index_info.putValue("zhengren_index", String.valueOf(0));
			}else{
				index_info.putValue("zhengren_index", String.valueOf(i-1));
			}
		}else if(type.equals("10007")){
			if(i==0){
				index_info.putValue("zonghe_index", String.valueOf(0));
			}else{
				index_info.putValue("zonghe_index", String.valueOf(i-1));
			}
		}else if(type.equals("10008")){
			if(i==0){
				index_info.putValue("tese_index", String.valueOf(0));
			}else{
				index_info.putValue("tese_index", String.valueOf(i-1));
			}
		}else if(type.equals("10009")){
			if(i==0){
				index_info.putValue("fengqu_index", String.valueOf(0));
			}else{
				index_info.putValue("fengqu_index", String.valueOf(i-1));
			}
		}
	}
	
	//得到类型下标
	public int getTypeIndex(String type){
		int index=0;
		if(type.equals("10001")){
			if(index_info.getValue("gaoxiao_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("gaoxiao_index"));
			}
		}else if(type.equals("10002")){
			if(index_info.getValue("youmo_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("youmo_index"));
			}
		}else if(type.equals("10003")){
			if(index_info.getValue("quwei_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("quwei_index"));
			}
		}else if(type.equals("10004")){
			if(index_info.getValue("dongwu_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("dongwu_index"));
			}
		}else if(type.equals("10005")){
			if(index_info.getValue("jindian_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("jindian_index"));
			}
		}else if(type.equals("10006")){
			if(index_info.getValue("zhengren_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("zhengren_index"));
			}
		}else if(type.equals("10007")){
			if(index_info.getValue("zonghe_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("zonghe_index"));
			}
		}else if(type.equals("10008")){
			if(index_info.getValue("tese_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("tese_index"));
			}
		}else if(type.equals("10009")){
			if(index_info.getValue("fengqu_index")==null){
				index=0;
			}else{
				index=Integer.parseInt(index_info.getValue("fengqu_index"));
			}
		}
		return index;
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
