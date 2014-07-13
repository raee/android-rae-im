package com.rae.core.im;

/**
 * IM监听接口
 * 
 * @author Chenrui
 * 
 */
public interface IMListener
{
	public void onIMError(IMException e);
	
	IMLoginListener getLoginListener();
}
