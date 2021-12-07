package com.coxscavcalculator;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Cox Scav Calculator",
		description = "Calculates Scav Drops and Herbs for potions in Cox"
)
public class CoxScavCalculatorPlugin extends Plugin
{
	@Inject
	private ClientToolbar clientToolbar;

	private CoxScavCalculatorPanel panel;
	private NavigationButton navButton;

	@Override
	protected void startUp()
	{
		startPanel();
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);

	}

	@Subscribe
	private void onGameTick(GameTick gameTick)
	{
		// runs every gametick
		;
	}

	private void startPanel(){
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "icon.png");
		panel = injector.getInstance(CoxScavCalculatorPanel.class);

		navButton = NavigationButton.builder()
				.tooltip("Cox Scavs")
				.icon(icon)
				.priority(7)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}
}
