package com.rae.core.im;

/**
 * IM异常
 * 
 * @author 陈睿
 * 
 */
public class IMException extends Exception
{
	private static final long	serialVersionUID	= -3532362493210819151L;
	private int					mCode;
	
	public IMException()
	{
	}
	
	public IMException(String msg)
	{
		super(msg);
	}
	
	public IMException(int code, String msg)
	{
		super(msg);
		this.mCode = code;
	}
	
	public IMException(Throwable e)
	{
		super(e);
	}
	
	public IMException(String msg, Throwable e)
	{
		super(msg, e);
	}
	
	public int getCode()
	{
		return mCode;
	}
}
