package com.rae.core.im;

import com.rae.core.im.model.User;

/**
 * 登录回调接口
 * 
 * @author Chenrui
 * 
 */
public interface IMLoginListener
{
	void onLoginSuccess(User user);
	
	void onLoginError(IMException e);
}
