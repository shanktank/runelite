package net.runelite.client.plugins.keepassxc.proto;

import java.io.IOException;

public class IOTimeoutException extends IOException
{
	public IOTimeoutException()
	{
		super("Hit deadline");
	}

	public IOTimeoutException(Throwable i)
	{
		super("Hit deadline", i);
	}
}
