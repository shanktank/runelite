package net.runelite.client.plugins.keepassxc.proto;

public class DatabaseClosedException extends KeePassException
{
	protected DatabaseClosedException(int code, String message)
	{
		super(code, message);
	}
}
