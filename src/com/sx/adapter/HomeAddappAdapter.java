package com.sx.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sx.brainsharp.ContentActivity;
import com.sx.brainsharp.R;
import com.sx.model.AppInfo;

public class HomeAddappAdapter extends BaseAdapter{
	
	private Context context;
	private List<AppInfo> list;
	private List<Boolean> mChecked;
	private boolean flag;
	public static Map<Integer, Boolean> isSelected; 
	HashMap<Integer,View> map = new HashMap<Integer,View>();


	public HomeAddappAdapter(Context context) {
		this.context=context;
	}


	public void setData(List<AppInfo> list){
		this.list=list;
		mChecked = new ArrayList<Boolean>();  
        for(int i=0;i<list.size();i++){  
            mChecked.add(false);  
        }
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if(list==null){
			return 0;
		}
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		if(list==null){
			return null;
		}
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup viewGroup) {
		final AppInfo appInfo=list.get(position);
		final Holder holder;
		if(convertView==null){
			holder=new Holder();
			convertView=LayoutInflater.from(context).inflate(R.layout.home_add_app_item, null);
			holder.app_icon=(ImageView) convertView.findViewById(R.id.home_add_app_item_icon);
			holder.app_name=(TextView) convertView.findViewById(R.id.home_add_app_item_name);
//			holder.check_state=(CheckBox) convertView.findViewById(R.id.home_add_app_item_checkbox);
			holder.layout=(RelativeLayout) convertView.findViewById(R.id.home_add_app_item_layout);
			map.put(position, convertView);
			convertView.setTag(holder);
		}else{

			holder=(Holder) convertView.getTag();
		}
		holder.app_icon.setImageDrawable(context.getResources().getDrawable(appInfo.getIcon()));
		holder.app_name.setText(appInfo.getAppName());
//		holder.check_state.setChecked(appInfo.isState());
		
		holder.layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(position==0){
//					Toast.makeText(context, "第一个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10001");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==1){
//					Toast.makeText(context, "第二个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10002");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==2){
//					Toast.makeText(context, "第三个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10003");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==3){
//					Toast.makeText(context, "第四个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10004");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==4){
//					Toast.makeText(context, "第五个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10005");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==5){
//					Toast.makeText(context, "第六个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10006");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==6){
//					Toast.makeText(context, "第七个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10007");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==7){
//					Toast.makeText(context, "第八个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10008");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}else if(position==8){
//					Toast.makeText(context, "第九个", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.putExtra("typedate", "10009");
				    intent.setClass(context, ContentActivity.class);
				    context.startActivity(intent);
				}
				
			}

			private void startActivity(Intent intent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return convertView;
	}
	
	class Holder{
		private ImageView app_icon;
		private TextView app_name;
		public CheckBox check_state;
		private RelativeLayout layout;
	}

}
