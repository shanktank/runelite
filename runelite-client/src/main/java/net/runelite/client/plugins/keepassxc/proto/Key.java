package net.runelite.client.plugins.keepassxc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Key
{
	String id;
	byte[] key;
}
