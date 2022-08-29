package net.runelite.client.plugins.keepassxc;

public class DatabaseClosedException extends KeePassException
{
	protected DatabaseClosedException(int code, String message)
	{
		super(code, message);
	}
}
