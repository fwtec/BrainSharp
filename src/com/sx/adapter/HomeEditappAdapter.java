package com.sx.adapter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sx.brainsharp.R;
import com.sx.model.AppInfo;

public class HomeEditappAdapter extends BaseAdapter {

	private List<AppInfo> list;
	private Context mContext;
	private boolean isShowDelete;
	String MODE_WORLD_WRITEABLE="app_list";
	//删除后的list
	private ArrayList<AppInfo> mlist;
	
	private Context context;
	
	public void setContext(Context context) {
		this.context = context;
	}

	public HomeEditappAdapter(Context mContext) {
		this.mContext = mContext;

	}

	public void setData(List<AppInfo> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	//标记删除item
	public void setIsShowDelete(boolean isShowDelete) {
			this.isShowDelete = isShowDelete;	
		
		notifyDataSetChanged();
	}
	
	//删除item
	public void delete(int position) {
		AppInfo appinfo = list.get(position);
		appinfo.setState(false);
		list.remove(position);
		notifyDataSetChanged();

	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppInfo info = list.get(position);
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.edit_app_icon, null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.edit_app_icon_img);
			holder.name_tv = (TextView) convertView
					.findViewById(R.id.edit_app_icon_tv);
			holder.deleteView = convertView
					.findViewById(R.id.edit_app_icon_clear);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.deleteView
				.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);// 设置删除按钮是否显示
		holder.img.setImageDrawable(mContext.getResources().getDrawable(
				info.getIcon()));
		holder.name_tv.setText(info.getAppName());
		if(position==list.size()-1){
			holder.deleteView.setVisibility(View.GONE);
		}
		
		return convertView;

	}

	class Holder {
		private ImageView img;
		// private ImageView deleteView;
		private TextView name_tv;
		private View deleteView;
	}
	
	private OnTouchListener onTouchListener=new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				isShowDelete=false;
				break;

			default:
				break;
			}
			return false;
		}
	};
	
}
