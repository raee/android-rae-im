package com.rae.core.im;

import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.rae.core.im.model.User;

public class IMConnection extends XMPPConnection
{
	private IMConnectionThread	mConnectionThread	= null;
	private IMConfig			mConfig;
	private List<IMListener>	mListeners;
	private User				mUser;
	
	public IMConnection()
	{
		super(new IMConfig());
		mConfig = (IMConfig) config;
		mListeners = new ArrayList<IMListener>();
		this.mConnectionThread = new IMConnectionThread();
	}
	
	public void addListener(IMListener listener)
	{
		this.mListeners.add(listener);
	}
	
	public void connect(User user)
	{
		this.setUser(user);
		this.mConnectionThread.run(this);
	}
	
	/**
	 * 登录，请调用connect(user)进行登录
	 * 
	 * @throws XMPPException
	 */
	public void login() throws XMPPException
	{
		User user = getUsers();
		login(user.getUsername(), user.getPassword());
	}
	
	@Override
	@Deprecated
	public void login(String username, String password) throws XMPPException
	{
		this.login(username, password, mConfig.getResources());
	}
	
	@Override
	@Deprecated
	public synchronized void login(String username, String password,
			String resource) throws XMPPException
	{
		super.login(username, password, resource);
	}
	
	public void setUser(User user)
	{
		this.mUser = user;
	}
	
	public User getUsers()
	{
		return mUser;
	}
	
	public List<IMListener> getListeners()
	{
		return this.mListeners;
	}
	
}
