package com.leagueswaystones;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface LeaguesWaystonesConfig extends Config
{
	@ConfigItem(
		keyName = "lockToEdge",
		name = "Lock to map edge",
		description = "Locks the waystone points to the edge of the map when not in view. Relog to apply.",
		position = 1
	)
	default boolean lockToEdge()
	{
		return true;
	}
}
