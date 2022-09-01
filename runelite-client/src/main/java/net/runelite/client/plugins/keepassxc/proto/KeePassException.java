package net.runelite.client.plugins.keepassxc.proto;

import lombok.Getter;

import java.io.IOException;

public class KeePassException extends IOException
{
	@Getter
	private final int errorCode;

	protected KeePassException(int code, String message)
	{
		super(message);
		errorCode = code;
	}

	public static KeePassException create(int code, String message)
	{
		switch (code) {
			case 1:
				return new DatabaseClosedException(code, message);
			case 15:
				return new NoLoginsFound(code, message);
			default:
				return new KeePassException(code, message + "(" + code + ")");
		}
	}
}
