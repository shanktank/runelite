package net.runelite.client.plugins.keepassxc.proto;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Base64;

public class Base64Adapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]>
{
	@Override
	public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context)
	{
		return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
	}

	@Override
	public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return Base64.getDecoder().decode(json.getAsString());
	}
}
