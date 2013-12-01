/*
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2013, Greatman <http://github.com/greatman/>
 *
 * GreatmancodeTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreatmancodeTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreatmancodeTools.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.tools.caller.spout;

import java.io.File;
import java.util.logging.Logger;

import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.commands.spout.SpoutCommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.events.event.EconomyChangeEvent;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.interfaces.SpoutLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import org.spout.api.Server;

/**
 * Server serverCaller for Spout
 * @author greatman
 */
public class SpoutServerCaller extends ServerCaller {
	public SpoutServerCaller(Loader loader) {
		super(loader);
		addPlayerCaller(new SpoutPlayerCaller(this));
		addSchedulerCaller(new SpoutSchedulerCaller(this));
	}

	@Override
	public void disablePlugin() {
		((SpoutLoader) loader).getPluginLoader().disablePlugin((SpoutLoader) loader);
	}

	@Override
	public String addColor(String str) {
		String coloredString = str;
		coloredString = coloredString.replace("{{BLACK}}", SpoutChatStyle.BLACK.toString());
		coloredString = coloredString.replace("{{DARK_BLUE}}", SpoutChatStyle.DARK_BLUE.toString());
		coloredString = coloredString.replace("{{DARK_GREEN}}", SpoutChatStyle.DARK_GREEN.toString());
		coloredString = coloredString.replace("{{DARK_CYAN}}", SpoutChatStyle.DARK_AQUA.toString());
		coloredString = coloredString.replace("{{DARK_RED}}", SpoutChatStyle.DARK_RED.toString());
		coloredString = coloredString.replace("{{PURPLE}}", SpoutChatStyle.PURPLE.toString());
		coloredString = coloredString.replace("{{GOLD}}", SpoutChatStyle.GOLD.toString());
		coloredString = coloredString.replace("{{GRAY}}", SpoutChatStyle.GRAY.toString());
		coloredString = coloredString.replace("{{DARK_GRAY}}", SpoutChatStyle.DARK_GRAY.toString());
		coloredString = coloredString.replace("{{BLUE}}", SpoutChatStyle.BLUE.toString());
		coloredString = coloredString.replace("{{BRIGHT_GREEN}}", SpoutChatStyle.GREEN.toString());
		coloredString = coloredString.replace("{{CYAN}}", SpoutChatStyle.AQUA.toString());
		coloredString = coloredString.replace("{{RED}}", SpoutChatStyle.RED.toString());
		coloredString = coloredString.replace("{{PINK}}", SpoutChatStyle.PINK.toString());
		coloredString = coloredString.replace("{{YELLOW}}", SpoutChatStyle.YELLOW.toString());
		coloredString = coloredString.replace("{{WHITE}}", SpoutChatStyle.WHITE.toString());
		coloredString = coloredString.replace("{{OBFUSCATED}}", SpoutChatStyle.OBFUSCATED.toString());
		coloredString = coloredString.replace("{{BOLD}}", SpoutChatStyle.BOLD.toString());
		coloredString = coloredString.replace("{{UNDERLINE}}", SpoutChatStyle.UNDERLINE.toString());
		coloredString = coloredString.replace("{{ITALIC}}", SpoutChatStyle.ITALIC.toString());
		coloredString = coloredString.replace("{{RESET}}", SpoutChatStyle.RESET.toString());
		return coloredString;
	}

	@Override
	public boolean worldExist(String worldName) {
		return ((Server) ((SpoutLoader) loader).getEngine()).getWorld(worldName) != null;
	}

	@Override
	public String getDefaultWorld() {
		return ((SpoutLoader) loader).getEngine().getWorlds().iterator().next().getName();
	}

	@Override
	public File getDataFolder() {
		return ((SpoutLoader) loader).getDataFolder();
	}

	@Override
	public void addCommand(String name, String help, CommandReceiver manager) {
		if (manager instanceof SpoutCommandReceiver) {
			((SpoutLoader) loader).getEngine().getCommandManager().getCommand(name, true).setHelp(help).setExecutor((SpoutCommandReceiver) manager);
		}
	}

	@Override
	public String getServerVersion() {
		return ((SpoutLoader) loader).getEngine().getAPIVersion();
	}

	@Override
	public String getPluginVersion() {
		return ((SpoutLoader) loader).getDescription().getVersion();
	}

    @Override
    public String getPluginName() {
        return ((SpoutLoader) loader).getName();
    }

    @Override
	public void loadLibrary(String path) {
		((SpoutLoader) loader).loadLibrary(new File(path));
	}

	@Override
	public void registerPermission(String permissionNode) {
		//TODO: Not needed on spout?
	}

	@Override
	public boolean isOnlineMode() {
		return true;
	}

	@Override
	public Logger getLogger() {
		return ((SpoutLoader) loader).getLogger();
	}

	@Override
	public void throwEvent(Event event) {
		if (event instanceof EconomyChangeEvent) {
			((SpoutLoader) loader).getEngine().getEventManager().callEvent(new com.greatmancode.tools.events.spout.events.EconomyChangeEvent(((EconomyChangeEvent) event).getAccount(), ((EconomyChangeEvent) event).getAmount()));
		}
	}
}
