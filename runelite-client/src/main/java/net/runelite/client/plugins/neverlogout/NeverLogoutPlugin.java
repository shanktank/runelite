package net.runelite.client.plugins.neverlogout;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Never Logout",
	enabledByDefault = false,
	description = "Overrides the 5 minute AFK logout timer.",
	tags = {"never log", "idle", "logout", "log", "never"}
)
public class NeverLogoutPlugin extends Plugin {
	@Inject
	private Client client;

	@Subscribe
	private void onGameTick(GameTick gameTick) {
		if (client.getKeyboardIdleTicks() > 14900) { client.setKeyboardIdleTicks(0); }
		if (client.getMouseIdleTicks() > 14900) { client.setMouseIdleTicks(0); }
	}
}
