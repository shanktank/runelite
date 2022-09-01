package net.runelite.client.plugins.keepassxc.proto.msg;

import abex.os.keepassxc.proto.Key;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;

public class GetLogins
{
	private GetLogins(){}

	public static final String ACTION = "get-logins";

	@Data
	@Builder
	public static class Request {
		@Builder.Default
		String action = ACTION;
		String url;
		String submitUrl;
		String httpAuth;
		Collection<Key> keys;
	}

	@Data
	public static class Response {
		List<Entry> entries;
	}

	@Data
	public static class Entry {
		String login;
		String name;
		String password;
	}
}
