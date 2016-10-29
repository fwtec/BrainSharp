/*
 * 仿微信公众平台消息界面
 * @作者：秦元培
 * 
 */
package com.sx.chartcart;

public class BaseCard 
{
   private int mDrawable;

   private String mDescription;

   public BaseCard(int Drawable,String Description)
   {
	   this.mDrawable=Drawable;
	   this.mDescription=Description;
   }
   
   public int getDrawable() 
   {
	return mDrawable;
   }
   
   public void setDrawable(int mDrawable) 
   {
	this.mDrawable = mDrawable;
   }
   
   public String getDescription() 
   {
	return mDescription;
   }
   
   public void setDescription(String mDescription) 
   {
	this.mDescription = mDescription;
   }
   
}
