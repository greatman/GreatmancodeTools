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
package com.greatmancode.tools.caller.bukkit;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.greatmancode.tools.commands.bukkit.BukkitCommandReceiver;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.events.event.EconomyChangeEvent;
import com.greatmancode.tools.interfaces.BukkitLoader;
import com.greatmancode.tools.interfaces.Caller;
import com.greatmancode.tools.interfaces.Loader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * Server caller for Craftbukkit
 * @author greatman
 */
public class BukkitCaller extends Caller {
	private static final long TICK_LENGTH = 20L;
	
	
	public BukkitCaller(Loader loader) {
		super(loader);
	}

	@Override
	public void disablePlugin() {
		((BukkitLoader)loader).getPluginLoader().disablePlugin(((BukkitLoader)loader));
	}

	@Override
	public boolean checkPermission(String playerName, String perm) {
		boolean result;
		Player p = ((BukkitLoader)loader).getServer().getPlayerExact(playerName);
		if (p != null) {
			result = p.isOp() || p.hasPermission(perm);
		} else {
			// It's the console
			result = true;
		}
		return result;
	}

	@Override
	public void sendMessage(String playerName, String message) {
		Player p = ((BukkitLoader)loader).getServer().getPlayerExact(playerName);
		if (p != null) {
			p.sendMessage(addColor(getCommandPrefix() + message));
		} else {
			((BukkitLoader)loader).getServer().getConsoleSender().sendMessage(addColor(getCommandPrefix() + message));
		}
	}

	@Override
	public String getPlayerWorld(String playerName) {
		String result = "";
		Player p = ((BukkitLoader)loader).getServer().getPlayerExact(playerName);
		if (p != null) {
			result = p.getWorld().getName();
		}
		return result;
	}

	@Override
	public boolean isOnline(String playerName) {
		return ((BukkitLoader)loader).getServer().getPlayerExact(playerName) != null;
	}

	@Override
	public String addColor(String str) {
		String coloredString = str;
		coloredString = coloredString.replace("{{BLACK}}", ChatColor.BLACK.toString());
		coloredString = coloredString.replace("{{DARK_BLUE}}", ChatColor.DARK_BLUE.toString());
		coloredString = coloredString.replace("{{DARK_GREEN}}", ChatColor.DARK_GREEN.toString());
		coloredString = coloredString.replace("{{DARK_CYAN}}", ChatColor.DARK_AQUA.toString());
		coloredString = coloredString.replace("{{DARK_RED}}", ChatColor.DARK_RED.toString());
		coloredString = coloredString.replace("{{PURPLE}}", ChatColor.DARK_PURPLE.toString());
		coloredString = coloredString.replace("{{GOLD}}", ChatColor.GOLD.toString());
		coloredString = coloredString.replace("{{GRAY}}", ChatColor.GRAY.toString());
		coloredString = coloredString.replace("{{DARK_GRAY}}", ChatColor.DARK_GRAY.toString());
		coloredString = coloredString.replace("{{BLUE}}", ChatColor.BLUE.toString());
		coloredString = coloredString.replace("{{BRIGHT_GREEN}}", ChatColor.GREEN.toString());
		coloredString = coloredString.replace("{{CYAN}}", ChatColor.AQUA.toString());
		coloredString = coloredString.replace("{{RED}}", ChatColor.RED.toString());
		coloredString = coloredString.replace("{{PINK}}", ChatColor.LIGHT_PURPLE.toString());
		coloredString = coloredString.replace("{{YELLOW}}", ChatColor.YELLOW.toString());
		coloredString = coloredString.replace("{{WHITE}}", ChatColor.WHITE.toString());
		return coloredString;
	}

	@Override
	public String getDefaultWorld() {
		return ((BukkitLoader)loader).getServer().getWorlds().get(0).getName();
	}

	@Override
	public boolean worldExist(String worldName) {
		return ((BukkitLoader)loader).getServer().getWorld(worldName) != null;
	}

	@Override
	public File getDataFolder() {
		return ((BukkitLoader)loader).getDataFolder();
	}

	@Override
	public int schedule(Runnable entry, long firstStart, long repeating) {
		return schedule(entry, firstStart, repeating, false);
	}

	@Override
	public int schedule(Runnable entry, long firstStart, long repeating, boolean async) {
		if (!async) {
			if (repeating == 0) {
				return ((BukkitLoader)loader).getServer().getScheduler().runTaskLater(((BukkitLoader)loader), entry, firstStart * TICK_LENGTH).getTaskId();
			} else {
				return ((BukkitLoader)loader).getServer().getScheduler().scheduleSyncRepeatingTask(((BukkitLoader)loader), entry, firstStart * TICK_LENGTH, repeating * TICK_LENGTH);
			}
		} else {
			if (repeating == 0) {
				return ((BukkitLoader)loader).getServer().getScheduler().runTaskLaterAsynchronously(((BukkitLoader)loader), entry, firstStart * TICK_LENGTH).getTaskId();
			} else {
				return ((BukkitLoader)loader).getServer().getScheduler().runTaskTimerAsynchronously(((BukkitLoader)loader), entry, firstStart * TICK_LENGTH, repeating * TICK_LENGTH).getTaskId();
			}
		}
	}

	@Override
	public List<String> getOnlinePlayers() {
		List<String> list = new ArrayList<String>();
		Player[] pList = ((BukkitLoader)loader).getServer().getOnlinePlayers();
		for (Player p : pList) {
			list.add(p.getName());
		}
		return list;
	}

	@Override
	public void cancelSchedule(int id) {
		((BukkitLoader)loader).getServer().getScheduler().cancelTask(id);
	}

	@Override
	public int delay(Runnable entry, long start) {
		return delay(entry, start, false);
	}

	@Override
	public int delay(Runnable entry, long start, boolean async) {
		if (!async) {
			return ((BukkitLoader)loader).getServer().getScheduler().scheduleSyncDelayedTask(((BukkitLoader)loader), entry, start * TICK_LENGTH);
		} else {
			return ((BukkitLoader)loader).getServer().getScheduler().runTaskLaterAsynchronously(((BukkitLoader)loader), entry, start * TICK_LENGTH).getTaskId();
		}
	}

	@Override
	public void addCommand(String name, String help, CommandReceiver manager) {
		if (manager instanceof BukkitCommandReceiver) {
			((BukkitLoader)loader).getCommand(name).setExecutor((BukkitCommandReceiver) manager);
		}
	}

	@Override
	public String getServerVersion() {
		return Bukkit.getBukkitVersion();
	}

	@Override
	public String getPluginVersion() {
		return ((BukkitLoader)loader).getDescription().getVersion();
	}

	@Override
	public boolean isOp(String playerName) {
		return ((BukkitLoader)loader).getServer().getOfflinePlayer(playerName).isOp();
	}

	@Override
	public void loadLibrary(String path) {
		try {
			((BukkitLoader)loader).getPluginClassLoader().addURL(new File(path).toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			getLogger().log(Level.SEVERE, "Invalid library!", e.getMessage());
		}
	}

	@Override
	public void registerPermission(String permissionNode) {
		if (permissionNode != null) {
			try {
				((BukkitLoader)loader).getServer().getPluginManager().addPermission(new Permission(permissionNode));
			} catch (IllegalArgumentException e) {
				//Do nothing. We don't care if the permission was already registered.
			}
		}
	}

	@Override
	public boolean isOnlineMode() {
		return Bukkit.getServer().getOnlineMode();
	}

	@Override
	public Logger getLogger() {
		return Bukkit.getLogger();
	}

	@Override
	public void throwEvent(Event event) {
		if (event instanceof EconomyChangeEvent) {
			((BukkitLoader)loader).getServer().getPluginManager().callEvent(new com.greatmancode.tools.events.bukkit.events.EconomyChangeEvent(((EconomyChangeEvent) event).getAccount(), ((EconomyChangeEvent) event).getAmount()));
		}
	}
}
