package com.sx.chartcart;

import java.util.List;

import com.sx.brainsharp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardAdapter extends BaseAdapter {

	private Context mContext;
	private List<Card> mCards;
	
	private TextView News_Title1;
	
	public CardAdapter(Context mContext,List<Card> mCards)
	{
		this.mContext=mContext;
		this.mCards=mCards;
	}
	@Override
	public int getCount() 
	{
		return mCards.size();
	}

	@Override
	public Object getItem(int Index) 
	{
		return mCards.get(Index);
	}

	@Override
	public long getItemId(int Index) 
	{
		return Index;
	}

	@Override
	public View getView(final int Index, View mView, ViewGroup mParent) 
	{
		 mView=LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
	     //头条消息
	     ImageView News_Pic=(ImageView)mView.findViewById(R.id.News_Pic);
//	     News_Pic.setImageResource(mCards.get(Index).getDrawable());
	     
	     TextView News_Title=(TextView)mView.findViewById(R.id.News_Title);
	     News_Title.setText(mCards.get(Index).getDescription());

	     News_Title1 =(TextView)mView.findViewById(R.id.News_Title1);
	     News_Title1.setText(mCards.get(Index).getSubCards().get(0).getDescription());
	     
//	     mView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(v.getId()==Index){
//					Toast.makeText(mContext, "第"+Index+"个", Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
	     News_Pic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(mContext, "第"+Index+"个", Toast.LENGTH_SHORT).show();
			}
		});
	     
		return mView;
	}
}
