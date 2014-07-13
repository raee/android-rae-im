package com.rae.core.im;

import org.jivesoftware.smack.XMPPException;

import com.rae.core.im.model.User;

import android.os.AsyncTask;

public class IMConnectionThread extends AsyncTask<IMConnection, Integer, User>
{
	private IMConnection	mIMConnection;
	
	@Override
	protected User doInBackground(IMConnection... params)
	{
		mIMConnection = params[0];
		User user = null;
		// 判断连接状态
		if (isLogined())
		{
			notifyError(203, "连接已经建立");
			return null;
		}
		try
		{
			if (!mIMConnection.isConnected())
			{
				mIMConnection.connect(); // 没有连接，先进行连接
			}
			
			mIMConnection.login();
		}
		catch (XMPPException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	protected void onPostExecute(User result)
	{
		super.onPostExecute(result);
		notifySuccess(result);
	}
	
	public void run(IMConnection connection)
	{
		if (isRuning())
		{
			notifyError(202, "服务正在连接");
		}
		else
		{
			this.execute(connection);
		}
	}
	
	/**
	 * 线程是否已经执行
	 * 
	 * @return
	 */
	private boolean isRuning()
	{
		if (this.getStatus() == Status.RUNNING) return true;
		else return false;
	}
	
	/**
	 * 是否已经登录
	 * 
	 * @return
	 */
	private boolean isLogined()
	{
		if (mIMConnection.isConnected() && mIMConnection.isAuthenticated()) return true;
		else return false;
	}
	
	private void notifyError(int code, String msg)
	{
		notifyError(new IMException(code, msg));
	}
	
	private void notifyError(IMException e)
	{
		for (IMListener listener : mIMConnection.getListeners())
		{
			IMLoginListener loginListener = listener.getLoginListener();
			if (loginListener != null) loginListener.onLoginError(e);
		}
	}
	
	private void notifySuccess(User user)
	{
		for (IMListener listener : mIMConnection.getListeners())
		{
			IMLoginListener loginListener = listener.getLoginListener();
			if (loginListener != null) loginListener.onLoginSuccess(user);
		}
	}
	
}
