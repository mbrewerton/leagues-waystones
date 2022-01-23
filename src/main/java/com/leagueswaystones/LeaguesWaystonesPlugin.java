package com.leagueswaystones;

import com.google.inject.Provides;
import javax.inject.Inject;

import jdk.internal.jimage.ImageStrings;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;

import java.util.ArrayList;

@Slf4j
@PluginDescriptor(
	name = "Leagues Waystones"
)
public class LeaguesWaystonesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private WorldMapPointManager worldMapPointManager;

	@Inject
	private LeaguesWaystonesConfig config;
	private ArrayList<WorldMapPoint> waystonePoints = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		log.info("Leagues Waystones started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Leagues Waystones stopped. Clearing down data.");
		for (WorldMapPoint worldMapPoint: waystonePoints) {
			worldMapPointManager.remove(worldMapPoint);
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN && client.getWorldType().contains(WorldType.SEASONAL))
		{
			LeaguesWaystoneCoordinates waystoneCoordinates[] = LeaguesWaystoneCoordinates.values();
			for (LeaguesWaystoneCoordinates waystone: waystoneCoordinates) {
				WorldMapPoint worldPoint = waystone.getWorldPoint();
				worldPoint.setSnapToEdge(config.lockToEdge());
				waystonePoints.add(worldPoint);
				worldMapPointManager.add(worldPoint);
			}
		}
	}

	@Provides
	LeaguesWaystonesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LeaguesWaystonesConfig.class);
	}
}
