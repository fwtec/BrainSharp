package com.sx.chartcart;

import java.util.ArrayList;
import java.util.List;

public class Card extends BaseCard {


	private List<BaseCard> mSubCards;

	public Card(int Drawable, String Description) 
	{
		super(Drawable, Description);
		mSubCards=new ArrayList<BaseCard>();
	}
	
	public void AppendCard(BaseCard mCard)
	{
		mSubCards.add(mCard);
	}
	
	public List<BaseCard> getSubCards() 
	{
		return mSubCards;
	}

}
