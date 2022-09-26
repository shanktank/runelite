package net.runelite.client.plugins.keepassxc.proto.path;

import lombok.Data;

import java.util.List;

@Data
public class NativeMessagingManifest
{
	private List<String> allowedOrigins;
	private String description;
	private String name;
	private String path;
	private String type;
}
