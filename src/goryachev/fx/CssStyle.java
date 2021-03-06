// Copyright © 2016-2018 Andy Goryachev <andy@goryachev.com>
package goryachev.fx;
import goryachev.common.util.FH;
import java.util.concurrent.atomic.AtomicLong;


/**
 * CSS Style.
 */
public class CssStyle
{
	private final String name;
	private static final AtomicLong seq = new AtomicLong();
	
	
	public CssStyle(String name)
	{
		this.name = name + "_" + seq.incrementAndGet();
	}
	
	
	public CssStyle()
	{
		this("");
	}
	
	
	public boolean equals(Object x)
	{
		if(x == this)
		{
			return true;
		}
		else if(x instanceof CssStyle)
		{
			CssStyle z = (CssStyle)x;
			return name.equals(z.name);
		}
		else
		{
			return false;
		}
	}
	
	
	public int hashCode()
	{
		int h = FH.hash(CssStyle.class);
		h = FH.hash(h, name);
		return h;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public String toString()
	{
		return getName();
	}
}
